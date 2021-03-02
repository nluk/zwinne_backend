package agile.fuel.domain.model

import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document

@Document("stats")
class CarStats : AuditableEntity() {
    @Indexed
    lateinit var carId : ObjectId

    var fuelCosts = 0.0
    var fuelAmount = 0.0
    var otherCosts = 0.0
    var initialMileage = 0.0
    var mileage = 0.0
}