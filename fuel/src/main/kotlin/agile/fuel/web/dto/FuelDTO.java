package agile.fuel.web.dto;

import org.bson.types.ObjectId;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class FuelDTO {

    @NotNull
    public Double liters;

    @NotNull
    public Double costPerLiter;

    public Double fullCost;

    public String type;

    @NotNull
    public ObjectId carId;

}