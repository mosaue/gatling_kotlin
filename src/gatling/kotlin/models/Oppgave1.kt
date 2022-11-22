package models

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import org.apache.commons.lang3.RandomStringUtils
import java.io.FileWriter

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
    //TODO: Fyll inn resten av feltene
)

fun main() {
    //Oppgave 2
    val feeder: Map<String, User> = TODO("Fullfør sekvensgeneratoren")

    //Oppgave 3
    for (i in 0 until 10) {
        println()
    }

    //Oppgave 4
    for (i in 0 until 10) {
        val myWriter = FileWriter("${System.getProperty("user.dir")}/src/gatling/resources/testData.csv", true)
        myWriter.write("")
        myWriter.close()
    }

    //Oppgave 5
    val objectMapper =
        ObjectMapper().registerKotlinModule().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

    for (i in 0 until 10) {
        println()
    }

}
