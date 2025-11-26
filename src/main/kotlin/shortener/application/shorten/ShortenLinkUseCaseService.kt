package shortener.application.shorten

import shortener.domain.model.Link
import shortener.infrastructure.adapter.outgoing.LinkRepositoryPort
import java.time.LocalDateTime
import java.util.UUID

class ShortenLinkUseCaseService(
    private val repository: LinkRepositoryPort
) : ShortenLinkUseCase {

    override fun execute(command: ShortenCommand) {
        val link = Link(
            id = UUID.randomUUID(),
            originalLink = command.originalLink,
            lastUpdate =  LocalDateTime.now()
        )
        link.shorten()
        repository.save(link)
    }

}