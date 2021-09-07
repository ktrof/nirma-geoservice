package org.nirma.configuration;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "nirma")
@EnableReactiveMongoRepositories(basePackages = {"org.nirma.repository"})
public class MongoConfiguration extends AbstractReactiveMongoConfiguration {

    private final Map<String, String> mongodb = new HashMap<>();

    @Override
    public MongoClient reactiveMongoClient() {
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

    @Override
    protected String getDatabaseName() {
        return mongodb.get("database");
    }

    @Override
    public boolean autoIndexCreation() {
        return true;
    }

    public Integer getDeleteDaysLimit() {
        return Integer.parseInt(mongodb.get("deletedayslimit"));
    }
}
