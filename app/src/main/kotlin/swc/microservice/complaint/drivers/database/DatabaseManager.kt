package swc.microservice.complaint.drivers.database

import com.mongodb.ConnectionString
import com.mongodb.client.MongoCollection
import io.github.cdimascio.dotenv.dotenv
import org.litote.kmongo.KMongo
import org.litote.kmongo.eq
import org.litote.kmongo.getCollectionOfName
import org.litote.kmongo.setValue
import swc.microservice.complaint.drivers.database.DatabaseConstants.COLLECTION
import swc.microservice.complaint.drivers.database.DatabaseConstants.CONNECTION_STRING
import swc.microservice.complaint.drivers.database.DatabaseConstants.DATABASE
import swc.microservice.complaint.entities.Complaint
import swc.microservice.complaint.entities.ComplaintStatus
import swc.microservice.complaint.usecases.ComplaintManager

private object DatabaseConstants {
    val dotenv = dotenv {
        ignoreIfMissing = true
    }
    val CONNECTION_STRING: String = dotenv["MONGO_CONNECTION_STRING"]
    const val DATABASE: String = "smart-waste-collection"
    const val COLLECTION: String = "complaints"
}

class DatabaseManager(
    private val connectionString: String = CONNECTION_STRING,
    private val databaseName: String = DATABASE,
    private val collectionName: String = COLLECTION
) : ComplaintManager {
    private val collection: MongoCollection<Complaint>

    init {
        val mongoClient = KMongo.createClient(ConnectionString(this.connectionString))
        val database = mongoClient.getDatabase(this.databaseName)
        this.collection = database.getCollectionOfName(this.collectionName)
    }

    override fun getAllComplaints(): List<Complaint> =
        this.collection.find().toList()

    override fun createComplaint(complaint: Complaint): String {
        this.collection.insertOne(complaint)
        return complaint.id
    }

    override fun deleteComplaint(complaintId: String): Complaint? =
        this.collection.find(Complaint::id eq complaintId).first().also {
            this.collection.deleteOne(Complaint::id eq complaintId)
        }

    override fun closeComplaint(complaintId: String): Complaint? {
        this.collection.updateOne(Complaint::id eq complaintId, setValue(Complaint::status, ComplaintStatus.CLOSED))
        return this.collection.find(Complaint::id eq complaintId).first()
    }
}
