package agile.fuel.config

import agile.fuel.auth.AuthUserDetails
import agile.fuel.domain.model.AuditableEntity
import com.google.gson.ExclusionStrategy
import com.google.gson.FieldAttributes
import com.google.gson.Gson
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.stereotype.Component
import kotlin.reflect.full.isSuperclassOf

@Component
class GSONConfig {

    @Bean
    fun gson() : Gson{
        return Gson()
    }

}