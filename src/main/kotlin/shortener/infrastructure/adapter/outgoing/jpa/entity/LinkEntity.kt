package shortener.infrastructure.adapter.outgoing.jpa.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import jakarta.persistence.Id
import java.time.LocalDateTime
import java.util.UUID


@Entity
@Table(name = "links")
class LinkEntity(

    @Id
    val id: UUID? = null,

    @Column(name = "original_link", nullable = false)
    var originalLink: String,

    @Column(name = "short_link")
    var shortLink: String? = "",

    @Column(name = "last_update", nullable = false)
    var lastUpdate: LocalDateTime

) {
    protected constructor() : this(originalLink = "", lastUpdate = LocalDateTime.now())

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is LinkEntity) return false
        return id != null && id == other.id
    }

    override fun hashCode(): Int = id?.hashCode() ?: 0
}
