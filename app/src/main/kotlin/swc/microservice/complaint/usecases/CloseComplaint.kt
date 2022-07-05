package swc.microservice.complaint.usecases

import swc.microservice.complaint.entities.Complaint

class CloseComplaint(private val complaintId: String) : ComplaintUseCase<Complaint?> {
    override fun execute(complaintManager: ComplaintManager): Complaint? =
        complaintManager.closeComplaint(this.complaintId)
}
