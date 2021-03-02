package agile.fuel.domain.model

import java.util.*

abstract class CostData{
    abstract var type : String
}

class FuelCost : CostData(){
    override var type: String = "FUEL"
    var liters = 0.0
    var fuelType = ""
    var costPerLiter = 0.0
    var fullCost : Double? = null
    var isFullRefueling = false
    var mileage = 0.0;
    lateinit var date :Date
}

class OtherCost : CostData() {
    override var type: String = "OTHER"
    var costType = ""
    var description = ""
    var fullCost : Double? = null
    var mileage = 0.0;
    lateinit var date : Date
}