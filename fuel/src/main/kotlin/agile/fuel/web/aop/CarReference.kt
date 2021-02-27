package agile.fuel.web.aop

import org.bson.types.ObjectId

interface CarReference {
    fun carId() : ObjectId
}