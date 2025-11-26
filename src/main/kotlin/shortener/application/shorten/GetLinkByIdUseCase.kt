package shortener.application.shorten

interface GetLinkByIdUseCase {
    fun execute(query: GetLinkByIdQuery): GetLinkByIdResponse
}
