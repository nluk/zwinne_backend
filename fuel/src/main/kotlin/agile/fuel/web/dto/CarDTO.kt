package agile.fuel.web.dto

import agile.fuel.web.aop.CarReference
import org.bson.types.ObjectId
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

open class CarDTO {
        @NotBlank
        var carName: String = ""

        @NotBlank
        var carDescription: String = ""

        @NotBlank
        var make : String = ""

        @NotBlank
        var model: String = ""

        @NotNull
        var year : Int = 0

        var registrationNumber: String = ""
        var VIN: String = ""

}

open class UpdateCarDTO : CarDTO(), CarReference{
        @NotNull
        lateinit var id : ObjectId
        @NotNull
        var version : Long = 0L
        override fun carId() = id
}