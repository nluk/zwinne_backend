package agile.fuel.domain.model

abstract class CostData{
    abstract var type : String
}

class FuelCost : CostData(){
    override var type: String = "FUEL"
    var liters = 0.0
    var costPerLiter = 0.0
    var fullCost : Double? = null
}