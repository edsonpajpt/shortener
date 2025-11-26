package shortener.application.shorten

import shortener.infrastructure.adapter.incoming.rest.shorten.dto.LinkDto

// Query para buscar todos os links
data class GetAllLinksQuery(
    val dummy: String = "" // placeholder para padrão consistente
)

// Response com lista de links
data class GetAllLinksResponse(
    val links: List<LinkDto>
)
