package swc.microservice.complaint.drivers.database

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import swc.microservice.complaint.drivers.database.DatabaseSerializer.deserializeComplaint
import swc.microservice.complaint.drivers.database.DatabaseSerializer.serializeComplaint
import swc.microservice.complaint.entities.Complaint
import swc.microservice.complaint.entities.ComplaintStatus
import swc.microservice.complaint.entities.Issuer

class SerializationTest : FreeSpec({
    val id = "myId"
    val ownerId = "myOwnerId"
    val title = "myTitle"
    val issuer = Issuer.USER
    val message = "myMessage"
    val status = ComplaintStatus.CLOSED
    val complaint = Complaint(id, ownerId, title, issuer, message, status)

    "The serializer and deserializer" - {
        "should handle a complaint" {
            val newComplaint = deserializeComplaint(serializeComplaint(complaint))
            newComplaint.id shouldBe id
            newComplaint.ownerId shouldBe ownerId
            newComplaint.title shouldBe title
            newComplaint.issuer shouldBe issuer
            newComplaint.message shouldBe message
            newComplaint.status shouldBe status
        }
    }
})
