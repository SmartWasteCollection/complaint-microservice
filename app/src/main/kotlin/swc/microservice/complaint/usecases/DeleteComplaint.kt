package swc.microservice.complaint.usecases

import swc.microservice.complaint.entities.Complaint

class DeleteComplaint(private val complaintId: String) : ComplaintUseCase<Complaint> {
    override fun execute(complaintManager: ComplaintManager): Complaint =
        complaintManager.deleteComplaint(this.complaintId)
}
