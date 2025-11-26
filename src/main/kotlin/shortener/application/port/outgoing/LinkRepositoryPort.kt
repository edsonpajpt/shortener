package shortener.application.port.outgoing

import shortener.domain.model.Link
import java.util.UUID

interface LinkRepositoryPort {
    fun save(link: Link)
    fun findById(id: UUID) : Link?
}
