package shortener.domain.service

import java.util.UUID

interface ShortLinkGenerator {
    fun generate(originalLink: String, id: UUID): String
}
