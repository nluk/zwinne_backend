package agile.fuel.web.dto;

import org.jetbrains.annotations.Nullable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;
import java.util.Date;

public class FuelCostDTO extends CostDTO{

    public static final String TYPE = "FUEL";

    @NotNull
    public Double liters;

    @Nullable
    public String fuelType;

    @Nullable
    public Double costPerLiter;

    @Nullable
    public Boolean isFullRefueling;

    @Nullable
    public String petrolStation;


}
