package swc.microservice.complaint.usecases

import swc.microservice.complaint.entities.Complaint

class GetAllComplaints : ComplaintUseCase<List<Complaint>> {
    override fun execute(complaintManager: ComplaintManager): List<Complaint> =
        complaintManager.getAllComplaints()
}
