package agile.fuel.web.dto

import agile.fuel.web.aop.CarReference
import org.bson.types.ObjectId
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

open class CarDTO {
        @get:NotBlank
        var carName: String = ""

        var carDescription: String = ""

        @get:NotBlank
        var make : String = ""

        @get:NotBlank
        var model: String = ""

        @get:NotNull
        var year : Int = 0

        var registrationNumber: String = ""
        var VIN: String = ""

        var archived: Boolean = false
}

open class CreateCarDTO : CarDTO(){

        @get:NotNull
        var initialMileage : Double? = null
}

open class UpdateCarDTO : CarDTO(), CarReference{
        @get:NotNull
        lateinit var id : ObjectId
        @get:NotNull
        var version : Long = 0L
        override fun carId() = id
}