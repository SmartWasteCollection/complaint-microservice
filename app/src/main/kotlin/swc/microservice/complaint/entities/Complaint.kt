package swc.microservice.complaint.entities

data class Complaint(
    val id: String,
    val ownerId: String,
    val title: String,
    val issuer: Issuer,
    val message: String,
    val status: ComplaintStatus = ComplaintStatus.OPEN
)
