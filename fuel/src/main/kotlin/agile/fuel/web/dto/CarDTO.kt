package agile.fuel.web.dto

import org.bson.types.ObjectId
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

open class CarDTO {
        @NotBlank
        var make: String = ""
        @NotNull
        var year : Int = 0
}

open class UpdateCarDTO : CarDTO(){
        @NotNull
        lateinit var id : ObjectId
        @NotNull
        var version : Long = 0L
}