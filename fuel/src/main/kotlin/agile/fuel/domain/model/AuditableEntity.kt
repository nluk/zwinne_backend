package agile.fuel.domain.model

import com.google.gson.ExclusionStrategy
import com.google.gson.FieldAttributes
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Version
import java.io.Serializable
import java.time.LocalDateTime
import java.util.*

abstract class AuditableEntity : Serializable {
    @Id
    var id : ObjectId? = null
    var created : Date = Date()
    var modified : Date ? = null
    @Version
    var version = 0L
}