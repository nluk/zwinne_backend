package agile.fuel.domain.model

import org.springframework.data.mongodb.core.mapping.Document

@Document("fuel_cars")
class CarEntity : AuditableEntity() {
    var make : String = ""
    var year : Int = 0
}