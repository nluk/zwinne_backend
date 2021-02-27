package agile.fuel.config

import agile.fuel.domain.conf.CostAdapter
import agile.fuel.domain.conf.ObjectIdDeserializer
import agile.fuel.domain.conf.ObjectIdSerializer
import agile.fuel.web.dto.CostDTO
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.bson.types.ObjectId
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component

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
                .registerTypeAdapter(CostDTO::class.java, CostAdapter())
                .setDateFormat(DATE_FORMAT)
                .create()
    }

}