package agile.fuel.domain.model

import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.mapping.Document

@Document("fuel_cars")
class CarEntity : AuditableEntity() {
    var make : String = ""
    var year : Int = 0
    lateinit var ownerId : ObjectId
}