package swc.microservice.complaint.api

import swc.microservice.complaint.drivers.database.DatabaseManager
import swc.microservice.complaint.usecases.ComplaintManager

object DefaultManager {
    fun get(): ComplaintManager = DatabaseManager()
}
