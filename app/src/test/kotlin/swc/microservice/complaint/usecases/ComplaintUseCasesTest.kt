package swc.microservice.complaint.usecases

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import swc.microservice.complaint.entities.Complaint
import swc.microservice.complaint.entities.ComplaintStatus
import swc.microservice.complaint.entities.Issuer

class ComplaintUseCasesTest : FreeSpec({
    var complaints: List<Complaint> = listOf()
    val complaintId: String = "id"

    fun newComplaint(status: ComplaintStatus = ComplaintStatus.OPEN): Complaint =
        Complaint(complaintId, "owner", "title", Issuer.USER, "message", status)

    val manager: ComplaintManager = object : ComplaintManager {
        override fun getAllComplaints(): List<Complaint> = complaints

        override fun createComplaint(complaint: Complaint): String {
            complaints = complaints + complaint
            return complaint.id
        }

        override fun deleteComplaint(complaintId: String): Complaint? {
            val complaint = complaints.find { it.id == complaintId }
            complaints = complaints.filter { it.id != complaintId }
            return complaint
        }

        override fun closeComplaint(complaintId: String): Complaint? {
            complaints = complaints.map {
                when (it.id) {
                    complaintId -> newComplaint(ComplaintStatus.CLOSED)
                    else -> it
                }
            }
            return complaints.find { it.id == complaintId }
        }
    }

    "When performing the complaint use cases" - {
        "The CreateComplaint use case" - {
            "should create a complaint" {
                val complaint = newComplaint()
                CreateComplaint(complaint).execute(manager)
                complaints.size shouldBe 1
            }
        }

        "The GetAllComplaints use case" - {
            "should return the complaints created" {
                GetAllComplaints().execute(manager).size shouldBe 1
            }
        }

        "The CloseComplaint use case" - {
            "should close the specified complaint" {
                val complaint = CloseComplaint(complaintId).execute(manager)
                complaint?.status shouldBe ComplaintStatus.CLOSED
                complaint?.id shouldBe complaintId
                complaints.find { it.id == complaintId }?.status shouldBe ComplaintStatus.CLOSED
                complaints.size shouldBe 1
            }
            "when the specified complaint is not present" - {
                "should return null" {
                    CloseComplaint("").execute(manager) shouldBe null
                }
            }
        }

        "The DeleteComplaint use case" - {
            "should delete the specified complaint" {
                val complaint = DeleteComplaint(complaintId).execute(manager)
                complaint?.id shouldBe complaintId
                complaints.find { it.id == complaintId } shouldBe null
                complaints.size shouldBe 0
            }
            "when the specified complaint is not present" - {
                "should return null" {
                    DeleteComplaint("").execute(manager) shouldBe null
                }
            }
        }
    }
})
