package agile.fuel.service.impl

import agile.fuel.auth.getCurrentUserId
import agile.fuel.domain.dao.CarRepository
import agile.fuel.domain.model.CarEntity
import agile.fuel.service.CarService
import agile.fuel.service.exceptions.FuelErrorType
import agile.fuel.service.exceptions.FuelException
import agile.fuel.web.dto.CarDTO
import agile.fuel.web.dto.UpdateCarDTO
import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.MongoOperations
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.isEqualTo
import org.springframework.stereotype.Service
import java.util.*
import javax.annotation.Resource

@Service
class CarServiceImpl(
                     @Resource
                     val mongoOperations : MongoOperations,
                     @Resource
                     val carRepository : CarRepository
) : CarService {
    override fun add(car: CarDTO) : CarEntity {
        val carEntity = newCar(car)
        carRepository.insert(carEntity)
        return carEntity
    }

    override fun getById(id: ObjectId): Optional<CarEntity> {
        return carRepository.findById(id)
    }

    override fun update(car: UpdateCarDTO) : CarEntity{
        val dbCar = carRepository.findById(car.id).orElseThrow { FuelException(FuelErrorType.UNKNOWN_CAR, car.id.toHexString()) }
        if(dbCar.version > car.version){
            throw FuelException(FuelErrorType.OPTIMISTIC_LOCK, car.id.toHexString())
        }
        with(dbCar){
            carName = car.carName
            carDescription = car.carDescription
            make = car.make
            model = car.model
            year = car.year
            registrationNumber = car.registrationNumber
            VIN = car.VIN

        }
        carRepository.save(dbCar)
        return dbCar
    }

    override fun deleteCar(carId: ObjectId): CarEntity {
        val dbCar = carRepository.findById(carId).orElseThrow { FuelException(FuelErrorType.UNKNOWN_CAR, carId.toHexString()) }
        carRepository.delete(dbCar)
        return dbCar
    }

    override fun findAllByOwner(ownerId: ObjectId): List<CarEntity> {
        return mongoOperations.find(Query().addCriteria(CarEntity::ownerId isEqualTo ownerId), CarEntity::class.java)
    }

    override fun findOneByOwner(carId: ObjectId, ownerId: ObjectId) : CarEntity {
        with(Query()){
            addCriteria(CarEntity::ownerId isEqualTo ownerId)
            addCriteria(CarEntity::id isEqualTo carId)
            return mongoOperations.findOne(this, CarEntity::class.java) ?: throw FuelException(FuelErrorType.UNKNOWN_CAR, carId.toHexString())
        }
    }

    private fun newCar(car: CarDTO) : CarEntity{
        val carEntity = CarEntity()
        carEntity.carName = car.carName
        carEntity.carDescription = car.carDescription
        carEntity.make= car.make
        carEntity.model = car.model
        carEntity.year = car.year
        carEntity.registrationNumber = car.registrationNumber
        carEntity.VIN = car.VIN
        carEntity.ownerId = getCurrentUserId()
        return carEntity
    }


}