package agile.fuel.domain.model;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("costs")
public class CostEntity extends AuditableEntity{
    @Indexed
    public ObjectId carId;

    public CostData cost;
}
