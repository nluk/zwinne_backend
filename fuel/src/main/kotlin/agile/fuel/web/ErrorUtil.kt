package agile.fuel.web

import org.springframework.http.ResponseEntity
import org.springframework.validation.Errors

object ErrorUtil {
    fun mapErrors(errors : Errors?) : ResponseEntity<Any>?{
        return if(errors?.hasErrors() == true) ResponseEntity.badRequest().body(errors.fieldErrors.map { FieldError(it.field, it.defaultMessage) }) else null
    }
}

data class FieldError(
    val field : String,
    val error : String?
)