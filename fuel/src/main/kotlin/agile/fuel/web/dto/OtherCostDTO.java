package agile.fuel.web.dto;

import java.util.Date;

public class OtherCostDTO extends CostDTO {
    public static final String TYPE = "OTHER";

    public String costType;

    public String description;

    public Double fullCost;

    public Double mileage;

    public Date date;

}
