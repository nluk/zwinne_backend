package agile.fuel.service.impl;

import agile.fuel.domain.dao.CostRepository;
import agile.fuel.domain.model.CostEntity;
import agile.fuel.service.CostService;
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

    @Override
    public CostEntity add(CostDTO cost) {
        CostEntity costEntity = costMapper.map(cost);
        costRepository.insert(costEntity);
        return costEntity;
    }

    @Override
    public List<CostEntity> getCostsByCar(ObjectId carId) {
        return costRepository.findAllByCarId(carId);
    }

}
