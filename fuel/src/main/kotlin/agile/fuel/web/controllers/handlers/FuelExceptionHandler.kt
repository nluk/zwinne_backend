package agile.fuel.web.controllers.handlers

import agile.fuel.service.exceptions.FuelException
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.time.LocalDateTime
import java.util.*


@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
class FuelExceptionHandler : ResponseEntityExceptionHandler() {

    @ExceptionHandler(FuelException::class)
    protected fun handleError(fuelException: FuelException): ResponseEntity<ErrorMessage> {
        val errorMessage = ErrorMessage(fuelException.message, fuelException.fuelError.status.name, fuelException.fuelError.status.value(), Date())
        return ResponseEntity(errorMessage, fuelException.fuelError.status)
    }

    protected data class ErrorMessage(
            val message : String,
            val statusCode : String,
            val status : Int,
            val timestamp : Date
    )
}