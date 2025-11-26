package shortener.infrastructure.adapter.outgoing.jpa

import org.springframework.stereotype.Repository
import shortener.domain.model.Link
import shortener.application.port.outgoing.LinkRepositoryPort
import shortener.infrastructure.adapter.outgoing.jpa.entity.LinkEntity
import java.util.UUID

@Repository
class LinkRepositoryJPA (
    private val springDataLinkRepository : SpringDataLinkRepository
) : LinkRepositoryPort {

    override fun save(link: Link) {
        val entity = LinkEntity(link.id, link.originalLink, link.shortLink, link.lastUpdate)
        springDataLinkRepository.save(entity);
    }

    override fun findById(id: UUID): Link? {
        val entity = springDataLinkRepository.findById(id).get();
        if(entity.id != null){
            return Link(id, entity.originalLink, entity.shortLink, entity.lastUpdate)
        }else{
            throw IllegalArgumentException("Id not found!")
        }
    }

    override fun findAll(): List<Link> {
        return springDataLinkRepository.findAll().map { entity ->
            Link(
                id = entity.id ?: throw IllegalArgumentException("Entity id cannot be null"),
                originalLink = entity.originalLink,
                shortLink = entity.shortLink,
                lastUpdate = entity.lastUpdate
            )
        }
    }
}