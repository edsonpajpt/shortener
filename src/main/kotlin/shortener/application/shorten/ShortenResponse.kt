package shortener.application.shorten

import java.util.UUID

data class ShortenResponse(
    val id: UUID,
    val originalLink: String,
    val shortLink: String
)
