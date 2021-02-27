package agile.fuel.web.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class FuelCostDTO extends CostDTO{

    public static final String TYPE = "FUEL";

    public Double liters;

    @NotNull
    public Double costPerLiter;

    public Double fullCost;

}
