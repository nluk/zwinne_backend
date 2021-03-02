package agile.fuel.service.impl

import agile.fuel.domain.model.CostEntity
import agile.fuel.domain.model.FuelCost
import agile.fuel.domain.model.OtherCost
import agile.fuel.service.exceptions.FuelErrorType
import agile.fuel.service.exceptions.FuelException
import agile.fuel.web.dto.CostDTO
import agile.fuel.web.dto.FuelCostDTO
import agile.fuel.web.dto.OtherCostDTO
import org.springframework.stereotype.Component

@Component
object CostMapper {
    fun map(costDTO : CostDTO) : CostEntity = when(costDTO){
        is FuelCostDTO -> toFuelCost(costDTO)
        is OtherCostDTO -> toOtherCost(costDTO)
        else -> throw FuelException(FuelErrorType.UNKNOWN_COST_ENTITY, costDTO::class.java.simpleName)
    }

    fun toOtherCost(otherCostDTO: OtherCostDTO) = CostEntity().apply {
        carId = otherCostDTO.carId
        cost = OtherCost().apply {
            costType = otherCostDTO.costType
            description = otherCostDTO.description
            fullCost = otherCostDTO.fullCost
            mileage = otherCostDTO.mileage
            date = otherCostDTO.date
        }
    }

    fun toFuelCost(fuelCostDTO: FuelCostDTO) = CostEntity().apply {
        carId = fuelCostDTO.carId
        cost = FuelCost().apply {
            liters = fuelCostDTO.liters
            fuelType = fuelCostDTO.fuelType
            fullCost = fuelCostDTO.fullCost
            costPerLiter = fuelCostDTO.costPerLiter
            isFullRefueling = fuelCostDTO.isFullRefueling
            mileage = fuelCostDTO.mileage
            date = fuelCostDTO.date
        }
    }

}