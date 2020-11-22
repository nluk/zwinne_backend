package agile.fuel.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "mongo")
class MongoProperties{
    var dbName = ""
    var host = ""
    var port = 27017
    var username = ""
    var password = ""
}