package agile.fuel.web.aop

import agile.fuel.auth.getCurrentUserId
import agile.fuel.domain.model.CarEntity
import agile.fuel.service.CarService
import agile.fuel.service.exceptions.FuelErrorType
import agile.fuel.service.exceptions.FuelException
import agile.fuel.web.dto.UpdateCarDTO
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.bson.types.ObjectId
import org.slf4j.LoggerFactory
import org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Aspect
@Component
class CheckOwnerAspect(
    @Autowired val carService: CarService) {

    companion object{
        val log = LoggerFactory.getLogger(CheckOwnerAspect::class.java)
    }

    @Throws(FuelException::class)
    @Before("@annotation(CheckOwner)")
    fun checkOwner(joinPoint: JoinPoint){
        (joinPoint as? MethodInvocationProceedingJoinPoint)?.run {
            if(!args.isNullOrEmpty()){
                val carReference : CarReference = args.first { it is CarReference } as CarReference
                val carOwnerId : ObjectId = carService.findCarOwner(carReference.carId()).orElseThrow { FuelException(FuelErrorType.UNKNOWN_CAR, carReference.carId().toHexString()) }
                if (getCurrentUserId() != carOwnerId){
                    throw FuelException(FuelErrorType.WRONG_OWNER, carReference.carId().toHexString(), getCurrentUserId().toHexString())
                }
            }
        }
    }

}