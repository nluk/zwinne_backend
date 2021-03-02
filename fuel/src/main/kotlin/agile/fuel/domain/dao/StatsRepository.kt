package agile.fuel.domain.dao

import agile.fuel.domain.model.CarStats
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.repository.CrudRepository

interface StatsRepository : MongoRepository<CarStats, ObjectId> {
    fun findAllByCarIdIn(carIds : Collection<ObjectId>) : List<CarStats>
    fun findByCarId(carId : ObjectId) : CarStats
    fun deleteByCarId(carId: ObjectId)
}