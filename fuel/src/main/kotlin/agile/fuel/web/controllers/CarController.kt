package agile.fuel.web.controllers

import agile.fuel.domain.model.CarEntity
import agile.fuel.service.CarService
import agile.fuel.web.dto.CarDTO
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import javax.annotation.Resource

@RestController
@RequestMapping("/cars")
class CarController(
        @Resource
        val carService: CarService
) {

    @RequestMapping("/add", method = [RequestMethod.POST])
    fun add(@RequestBody carDTO: CarDTO) : ResponseEntity<List<CarEntity>>{
        return ResponseEntity.ok(carService.addCar(carDTO))
    }
}