package agile.fuel.service.impl

import agile.fuel.domain.model.CostEntity
import agile.fuel.domain.model.FuelCost
import agile.fuel.service.exceptions.FuelErrorType
import agile.fuel.service.exceptions.FuelException
import agile.fuel.web.dto.CostDTO
import agile.fuel.web.dto.FuelCostDTO
import org.springframework.stereotype.Component

@Component
object CostMapper {
    fun map(costDTO : CostDTO) : CostEntity = when(costDTO){
        is FuelCostDTO -> toFuelCost(costDTO)
        else -> throw FuelException(FuelErrorType.UNKNOWN_COST_ENTITY, costDTO::class.java.simpleName)
    }

    fun toFuelCost(fuelCostDTO: FuelCostDTO) = CostEntity().apply {
        carId = fuelCostDTO.carId
        cost = FuelCost().apply {
            liters = fuelCostDTO.liters
            fullCost = fuelCostDTO.fullCost
            costPerLiter = fuelCostDTO.costPerLiter
        }
    }

}