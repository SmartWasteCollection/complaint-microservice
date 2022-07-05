package swc.microservice.complaint.entities

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class ComplaintTest : FreeSpec({
    val id: String = "id"
    val ownerId: String = "ownerId"
    val title: String = "title"
    val issuer: Issuer = Issuer.USER
    val message: String = "message"
    val complaint = Complaint(id, ownerId, title, issuer, message)

    "A complaint" - {
        "should give access to its fields" {
            complaint.id shouldBe id
            complaint.ownerId shouldBe ownerId
            complaint.title shouldBe title
            complaint.issuer shouldBe issuer
            complaint.message shouldBe message
            complaint.status shouldBe ComplaintStatus.OPEN
        }
    }
})
