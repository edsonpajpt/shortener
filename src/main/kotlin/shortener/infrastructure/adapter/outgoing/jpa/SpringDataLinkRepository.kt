package shortener.infrastructure.adapter.outgoing.jpa

import org.springframework.data.jpa.repository.JpaRepository
import shortener.infrastructure.adapter.outgoing.jpa.entity.LinkEntity
import java.util.UUID

interface SpringDataLinkRepository  : JpaRepository<LinkEntity, UUID> {
}