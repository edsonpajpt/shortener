package shortener.application.shorten

import org.springframework.stereotype.Service
import shortener.application.port.outgoing.LinkRepositoryPort
import shortener.infrastructure.adapter.incoming.rest.shorten.dto.LinkDto

@Service
class GetLinkByIdUseCaseService(
    private val repository: LinkRepositoryPort
) : GetLinkByIdUseCase {

    override fun execute(query: GetLinkByIdQuery): GetLinkByIdResponse {
        val link = repository.findById(query.id)

        val linkDto = link?.let {
            LinkDto(
                id = it.id,
                originalLink = it.originalLink,
                shortLink = it.shortLink ?: "",
                lastUpdate = it.lastUpdate
            )
        }

        return GetLinkByIdResponse(link = linkDto)
    }
}
