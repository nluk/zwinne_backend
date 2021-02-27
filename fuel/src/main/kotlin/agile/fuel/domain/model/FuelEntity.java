package agile.fuel.domain.model;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("fuelling")
public class FuelEntity extends AuditableEntity{
    @Indexed
    public ObjectId carId;

    public Double liters = 0.0;
    public Double costPerLiter = 0.0;
    public Double fullCost = 0.0;
    public String type = "";
}
