package agile.fuel.web.controllers;

import agile.fuel.domain.model.FuelEntity;
import agile.fuel.service.FuelService;
import agile.fuel.web.dto.FuelDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/refuel")
public class FuelController {
    @Resource
    FuelService fuelService;

    @PostMapping("/add")
    ResponseEntity<FuelEntity> add(FuelDTO fuelDTO) {
        return ResponseEntity.ok(fuelService.add(fuelDTO));
    }

    @GetMapping("/carId/{carId}")
    ResponseEntity<List<FuelEntity>> getFuels(@PathVariable("carId") String carId) {
        return ResponseEntity.ok(fuelService.getFuelsByCar(carId));
    }
}
