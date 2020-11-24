package agile.fuel.domain.model

import org.springframework.data.annotation.Version
import java.time.LocalDateTime
import java.util.*

abstract class AuditableEntity {
    var created  = LocalDateTime.now()
    var modified : LocalDateTime ? = null
    @Version
    var version = 0L
}