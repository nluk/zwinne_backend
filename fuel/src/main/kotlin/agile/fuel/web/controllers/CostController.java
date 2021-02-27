package agile.fuel.web.controllers;

import agile.fuel.domain.model.CostEntity;
import agile.fuel.service.CostService;
import agile.fuel.web.dto.CostDTO;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/costs")
public class CostController {
    @Resource
    CostService costService;

    @PostMapping
    ResponseEntity<CostEntity> add(@RequestBody CostDTO costDTO) {
        return ResponseEntity.ok(costService.add(costDTO));
    }

    @GetMapping("/{carId}")
    ResponseEntity<List<CostEntity>> getCosts(@NotNull @PathVariable("carId") ObjectId carId) {
        return ResponseEntity.ok(costService.getCostsByCar(carId));
    }
}
