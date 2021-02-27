package agile.fuel.web.controllers;

import agile.fuel.domain.model.FuelEntity;
import agile.fuel.service.FuelService;
import agile.fuel.web.dto.FuelDTO;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/refuel")
public class FuelController {
    @Resource
    FuelService fuelService;

    @PostMapping
    ResponseEntity<FuelEntity> add(@RequestBody FuelDTO fuelDTO) {
        return ResponseEntity.ok(fuelService.add(fuelDTO));
    }

    @GetMapping("/{carId}")
    ResponseEntity<List<FuelEntity>> getFuels(@NotNull @PathVariable("carId") ObjectId carId) {
        return ResponseEntity.ok(fuelService.getFuelsByCar(carId));
    }
}
