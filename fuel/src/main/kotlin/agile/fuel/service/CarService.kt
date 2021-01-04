package agile.fuel.service

import agile.fuel.domain.model.CarEntity
import agile.fuel.web.dto.CarDTO
import org.bson.types.ObjectId
import org.springframework.stereotype.Service

@Service
interface CarService {
    fun addCar(car: CarDTO) : List<CarEntity>
}