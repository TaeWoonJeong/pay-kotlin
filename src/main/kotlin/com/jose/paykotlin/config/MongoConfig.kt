package com.jose.paykotlin.config

import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import com.mongodb.client.MongoClient
import com.mongodb.client.MongoClients
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.MongoDatabaseFactory
import org.springframework.data.mongodb.MongoTransactionManager
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration

@Configuration
class MongoConfig : AbstractMongoClientConfiguration() {
    @Bean
    fun transactionManager(dbFactory: MongoDatabaseFactory): MongoTransactionManager {
        return MongoTransactionManager(dbFactory)
    }

    override fun getDatabaseName(): String {
        return "pay-kotlin"
    }

    override fun mongoClient(): MongoClient {
        val connectionString = ConnectionString("mongodb://localhost:27017/pay-kotlin")
        val mongoClientSettings = MongoClientSettings.builder()
            .applyConnectionString(connectionString)
            .build()
        return MongoClients.create(mongoClientSettings)
    }
}
