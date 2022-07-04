package swc.microservice.complaint.entities

data class Complaint(
    val ownerId: String,
    val issuer: Issuer,
    val issue: String,
    val status: ComplaintStatus = ComplaintStatus.OPEN
)
