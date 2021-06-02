package org.nirma.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "nirma")
@EnableRabbit
public class RabbitMQConfiguration {

    private final Map<String, String> rabbitmq = new HashMap<>();

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(rabbitmq.get("host"));
        connectionFactory.setUsername(rabbitmq.get("username"));
        connectionFactory.setConnectionTimeout(1000);
        connectionFactory.setPassword(rabbitmq.get("password"));
        return connectionFactory;
    }

    @Bean
    public AmqpAdmin amqpAdmin() {
        return new RabbitAdmin(connectionFactory());
    }

    @Bean
    public DirectExchange featureExchange() {
        return new DirectExchange(rabbitmq.get("exchange"));
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        return new RabbitTemplate(connectionFactory());
    }

    @Bean
    public Queue featureQueue() {
        return new Queue(rabbitmq.get("queue"));
    }

    @Bean
    public Binding binding() {
        return BindingBuilder
                .bind(featureQueue())
                .to(featureExchange())
                .with(rabbitmq.get("routingkey"));
    }

    @Bean
    public SimpleMessageListenerContainer messageListenerContainer() {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.addQueueNames();
        container.setConnectionFactory(connectionFactory());
        container.setQueueNames(rabbitmq.get("queue"));

        return container;
    }

}
