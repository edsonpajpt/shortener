# Shortener

**Visão rápida**
- **Propósito:** serviço simples para encurtamento de URLs.
- **Estrutura:** organizada por camadas seguindo variações de Clean/Hexagonal Architecture.

**Convenção de camadas**
- **`application` (casos de uso):** contém contratos e modelos de negócio (ex.: `ShortenCommand`, `ShortenResponse`). Camada independente de transporte.
- **`infrastructure` (adaptadores):** contém DTOs e controladores HTTP. DTOs representam o formato público da API e isolam detalhes de serialização/validação.

**Por que usar DTOs em `infrastructure`?**
- **Isolamento:** mantém a camada de aplicação livre de dependências de transporte (JSON, validações HTTP, anotações). 
- **Flexibilidade:** permite alterar o formato público da API sem tocar na lógica de negócio.

**Exemplos no projeto**
- **Request DTO:** `src/main/kotlin/shortener/infrastructure/adapter/incoming/rest/shorten/dto/ShortenerLinkRequest.kt` — DTO usado apenas pela camada HTTP; o controller faz o mapeamento para `ShortenCommand`.
- **Command (application):** `src/main/kotlin/shortener/application/shorten/ShortenCommand.kt` — contrato do caso de uso.
- **Response (application):** `src/main/kotlin/shortener/application/shorten/ShortenResponse.kt` — modelo interno retornado pelo caso de uso.
- **Response DTO (infrastructure):** `src/main/kotlin/shortener/infrastructure/adapter/incoming/rest/shorten/dto/ShortenResponseDto.kt` — mapeado a partir de `ShortenResponse` antes de serializar para o cliente.

**Onde mapear**
- Faça o mapeamento `DTO -> Command` no controller (ex.: `ShortenerController`).
- Faça o mapeamento `Response -> DTO` no controller antes de retornar ao cliente.

**Comandos úteis**
- Rodar testes: `./gradlew test`

Se quiser, crio um guia mais detalhado com exemplos de mapeamento automático (MapStruct ou Kotlin extensions) ou adiciono essas regras no `CONTRIBUTING.md`.
