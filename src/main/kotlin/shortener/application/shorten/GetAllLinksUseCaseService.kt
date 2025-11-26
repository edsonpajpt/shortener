package shortener.application.shorten

import org.springframework.stereotype.Service
import shortener.application.port.outgoing.LinkRepositoryPort
import shortener.infrastructure.adapter.incoming.rest.shorten.dto.LinkDto

@Service
class GetAllLinksUseCaseService(
    private val repository: LinkRepositoryPort
) : GetAllLinksUseCase {

    override fun execute(query: GetAllLinksQuery): GetAllLinksResponse {
        val links = repository.findAll()
        
        val linkDtos = links.map { link ->
            LinkDto(
                id = link.id,
                originalLink = link.originalLink,
                shortLink = link.shortLink ?: "",
                lastUpdate = link.lastUpdate
            )
        }

        return GetAllLinksResponse(links = linkDtos)
    }
}
