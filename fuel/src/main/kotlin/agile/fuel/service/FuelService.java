package agile.fuel.service;

import agile.fuel.domain.model.FuelEntity;
import agile.fuel.web.dto.FuelDTO;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FuelService {
    FuelEntity add(FuelDTO fuel);

    List<FuelEntity> getFuelsByCar(ObjectId carId);

}
