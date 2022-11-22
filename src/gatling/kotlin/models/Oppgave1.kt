package models

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import org.apache.commons.lang3.RandomStringUtils
import java.io.FileWriter
import java.util.*
import kotlin.collections.Iterator

//Oppgave 1
data class User(
    val address: String,
    val country: String,
    val dob: String,
    val firstName: String,
    val gender: String,
    val homePhone: String,
    val lastName: String,
    val locality: String,
    val mobilePhone: String,
    val postalCode: String,
    val region: String,
    val ssn: String,
    val title: String,
    val workPhone: String,
    val emailAddress: String,
    val password: String
)

fun main() {
    val objectMapper =
        ObjectMapper().registerKotlinModule().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

    //Oppgave 2
    val feeder: Iterator<Map<String, User>> = generateSequence {
        val user = User(
            address = "string",
            country = "string",
            dob = "12/12/1990",
            firstName = "string",
            gender = "M",
            homePhone = "(111) 111-1111",
            lastName = "string",
            locality = "string",
            mobilePhone = "(111) 111-1111",
            postalCode = "string",
            region = "string",
            ssn = "${Random().nextInt(111, 999)}-${Random().nextInt(11, 99)}-${Random().nextInt(1111, 9999)}",
            title = "Mr.",
            workPhone = "(111) 111-1111",
            emailAddress = RandomStringUtils.randomAlphanumeric(20) + "@foo.com",
            password = "${RandomStringUtils.randomAlphabetic(16)}aB-${Random().nextInt(111, 999)}"
        )
        mapOf("user" to user)
    }.iterator()


    for (i in 0 until 10) {
        //Oppgave 3
        val user = feeder.next()["user"]!!

        println("Hello, my email is: ${user.emailAddress}")

        //Oppgave 4
        val myWriter = FileWriter("${System.getProperty("user.dir")}/src/gatling/resources/testData.csv", true)
        myWriter.write("${user.emailAddress},${user.password}\n")
        myWriter.close()

        //Oppgave 5
        println(objectMapper.writeValueAsString(user))
    }

}
