package org.nirma.configuration;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.HashMap;
import java.util.Map;

@Getter
@Configuration
@ConfigurationProperties(prefix = "nirma")
@EnableMongoRepositories(basePackages = {"org.nirma.repository"})
public class MongoConfiguration {

    private Map<String, String> mongodb = new HashMap<>();

    @Bean
    public MongoClient mongo() {
        ConnectionString connectionString = new ConnectionString(mongodb.get("uri"));
        MongoCredential credential = MongoCredential.createCredential(
                mongodb.get("username"),
                mongodb.get("database"),
                mongodb.get("password").toCharArray());
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                .credential(credential)
                .applyConnectionString(connectionString)
                .build();
        return MongoClients.create(mongoClientSettings);
    }

    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongo(), mongodb.get("database"));
    }
}
