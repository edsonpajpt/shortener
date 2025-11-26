package shortener.infrastructure.adapter.incoming.rest.shorten.dto

import java.util.UUID

// DTO de saída usado pela camada de infraestrutura (HTTP).
// Mantém a forma pública da API separada do modelo interno da aplicação.
data class ShortenResponseDto(
    val id: UUID,
    val originalLink: String,
    val shortLink: String
)
