package agile.fuel.config

import agile.fuel.domain.converters.StringToLocalDateTime
import agile.fuel.domain.converters.allConverters
import com.mongodb.MongoClientSettings
import com.mongodb.MongoCredential
import com.mongodb.ServerAddress
import com.mongodb.client.MongoClientFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration
import org.springframework.data.mongodb.core.MongoClientFactoryBean
import org.springframework.data.mongodb.core.convert.MongoCustomConversions
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories

@Configuration
@EnableConfigurationProperties(value = [MongoProperties::class])
@EnableMongoRepositories(basePackages = ["agile.fuel.domain.dao"])
class MongoConfig(
        @Autowired
        val mongoProperties : MongoProperties
) : AbstractMongoClientConfiguration(){

    override fun configureClientSettings(builder: MongoClientSettings.Builder) {
        builder.credential(
                mongoProperties.run { MongoCredential.createCredential(username, dbName, password.toCharArray()) }
        ).applyToClusterSettings { settings ->
            settings.hosts(listOf(mongoProperties.run { ServerAddress(host, port) }))
        }
    }

    override fun getDatabaseName(): String {
        return mongoProperties.dbName
    }

    @Bean
    fun registerConverters() : MongoCustomConversions = MongoCustomConversions(allConverters())

}