package agile.fuel.web.controllers

import agile.fuel.auth.getCurrentUserId
import agile.fuel.domain.model.CarEntity
import agile.fuel.service.CarService
import agile.fuel.web.aop.CheckOwner
import agile.fuel.web.dto.CarDTO
import agile.fuel.web.dto.UpdateCarDTO
import org.bson.types.ObjectId
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.annotation.Resource
import javax.validation.constraints.NotBlank
import javax.websocket.server.PathParam

@RestController
@RequestMapping("/cars")
class CarController(
        @Resource
        val carService: CarService
) {

    @PostMapping("/add")
    fun add(@RequestBody carDTO: CarDTO) : ResponseEntity<CarEntity>{
        return ResponseEntity.ok(carService.add(carDTO))
    }

    @CheckOwner
    @PostMapping("/update")
    fun update(@RequestBody carDTO : UpdateCarDTO) : ResponseEntity<CarEntity>{
        return ResponseEntity.ok(carService.update(carDTO))
    }

    @GetMapping
    fun getCars() : ResponseEntity<List<CarEntity>>{
        return ResponseEntity.ok(carService.findAllByOwner(getCurrentUserId()))
    }

    @GetMapping("/id/{carId}")
    fun getCar(@PathVariable("carId") @NotBlank carId : String) : ResponseEntity<CarEntity>{
        return ResponseEntity.ok(carService.findOneByOwner(ObjectId(carId), getCurrentUserId()))
    }

}