package shortener.infrastructure.adapter.incoming.rest.shorten

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import shortener.application.shorten.ShortenCommand
import shortener.application.shorten.ShortenLinkUseCase
import shortener.infrastructure.adapter.incoming.rest.shorten.dto.ShortenerLinkRequest
import shortener.application.shorten.ShortenResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

@RestController
open class ShortenerController(
    private var useCase : ShortenLinkUseCase
){

    @PostMapping("/v1/shorten")
    fun shortenLink(@RequestBody shortenerLinkRequest: ShortenerLinkRequest): ResponseEntity<ShortenResponse>{
        // Basic URL validation
        try {
            java.net.URI(shortenerLinkRequest.originalLink)
        } catch (ex: Exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build()
        }

        val command = ShortenCommand(originalLink = shortenerLinkRequest.originalLink)
        val response = useCase.execute(command)
        return ResponseEntity(response, HttpStatus.CREATED)
    }

}