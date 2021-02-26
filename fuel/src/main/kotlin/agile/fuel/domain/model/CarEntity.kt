package agile.fuel.domain.model

import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.mapping.Document

@Document("fuel_cars")
class CarEntity : AuditableEntity() {
    var carName: String = ""
    var carDescription: String = ""
    var make : String = ""
    var model: String = ""
    var year : Int = 0
    var registrationNumber: String = ""
    var VIN: String = ""
    lateinit var ownerId : ObjectId
}