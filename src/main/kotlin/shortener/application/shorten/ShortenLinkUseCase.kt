package shortener.application.shorten

interface ShortenLinkUseCase {
    fun execute(command: ShortenCommand)
}