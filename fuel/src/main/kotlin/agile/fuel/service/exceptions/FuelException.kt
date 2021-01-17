package agile.fuel.service.exceptions

class FuelException(val fuelError : FuelErrorType, private vararg val args : Any) : Throwable() {
    override val message : String
    get() = fuelError.message(*args)
}