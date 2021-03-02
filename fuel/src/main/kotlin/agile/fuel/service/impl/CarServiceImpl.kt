package agile.fuel.service.impl

import agile.fuel.auth.getCurrentUserId
import agile.fuel.domain.dao.CarRepository
import agile.fuel.domain.dao.StatsRepository
import agile.fuel.domain.model.CarEntity
import agile.fuel.domain.model.CarStats
import agile.fuel.service.CarService
import agile.fuel.service.exceptions.FuelErrorType
import agile.fuel.service.exceptions.FuelException
import agile.fuel.web.dto.CreateCarDTO
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
                     val carRepository : CarRepository,
                     @Resource
                     val statsRepository: StatsRepository
) : CarService {
    override fun add(car: CreateCarDTO) : CarEntity {
        var carEntity = newCar(car)
        carEntity = carRepository.insert(carEntity)
        var carStats = newCarStats(carEntity.id!!, car.initialMileage!!)
        carStats =  statsRepository.insert(carStats)
        carEntity.stats = carStats
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
        dbCar.apply {
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
        statsRepository.deleteByCarId(dbCar.id!!)
        return dbCar
    }

    override fun findAllByOwner(ownerId: ObjectId): List<CarEntity> {
        var cars = mongoOperations.find(Query().addCriteria(CarEntity::ownerId isEqualTo ownerId), CarEntity::class.java)
        var carToId = cars.groupBy { it.id }
        statsRepository.findAllByCarIdIn(carToId.keys as Collection<ObjectId>).forEach {
            carToId[it.carId]!!.first().stats = it
        }
        return cars
    }

    override fun findOneByOwner(carId: ObjectId, ownerId: ObjectId) : CarEntity = with(Query()){
            addCriteria(CarEntity::ownerId isEqualTo ownerId)
            addCriteria(CarEntity::id isEqualTo carId)
            mongoOperations.findOne(this, CarEntity::class.java)?.also { it.stats = statsRepository.findByCarId(it.id!!) }
                ?: throw FuelException(FuelErrorType.UNKNOWN_CAR, carId.toHexString())
    }


    override fun findCarOwner(carId: ObjectId): Optional<ObjectId> = with(Query()){
           addCriteria(CarEntity::id isEqualTo carId)
           fields().include("ownerId")
           Optional.ofNullable(mongoOperations.findOne(this, CarEntity::class.java)?.ownerId)
    }

    private fun newCar(car: CreateCarDTO) : CarEntity = CarEntity().apply {
        carName = car.carName
        carDescription = car.carDescription
        make= car.make
        model = car.model
        year = car.year
        registrationNumber = car.registrationNumber
        VIN = car.VIN
        ownerId = getCurrentUserId()
    }

    private fun newCarStats(carId: ObjectId, initialMileage : Double) : CarStats = CarStats().also {
        it.carId = carId
        it.mileage = initialMileage
        it.initialMileage = initialMileage
    }


}