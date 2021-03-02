package agile.fuel.service.impl;

import agile.fuel.domain.dao.CarRepository;
import agile.fuel.domain.dao.CostRepository;
import agile.fuel.domain.dao.StatsRepository;
import agile.fuel.domain.model.CostEntity;
import agile.fuel.domain.model.FuelCost;
import agile.fuel.service.CostService;
import agile.fuel.service.exceptions.FuelErrorType;
import agile.fuel.service.exceptions.FuelException;
import agile.fuel.web.dto.CostDTO;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CostServiceImpl implements CostService {
        @Resource
        MongoOperations mongoOperations;

        @Resource
        CostRepository costRepository;

        @Resource
        CostMapper costMapper;

        @Resource
        StatsRepository statsRepository;

        @Resource
        CarRepository carRepository;

    @Override
    public CostEntity add(CostDTO cost) throws FuelException {
        var car = carRepository.findById(cost.carId).orElseThrow(()-> new FuelException(FuelErrorType.UNKNOWN_CAR, cost.carId));
        CostEntity costEntity = costMapper.map(cost);
        costEntity = costRepository.insert(costEntity);
        updateStats(costEntity);
        return costEntity;
    }

    @Override
    public List<CostEntity> getCostsByCar(ObjectId carId) {
        return costRepository.findAllByCarId(carId);
    }

    private void updateStats(CostEntity costEntity){
        var stats = statsRepository.findByCarId(costEntity.carId);
        stats.setMileage(costEntity.cost.getMileage());
        if(costEntity.cost instanceof FuelCost){
            FuelCost fuel = (FuelCost) costEntity.cost;
            stats.setFuelCosts(stats.getFuelCosts() + fuel.getFullCost());
            stats.setFuelAmount(stats.getFuelAmount() + fuel.getLiters());
        }
        else {
            stats.setOtherCosts(stats.getOtherCosts() + costEntity.cost.getFullCost());
        }
        statsRepository.save(stats);
    }

}
