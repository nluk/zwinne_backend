package agile.fuel.domain.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("fuelling")
public class FuelEntity extends AuditableEntity{
    public Double liters = 0.0;
    public Double costPerLiter = 0.0;
    public Double fullCost = 0.0;
    public String type = "";
    public String carId = null;
}
