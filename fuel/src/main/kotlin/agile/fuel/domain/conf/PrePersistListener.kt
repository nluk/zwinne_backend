package agile.fuel.domain.conf

import agile.fuel.domain.model.AuditableEntity
import org.slf4j.LoggerFactory
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent
import org.springframework.stereotype.Component
import java.util.*

@Component
class PrePersistListener : AbstractMongoEventListener<AuditableEntity>() {

    companion object{
        val log = LoggerFactory.getLogger(PrePersistListener::class.java)
    }

    override fun onBeforeConvert(event: BeforeConvertEvent<AuditableEntity>) {
        log.debug("Saving: ${event.document}")
        event.source.modified = Date()
        super.onBeforeConvert(event)
    }
}