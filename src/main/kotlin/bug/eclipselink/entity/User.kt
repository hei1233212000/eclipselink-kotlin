package bug.eclipselink.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class User(
    @Id @GeneratedValue var id: Long? = null,
    val name: String
)
