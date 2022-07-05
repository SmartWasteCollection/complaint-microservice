package swc.microservice.complaint.drivers.database

import org.bson.BsonDocument
import org.bson.BsonElement
import org.bson.BsonString
import swc.microservice.complaint.entities.Complaint
import swc.microservice.complaint.entities.ComplaintStatus
import swc.microservice.complaint.entities.Issuer

object DatabaseSerializer {
    fun serializeComplaint(complaint: Complaint): BsonDocument = BsonDocument(
        listOf(
            BsonElement("id", BsonString(complaint.id)),
            BsonElement("ownerId", BsonString(complaint.ownerId)),
            BsonElement("title", BsonString(complaint.title)),
            BsonElement("issuer", BsonString(complaint.issuer.toString())),
            BsonElement("message", BsonString(complaint.message)),
            BsonElement("status", BsonString(complaint.status.toString())),
        )
    )

    fun deserializeComplaint(document: BsonDocument): Complaint {
        return Complaint(
            (document["id"] as BsonString).value,
            (document["ownerId"] as BsonString).value,
            (document["title"] as BsonString).value,
            (document["issuer"] as BsonString).value.toIssuer(),
            (document["message"] as BsonString).value,
            (document["status"] as BsonString).value.toStatus(),
        )
    }

    private fun String.toIssuer(): Issuer = when (this) {
        "USER" -> Issuer.USER
        else -> Issuer.DUMPSTER
    }

    private fun String.toStatus(): ComplaintStatus = when (this) {
        "CLOSED" -> ComplaintStatus.CLOSED
        else -> ComplaintStatus.OPEN
    }
}
