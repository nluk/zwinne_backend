package agile.fuel.service;

import agile.fuel.domain.model.CostEntity;
import agile.fuel.service.exceptions.FuelException;
import agile.fuel.web.dto.CostDTO;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CostService {
    CostEntity add(CostDTO fuel) throws FuelException;

    List<CostEntity> getCostsByCar(ObjectId carId);

}
