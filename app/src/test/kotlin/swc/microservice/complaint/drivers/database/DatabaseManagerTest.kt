package swc.microservice.complaint.drivers.database

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import swc.microservice.complaint.entities.Complaint
import swc.microservice.complaint.entities.ComplaintStatus
import swc.microservice.complaint.entities.Issuer

class DatabaseManagerTest : FreeSpec({
    val manager = DatabaseManager(databaseName = "swc-test-${System.currentTimeMillis()}")
    val complaint = Complaint("myId", "myOwnerId", "myTitle", Issuer.USER, "myMessage")
    val otherComplaint = Complaint("otherId", "otherOwnerId", "otherTitle", Issuer.DUMPSTER, "otherMessage")

    "The DatabaseManager" - {
        "when creating a complaint" - {
            "should add it to the database" {
                manager.createComplaint(complaint) shouldBe complaint.id
                manager.createComplaint(otherComplaint) shouldBe otherComplaint.id
                manager.getAllComplaints().size shouldBe 2
            }
        }
        "when getting all complaints" - {
            "should retrieve all complaints from the database" {
                val complaints = manager.getAllComplaints()
                complaints.size shouldBe 2
                complaints.find { it.id == complaint.id } shouldNotBe null
                complaints.find { it.id == otherComplaint.id } shouldNotBe null
            }
        }
        "when closing a complaint" - {
            "should update the complaint in the database" {
                manager.closeComplaint(complaint.id)?.status shouldBe ComplaintStatus.CLOSED
                manager.getAllComplaints().find { it.id == complaint.id }?.status shouldBe ComplaintStatus.CLOSED
            }
        }
        "when deleting a complaint" - {
            "should remove the complaint from the database" {
                manager.deleteComplaint(complaint.id)?.id shouldBe complaint.id
                var complaints = manager.getAllComplaints()
                complaints.find { it.id == complaint.id } shouldBe null
                complaints.size shouldBe 1
                manager.deleteComplaint(otherComplaint.id)?.id shouldBe otherComplaint.id
                complaints = manager.getAllComplaints()
                complaints.find { it.id == otherComplaint.id } shouldBe null
                complaints.size shouldBe 0
            }
        }
    }
})
