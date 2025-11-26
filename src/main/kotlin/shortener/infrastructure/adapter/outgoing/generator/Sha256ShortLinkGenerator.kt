package shortener.infrastructure.adapter.outgoing.generator

import org.springframework.stereotype.Service
import shortener.domain.service.ShortLinkGenerator
import java.util.UUID

@Service
class Sha256ShortLinkGenerator : ShortLinkGenerator {
    override fun generate(originalLink: String, id: UUID): String {
        val digest = java.security.MessageDigest.getInstance("SHA-256")
        val hash = digest.digest((originalLink + id.toString()).toByteArray())
        val hex = hash.joinToString(separator = "") { String.format("%02x", it) }
        return hex.take(8)
    }
}
