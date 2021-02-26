package agile.fuel.domain.dao;

import agile.fuel.domain.model.FuelEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface FuelRepository extends MongoRepository<FuelEntity, ObjectId> {
    List<FuelEntity> findAllByCarId(String carId);
}
