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
)

fun main() {
    val objectMapper =
        ObjectMapper().registerKotlinModule().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

    //Oppgave 2
    val feeder: Iterator<Map<String, User>> = generateSequence {
        val user = TODO()
        mapOf("user" to user)
    }.iterator()


    for (i in 0 until 10) {
        //Oppgave 3
        val user = TODO()

        println("Hello, my email is: ")

        //Oppgave 4
        val myWriter = FileWriter("${System.getProperty("user.dir")}/src/gatling/resources/testData.csv", true)
        myWriter.write("")
        myWriter.close()

        //Oppgave 5
        println()
    }

}
