package shortener.infrastructure.adapter.incoming.rest.shorten.dto

// DTO usado apenas pela camada de infraestrutura (entrada HTTP).
// Mantemos este tipo aqui para isolar detalhes de transporte (nomes de campos,
// anotações de serialização ou validações HTTP) da camada de aplicação.
// A camada de aplicação recebe um `ShortenCommand` (contrato do caso de uso),
// então o controller faz o mapeamento DTO -> Command.
data class ShortenerLinkRequestDto(
    val originalLink: String,
)
