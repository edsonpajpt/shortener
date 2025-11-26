package shortener.application.shorten

import shortener.infrastructure.adapter.incoming.rest.shorten.dto.LinkDto
import java.util.UUID

// Query para buscar link por ID
data class GetLinkByIdQuery(
    val id: UUID
)

// Response com um link
data class GetLinkByIdResponse(
    val link: LinkDto?
)
