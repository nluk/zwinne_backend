package agile.fuel.config

import agile.fuel.auth.AuthUserDetails
import agile.fuel.domain.model.AuditableEntity
import agile.fuel.domain.serialization.ObjectIdDeserializer
import agile.fuel.domain.serialization.ObjectIdSerializer
import com.google.gson.ExclusionStrategy
import com.google.gson.FieldAttributes
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.bson.types.ObjectId
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.stereotype.Component
import java.text.DateFormat
import kotlin.reflect.full.isSuperclassOf

@Component
class GSONConfig {

    companion object{
        const val DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSZ"
    }

    @Bean
    fun gson() : Gson{
        return GsonBuilder()
                .registerTypeAdapter(ObjectId::class.java, ObjectIdDeserializer())
                .registerTypeAdapter(ObjectId::class.java, ObjectIdSerializer())
                .setDateFormat(DATE_FORMAT)
                .create()
    }

}