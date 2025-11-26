package shortener.application.shorten

import org.springframework.stereotype.Service
import shortener.domain.model.Link
import shortener.application.port.outgoing.LinkRepositoryPort
import shortener.domain.service.ShortLinkGenerator
import java.time.LocalDateTime
import java.util.UUID

@Service
class ShortenLinkUseCaseService(
    private val repository: LinkRepositoryPort,
    private val generator: ShortLinkGenerator
) : ShortenLinkUseCase {

    override fun execute(command: ShortenCommand): ShortenResponse {
        val id = UUID.randomUUID()
        val short = generator.generate(command.originalLink, id)

        val link = Link(
            id = id,
            originalLink = command.originalLink,
            shortLink = short,
            lastUpdate =  LocalDateTime.now()
        )

        repository.save(link)

        return ShortenResponse(
            id = link.id,
            originalLink = link.originalLink,
            shortLink = link.shortLink ?: ""
        )
    }

}