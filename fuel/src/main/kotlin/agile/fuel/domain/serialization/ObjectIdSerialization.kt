package agile.fuel.domain.serialization

import com.google.gson.*
import org.bson.types.ObjectId
import java.lang.reflect.Type

class ObjectIdSerializer : JsonSerializer<ObjectId> {
    override fun serialize(objectId: ObjectId, type: Type, context: JsonSerializationContext): JsonElement = JsonPrimitive(objectId.toHexString())
}

class ObjectIdDeserializer : JsonDeserializer<ObjectId> {
    override fun deserialize(jsonElement: JsonElement, type: Type, context: JsonDeserializationContext): ObjectId = ObjectId(jsonElement.asString)
}