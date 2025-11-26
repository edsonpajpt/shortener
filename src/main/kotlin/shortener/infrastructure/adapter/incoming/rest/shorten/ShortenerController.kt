package shortener.infrastructure.adapter.incoming.rest.shorten

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import shortener.application.shorten.ShortenCommand
import shortener.application.shorten.ShortenLinkUseCase
import shortener.application.shorten.GetAllLinksUseCase
import shortener.application.shorten.GetAllLinksQuery
import shortener.application.shorten.GetLinkByIdUseCase
import shortener.application.shorten.GetLinkByIdQuery
import shortener.infrastructure.adapter.incoming.rest.shorten.dto.ShortenerLinkRequestDto
import shortener.infrastructure.adapter.incoming.rest.shorten.dto.ShortenResponseDto
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import java.util.UUID

@RestController
open class ShortenerController(
    private var useCase : ShortenLinkUseCase,
    private var getAllLinksUseCase: GetAllLinksUseCase,
    private var getLinkByIdUseCase: GetLinkByIdUseCase
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

    @GetMapping("/v1/links")
    fun getAllLinks(): ResponseEntity<Any> {
        val query = GetAllLinksQuery()
        val response = getAllLinksUseCase.execute(query)
        return ResponseEntity.ok(response.links)
    }

    @GetMapping("/v1/links/{id}")
    fun getLinkById(@PathVariable id: UUID): ResponseEntity<Any> {
        val query = GetLinkByIdQuery(id = id)
        val response = getLinkByIdUseCase.execute(query)
        
        return if (response.link != null) {
            ResponseEntity.ok(response.link)
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND).build()
        }
    }

}