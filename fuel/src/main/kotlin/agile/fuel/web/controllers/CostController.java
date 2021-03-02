package agile.fuel.web.controllers;

import agile.fuel.domain.model.CostEntity;
import agile.fuel.service.CostService;
import agile.fuel.service.exceptions.FuelException;
import agile.fuel.web.ErrorUtil;
import agile.fuel.web.dto.CostDTO;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/costs")
public class CostController {
    @Resource
    CostService costService;

    @PostMapping
    ResponseEntity<?> add(@Valid @RequestBody CostDTO costDTO, Errors errors) throws FuelException {
        var err = ErrorUtil.INSTANCE.mapErrors(errors);
        return err != null ? err : ResponseEntity.ok(costService.add(costDTO));
    }

    @GetMapping("/{carId}")
    ResponseEntity<?> getCosts(@NotNull @PathVariable("carId") ObjectId carId) {
        return ResponseEntity.ok(costService.getCostsByCar(carId));
    }
}
