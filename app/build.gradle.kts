plugins {
    id("org.jetbrains.kotlin.jvm") version "1.9.10"
    id("org.jlleitschuh.gradle.ktlint") version "11.5.1"
    id("org.jlleitschuh.gradle.ktlint-idea") version "11.5.1"
    id("org.springframework.boot") version "3.1.3"
    jacoco
    application
}

repositories {
    mavenCentral()
}

val kotestVersion: String by project

dependencies {
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("com.google.guava:guava:32.1.2-jre")
    implementation("org.litote.kmongo:kmongo:5.1.0")
    implementation("io.github.cdimascio:dotenv-kotlin:6.4.1")
    implementation("org.springframework.boot:spring-boot-starter-web:3.1.3")
    testImplementation("io.kotest:kotest-runner-junit5:$kotestVersion")
    testImplementation("io.kotest:kotest-assertions-core:$kotestVersion")
    testImplementation("io.kotest:kotest-property:$kotestVersion")
}

application {
    mainClass.set("swc.microservice.complaint.AppKt")
}

jacoco {
    toolVersion = "0.8.10"
}

tasks.jacocoTestReport {
    dependsOn(tasks.test)
    reports {
        xml.required.set(true)
    }
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}

tasks.test {
    finalizedBy(tasks.jacocoTestReport)
}
