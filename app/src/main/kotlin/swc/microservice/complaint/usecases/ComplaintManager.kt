package swc.microservice.complaint.usecases

import swc.microservice.complaint.entities.Complaint

fun getAllComplaints(manager: ComplaintManager): List<Complaint> = manager.getAllComplaints()

fun createComplaint(complaint: Complaint, manager: ComplaintManager) {
    manager.createComplaint(complaint)
}

fun deleteComplaint(complaintId: String): Nothing = TODO()

fun closeComplaint(complaintId: String): Nothing = TODO()

interface ComplaintManager {
    fun getAllComplaints(): List<Complaint>

    fun createComplaint(complaint: Complaint)

    fun deleteComplaint(complaintId: String): Nothing

    fun closeComplaint(complaintId: String): Nothing
}
