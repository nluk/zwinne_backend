package agile.fuel.service.exceptions



enum class FuelErrorType(private val messageTemplate : String) {
    UNKNOWN_ERROR("Unknown error occured"),
    DUPLICATED_EMAIL("Customer with email {} already exists");

    fun message(vararg args : Any) : String {
        val rawTemplate = messageTemplate.split("{}")
        return rawTemplate.reduceIndexed{ index, acc, next -> if(index != rawTemplate.size) acc + args[index - 1] + next else acc + next }
    }
}