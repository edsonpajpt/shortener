package shortener.infrastructure.adapter.incoming.rest.shorten

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import shortener.application.shorten.ShortenCommand
import shortener.application.shorten.ShortenLinkUseCase
import shortener.infrastructure.adapter.incoming.rest.shorten.dto.ShortenerLinkRequestDto
import shortener.infrastructure.adapter.incoming.rest.shorten.dto.ShortenResponseDto
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

@RestController
open class ShortenerController(
    private var useCase : ShortenLinkUseCase
){

    @PostMapping("/v1/shorten")
    fun shortenLink(@RequestBody shortenerLinkRequest: ShortenerLinkRequestDto): ResponseEntity<ShortenResponseDto>{
        // Este controller está na camada de infraestrutura (entrada HTTP).
        // Ele recebe um DTO específico da API (`ShortenerLinkRequest`) e
        // mapeia para o comando da camada de aplicação (`ShortenCommand`).
        // Mantemos essa separação para que a camada de aplicação não dependa
        // de detalhes de transporte/serialização.
        // Basic URL validation
        try {
            java.net.URI(shortenerLinkRequest.originalLink)
        } catch (ex: Exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build()
        }

        // Mapeamento simples DTO -> Command
        val command = ShortenCommand(originalLink = shortenerLinkRequest.originalLink)
        val response = useCase.execute(command)

        // Mapeamento da resposta do caso de uso para o DTO de saída da API
        val dto = ShortenResponseDto(
            id = response.id,
            originalLink = response.originalLink,
            shortLink = response.shortLink
        )

        return ResponseEntity(dto, HttpStatus.CREATED)
    }

}