package agile.fuel.service.impl;

import agile.fuel.domain.dao.FuelRepository;
import agile.fuel.domain.model.FuelEntity;
import agile.fuel.service.FuelService;
import agile.fuel.web.dto.FuelDTO;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class FuelServiceImpl implements FuelService {
        @Resource
        MongoOperations mongoOperations;

        @Resource
        FuelRepository fuelRepository;

    @Override
    public FuelEntity add(FuelDTO fuel) {
        FuelEntity fuelEntity = newFuel(fuel);
        fuelRepository.insert(fuelEntity);
        return fuelEntity;
    }

    @Override
    public List<FuelEntity> getFuelsByCar(String carId) {
        return fuelRepository.findAllByCarId(carId);
    }

    private FuelEntity newFuel(FuelDTO fuel) {
        FuelEntity fuelEntity = new FuelEntity();
        fuelEntity.liters = fuel.liters;
        fuelEntity.costPerLiter = fuel.costPerLiter;
        fuelEntity.fullCost = fuel.fullCost;
        fuelEntity.type = fuel.type;
        fuelEntity.carId = fuel.carId;
        return fuelEntity;
    }

}
