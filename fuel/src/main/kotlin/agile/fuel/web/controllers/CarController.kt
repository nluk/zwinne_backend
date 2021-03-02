package agile.fuel.web.controllers

import agile.fuel.auth.getCurrentUserId
import agile.fuel.domain.model.CarEntity
import agile.fuel.service.CarService
import agile.fuel.web.ErrorUtil
import agile.fuel.web.aop.CheckOwner
import agile.fuel.web.dto.CreateCarDTO
import agile.fuel.web.dto.UpdateCarDTO
import org.bson.types.ObjectId
import org.springframework.http.ResponseEntity
import org.springframework.validation.Errors
import org.springframework.web.bind.annotation.*
import javax.annotation.Resource
import javax.validation.Valid
import javax.validation.constraints.NotNull

@RestController
@RequestMapping("/cars")
class CarController(
        @Resource
        val carService: CarService
) {

    @PostMapping
    fun add(@Valid @RequestBody carDTO: CreateCarDTO, errors : Errors?) : ResponseEntity<*>{
        return ErrorUtil.mapErrors(errors) ?: ResponseEntity.ok(carService.add(carDTO))
    }

    @CheckOwner
    @PutMapping
    fun update(@Valid @RequestBody carDTO : UpdateCarDTO, errors : Errors?) : ResponseEntity<*>{
        return ErrorUtil.mapErrors(errors) ?:ResponseEntity.ok(carService.update(carDTO))
    }

    @DeleteMapping("/{carId}")
    fun delete(@NotNull @PathVariable("carId") carId : ObjectId) : ResponseEntity<Any>{
        carService.deleteCar(carId)
        return ResponseEntity.noContent().build()
    }

    @GetMapping
    fun getCars() : ResponseEntity<List<CarEntity>>{
        return ResponseEntity.ok(carService.findAllByOwner(getCurrentUserId()))
    }

    @GetMapping("/{carId}")
    fun getCar(@NotNull @PathVariable("carId") carId : ObjectId) : ResponseEntity<CarEntity>{
        return ResponseEntity.ok(carService.findOneByOwner(carId, getCurrentUserId()))
    }

}