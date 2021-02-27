package agile.fuel.service

import agile.fuel.domain.model.CarEntity
import agile.fuel.web.dto.CarDTO
import agile.fuel.web.dto.UpdateCarDTO
import org.bson.types.ObjectId
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
interface CarService {
    fun add(car: CarDTO) : CarEntity
    fun getById(id : ObjectId) : Optional<CarEntity>
    fun update(car: UpdateCarDTO) : CarEntity
    fun findAllByOwner(ownerId : ObjectId) : List<CarEntity>
    fun findOneByOwner(carId : ObjectId, ownerId: ObjectId) : CarEntity
    fun findCarOwner(carId: ObjectId) : Optional<ObjectId>
}