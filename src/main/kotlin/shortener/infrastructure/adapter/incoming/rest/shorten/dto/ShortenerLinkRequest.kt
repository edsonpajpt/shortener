package shortener.infrastructure.adapter.incoming.rest.shorten.dto

data class ShortenerLinkRequest (
    val originalLink : String,
)