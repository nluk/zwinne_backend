package agile.fuel.web.dto;

import agile.fuel.web.aop.CarReference;
import org.bson.types.ObjectId;
import org.jetbrains.annotations.Nullable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;

public abstract class CostDTO implements CarReference {

    @NotNull
    public ObjectId carId;

    @NotNull
    public Double fullCost;

    @NotNull
    public Double mileage;

    @NotNull
    public Date date;

    @org.jetbrains.annotations.NotNull
    @Override
    public ObjectId carId() {
        return carId;
    }

}