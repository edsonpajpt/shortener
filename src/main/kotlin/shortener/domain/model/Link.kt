package shortener.domain.model

import java.time.LocalDateTime
import java.util.UUID

data class Link(
    val id: UUID,
    val originalLink: String,
    val shortLink: String? = "",
    val lastUpdate: LocalDateTime
)

