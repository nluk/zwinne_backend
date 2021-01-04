package agile.fuel.service.impl

import agile.fuel.domain.dao.CarRepository
import agile.fuel.domain.model.CarEntity
import agile.fuel.service.CarService
import agile.fuel.web.dto.CarDTO
import org.springframework.data.mongodb.core.MongoOperations
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Service
import org.springframework.util.SerializationUtils
import java.util.*
import javax.annotation.Resource

@Service
class CarServiceImpl(@Resource
                     val mongoTemplate: MongoTemplate,
                     @Resource
                     val mongoOperations : MongoOperations,
                     @Resource
                     val mongoRepository : CarRepository
) : CarService {
    override fun addCar(car: CarDTO) : List<CarEntity> {
        var carEntity1 = newCar(car)
        val initialMake = car.make
        carEntity1.make = "$initialMake - added using MongoTemplate"
        mongoTemplate.insert(carEntity1)
        var carEntity2 = newCar(car)
        carEntity2.make = "$initialMake - added using MongoOperations"
        mongoOperations.insert(carEntity2)
        var carEntity3 = newCar(car)
        carEntity3.make = "$initialMake - added using MongoRepository<Car, ObjectID>"
        mongoRepository.insert(carEntity3)
        return listOf(carEntity1, carEntity2, carEntity3)
    }

    private fun newCar(car: CarDTO) : CarEntity{
        val carEntity = CarEntity()
        carEntity.make= car.make
        carEntity.year = car.year
        return carEntity
    }


}