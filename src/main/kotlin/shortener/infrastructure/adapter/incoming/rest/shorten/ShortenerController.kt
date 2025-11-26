package shortener.infrastructure.adapter.incoming.rest.shorten

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import shortener.application.shorten.ShortenCommand
import shortener.application.shorten.ShortenLinkUseCase
import shortener.infrastructure.adapter.incoming.rest.shorten.dto.ShortenerLinkRequest

@RestController
open class ShortenerController(
    private var useCase : ShortenLinkUseCase
){

    @PostMapping("/v1/shorten")
    fun shortenLink(@RequestBody shortenerLinkRequest: ShortenerLinkRequest){
        val command = ShortenCommand(originalLink = shortenerLinkRequest.originalLink)
        useCase.execute(command)
    }

}