package agile.fuel.domain.model

import java.util.*

abstract class CostData{
    abstract var type : String
    var mileage = 0.0
    var fullCost : Double = 0.0
}

class FuelCost : CostData(){
    override var type: String = "FUEL"
    var liters = 0.0
    var fuelType : String? = null
    var costPerLiter : Double? = null
    var isFullRefueling = false
    var petrolStation : String? = null
    lateinit var date :Date
}

class OtherCost : CostData() {
    override var type: String = "OTHER"
    var costType = ""
    var description = ""
    lateinit var date : Date
}