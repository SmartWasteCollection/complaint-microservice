package swc.microservice.complaint.usecases

interface ComplaintUseCase<T> {
    fun execute(complaintManager: ComplaintManager): T
}
