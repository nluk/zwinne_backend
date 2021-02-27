package agile.fuel.web.dto;

import org.bson.types.ObjectId;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

public abstract class CostDTO {

    @NotNull
    public ObjectId carId;

}