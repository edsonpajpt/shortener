package shortener.infrastructure.adapter.incoming.rest

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import shortener.infrastructure.adapter.incoming.rest.shorten.dto.ShortenerLinkRequestDto

@RestController
@RequestMapping("/test")
open class TestController{

    @PostMapping("/shorten")
    fun shortenLink(@RequestBody originalShortenerLinkRequest: ShortenerLinkRequestDto){
        println("Deu bom >> ${originalShortenerLinkRequest.originalLink
        }")

    }

    @GetMapping("/hello")
    fun hello(): String {
        return "Hello, Kotlin Spring!"
    }

}