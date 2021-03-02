package agile.fuel.web.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

public class FuelCostDTO extends CostDTO{

    public static final String TYPE = "FUEL";

    public Double liters;

    public String fuelType;

    @NotNull
    public Double costPerLiter;

    public Double fullCost;

    public Boolean isFullRefueling;

    public Double mileage;

    public Date date;

}
