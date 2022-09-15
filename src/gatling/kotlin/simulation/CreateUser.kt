package simulation

import com.example.gatling.model.NewUser
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import io.gatling.javaapi.core.*

import io.gatling.javaapi.core.CoreDsl.*
import io.gatling.javaapi.http.HttpDsl.*
import org.apache.commons.lang3.RandomStringUtils
import java.io.FileWriter
import java.util.*

class CreateUser : Simulation() {

    private val httpProtocol = http
        .baseUrl("http://192.168.1.93:8080/bank")
        .disableFollowRedirect()
        .inferHtmlResources()
        .acceptHeader("*/*")
        .acceptEncodingHeader("gzip, deflate")
        .acceptLanguageHeader("nb-NO,nb;q=0.9,no;q=0.8,nn;q=0.7,en-US;q=0.6,en;q=0.5")
        .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/105.0.0.0 Safari/537.36")

    private val genericHeader = mapOf(
        "Accept" to "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9",
        "Cache-Control" to "no-cache",
        "Pragma" to "no-cache",
        "Upgrade-Insecure-Requests" to "1",
        "Content-type" to "application/json"
    )

    fun user() = NewUser(
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

    val objectMapper = ObjectMapper()
        .registerKotlinModule()
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

    val scenario = scenario("Add User")
        .exec(
            http("Post new user")
                .post("/api/v1/auth?password=Demo123!&username=admin@demo.io")
                .headers(genericHeader)
                .check(
                    jsonPath("$.authToken").saveAs("authToken")
                )
        )
        .exec { session ->
            val newSession = session.set(
                "userBody", user()
            )
            newSession
        }
        .exec(
            http("Post new user")
                .post("/api/v1/user?role=USER")
                .headers(genericHeader)
                .header("Authorization", "Bearer \${authToken}")
                .body(StringBody { session -> objectMapper.writeValueAsString(session.get<NewUser>("userBody")) })
        ).exec { session ->
            val user = session.get<NewUser>("userBody")
            val myWriter = FileWriter("${System.getProperty("user.dir")}/src/gatling/resources/testData.csv", true)
            myWriter.write("\n${user.emailAddress};${user.password}")
            myWriter.close()
            session
        }

    init {
        //setUp(scenario.injectOpen(constantUsersPerSec(20.0).during(15))).protocols(httpProtocol)
        setUp(scenario.injectOpen(atOnceUsers(1))).protocols(httpProtocol)
    }

}