package swc.microservice.complaint.api

import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import swc.microservice.complaint.entities.Complaint
import swc.microservice.complaint.usecases.CloseComplaint
import swc.microservice.complaint.usecases.ComplaintManager
import swc.microservice.complaint.usecases.CreateComplaint
import swc.microservice.complaint.usecases.DeleteComplaint
import swc.microservice.complaint.usecases.GetAllComplaints

@RestController
@CrossOrigin
@RequestMapping("/complaints")
class ComplaintControllers(private val manager: ComplaintManager = DefaultManager.get()) {
    @GetMapping
    fun getAllComplaints(): List<Complaint> = GetAllComplaints().execute(manager)

    @PostMapping
    fun createComplaint(@RequestBody complaint: Complaint): String = CreateComplaint(complaint).execute(manager)

    @DeleteMapping("/{complaintId}")
    fun deleteComplaint(@PathVariable complaintId: String): Complaint? = DeleteComplaint(complaintId).execute(manager)

    @PutMapping("/{complaintId}")
    fun closeComplaint(@PathVariable complaintId: String): Complaint? = CloseComplaint(complaintId).execute(manager)
}
