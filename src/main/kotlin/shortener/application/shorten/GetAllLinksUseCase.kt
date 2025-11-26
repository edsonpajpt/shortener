package shortener.application.shorten

interface GetAllLinksUseCase {
    fun execute(query: GetAllLinksQuery): GetAllLinksResponse
}
