package bug.eclipselink

import bug.eclipselink.entity.User
import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.`should not be`
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature
import javax.persistence.EntityManager
import javax.persistence.Persistence

object EclipselinkFeature: Spek({
    Feature("Eclipselink") {
        Scenario("Save an entity") {
            lateinit var entityManager: EntityManager
            var userId: Long = -1

            Given("EntityManager is reade") {
                val entityManagerFactory = Persistence.createEntityManagerFactory("my-app")
                entityManager = entityManagerFactory.createEntityManager()
            }

            When("Persist an User") {
                entityManager.transaction.begin()
                val user = User(
                    name = "Peter"
                )
                entityManager.persist(user)
                entityManager.flush()
                entityManager.transaction.commit()

                userId = user.id!!
            }

            Then("User record is created in DB") {
                entityManager.transaction.begin()
                val user = entityManager.find(User::class.java, userId)
                entityManager.transaction.commit()

                user `should not be` null
                user.name `should be equal to` "Peter"
            }
        }
    }
})
