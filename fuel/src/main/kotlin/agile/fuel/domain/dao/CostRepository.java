package agile.fuel.domain.dao;

import agile.fuel.domain.model.CostEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CostRepository extends MongoRepository<CostEntity, ObjectId> {
    List<CostEntity> findAllByCarId(ObjectId carId);
}
