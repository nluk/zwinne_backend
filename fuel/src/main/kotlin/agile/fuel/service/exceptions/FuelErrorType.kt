package agile.fuel.service.exceptions

import org.springframework.http.HttpStatus


enum class FuelErrorType {
    UNKNOWN_ERROR("Unknown error occured"),
    DUPLICATED_EMAIL("Customer with email {} already exists", HttpStatus.BAD_REQUEST),
    UNKNOWN_CAR("Car with id {} doesn't exist", HttpStatus.NOT_FOUND),
    WRONG_OWNER("Car with id {} doesn't belong to user {}", HttpStatus.UNAUTHORIZED),
    OPTIMISTIC_LOCK("Car with id {} was updated before your attempt", HttpStatus.BAD_REQUEST);

    private val messageTemplate : String
    val status : HttpStatus

    constructor(messageTemplate : String){
        this.messageTemplate = messageTemplate
        this.status = HttpStatus.INTERNAL_SERVER_ERROR
    }

    constructor(messageTemplate: String, status : HttpStatus){
        this.messageTemplate = messageTemplate
        this.status = status
    }

    fun message(vararg args : Any) : String {
        val rawTemplate = messageTemplate.split("{}")
        return rawTemplate.reduceIndexed{ index, acc, next -> if(index != rawTemplate.size) acc + args[index - 1] + next else acc + next }
    }
}