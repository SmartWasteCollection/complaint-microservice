package swc.microservice.complaint

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class ComplaintMicroservice

fun main(args: Array<String>) {
    runApplication<ComplaintMicroservice>(*args)
}
