package shortener.application.shorten

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import shortener.domain.model.Link
import shortener.application.port.outgoing.LinkRepositoryPort
import java.time.LocalDateTime
import java.util.UUID

class ShortenLinkUseCaseServiceTest {

    private class InMemoryRepo : LinkRepositoryPort {
        val storage = mutableMapOf<UUID, Link>()
        override fun save(link: Link) {
            storage[link.id] = link
        }

        override fun findById(id: UUID): Link? = storage[id]

        override fun findAll(): List<Link> = storage.values.toList()
    }

    private class InMemoryGenerator : shortener.domain.service.ShortLinkGenerator {
        override fun generate(originalLink: String, id: UUID): String {
            return "gen-${id.toString().take(8)}"
        }
    }

    @Test
    fun `execute should generate shortLink and save`() {
        val repo = InMemoryRepo()
        val generator = InMemoryGenerator()
        val service = ShortenLinkUseCaseService(repo, generator)

        val command = ShortenCommand(originalLink = "https://example.com/some/long/path")
        val response = service.execute(command)

        assertNotNull(response)
        assertEquals(command.originalLink, response.originalLink)
        assertNotNull(response.shortLink)

        val saved = repo.findById(response.id)
        assertNotNull(saved)
        assertEquals(response.shortLink, saved.shortLink)
    }
}
