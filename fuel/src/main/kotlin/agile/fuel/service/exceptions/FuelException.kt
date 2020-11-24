package agile.fuel.service.exceptions

class FuelException(private val fuelError : FuelErrorType, private vararg val args : Any) : Throwable() {
    override val message : String
    get() = fuelError.message(*args)
}