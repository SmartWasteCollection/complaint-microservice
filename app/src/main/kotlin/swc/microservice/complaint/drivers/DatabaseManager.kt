package swc.microservice.complaint.drivers

import com.mongodb.MongoClient
import com.mongodb.client.MongoCollection
import org.bson.BsonDocument
import org.bson.BsonElement
import org.bson.BsonString
import swc.microservice.complaint.entities.Complaint
import swc.microservice.complaint.entities.ComplaintStatus
import swc.microservice.complaint.entities.Issuer
import swc.microservice.complaint.usecases.ComplaintManager

class DatabaseManager : ComplaintManager {
    private val collection: MongoCollection<BsonDocument>

    init {
        val mongoClient = MongoClient()
        val database = mongoClient.getDatabase("smart-waste-collection")
        this.collection = database.getCollection("complaints", BsonDocument::class.java)
    }

    override fun getAllComplaints(): List<Complaint> {
        TODO()
    }

    override fun createComplaint(complaint: Complaint): String {
        val obj: BsonDocument = BsonDocument(
            listOf(
                BsonElement("id", BsonString("0")),
                BsonElement("ownerId", BsonString("owner")),
                BsonElement("title", BsonString("myTitle")),
                BsonElement("issuer", BsonString(Issuer.USER.toString())),
                BsonElement("message", BsonString("myMessage")),
                BsonElement("status", BsonString(ComplaintStatus.OPEN.toString()))
            )
        )
        this.collection.insertOne(obj)
        return "id"
    }

    override fun deleteComplaint(complaintId: String): Nothing {
        TODO("Not yet implemented")
    }

    override fun closeComplaint(complaintId: String): Nothing {
        TODO("Not yet implemented")
    }
}
