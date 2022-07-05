package swc.microservice.complaint.usecases

import swc.microservice.complaint.entities.Complaint

class CreateComplaint(private val complaint: Complaint) : ComplaintUseCase<String> {
    override fun execute(complaintManager: ComplaintManager): String =
        complaintManager.createComplaint(this.complaint)
}
