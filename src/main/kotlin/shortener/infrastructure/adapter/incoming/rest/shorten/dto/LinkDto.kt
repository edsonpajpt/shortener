package shortener.infrastructure.adapter.incoming.rest.shorten.dto

import java.time.LocalDateTime
import java.util.UUID

// DTO para retornar informações de um link
data class LinkDto(
    val id: UUID,
    val originalLink: String,
    val shortLink: String,
    val lastUpdate: LocalDateTime
)
