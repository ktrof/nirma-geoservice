package org.nirma.configuration;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import lombok.Getter;
import lombok.Setter;
import org.nirma.model.Feature;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mapping.context.MappingContext;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.index.IndexResolver;
import org.springframework.data.mongodb.core.index.MongoPersistentEntityIndexResolver;
import org.springframework.data.mongodb.core.index.ReactiveIndexOperations;
import org.springframework.data.mongodb.core.mapping.MongoPersistentEntity;
import org.springframework.data.mongodb.core.mapping.MongoPersistentProperty;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "nirma")
@EnableReactiveMongoRepositories(basePackages = {"org.nirma.repository"})
public class MongoConfiguration {

    private final Map<String, String> mongodb = new HashMap<>();

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
    public ReactiveMongoTemplate reactiveMongoTemplate() {
        ReactiveMongoTemplate mongoTemplate = new ReactiveMongoTemplate(mongo(), mongodb.get("database"));

        MappingContext<? extends MongoPersistentEntity<?>, MongoPersistentProperty> mappingContext = mongoTemplate
                .getConverter().getMappingContext();

        IndexResolver resolver = new MongoPersistentEntityIndexResolver(mappingContext);

        ReactiveIndexOperations indexOps = mongoTemplate.indexOps(Feature.class);
        resolver.resolveIndexFor(Feature.class).forEach(indexOps::ensureIndex);
        return mongoTemplate;
    }
}
