package swc.microservice.complaint.usecases

import swc.microservice.complaint.entities.Complaint

interface ComplaintManager {
    fun getAllComplaints(): List<Complaint>

    fun createComplaint(complaint: Complaint): String

    fun deleteComplaint(complaintId: String): Complaint?

    fun closeComplaint(complaintId: String): Complaint?
}
