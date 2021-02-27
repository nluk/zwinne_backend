package agile.fuel.domain.conf

import agile.fuel.web.dto.CostDTO
import agile.fuel.web.dto.FuelCostDTO
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import java.lang.reflect.Type

class CostAdapter : JsonDeserializer<CostDTO> {

    private companion object {
        private const val TYPE = "type"
        private val COST_TYPES = mapOf(
         FuelCostDTO.TYPE to FuelCostDTO::class.java
        )
    }

    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): CostDTO {
        val providedType = json.asJsonObject.getAsJsonPrimitive(TYPE).asString
        return context.deserialize(json, COST_TYPES[providedType] ?: throw JsonParseException("Unknown type $providedType"))
    }
}