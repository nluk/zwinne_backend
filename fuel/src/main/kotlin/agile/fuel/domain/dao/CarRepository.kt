package agile.fuel.domain.dao

import agile.fuel.domain.model.CarEntity
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface CarRepository : MongoRepository<CarEntity, ObjectId> {
}