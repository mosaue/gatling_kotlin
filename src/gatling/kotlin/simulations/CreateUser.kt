package simulations

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import io.gatling.javaapi.core.*
import io.gatling.javaapi.core.CoreDsl.*
import io.gatling.javaapi.http.HttpDsl.*
import models.Account
import models.User
import org.apache.commons.lang3.RandomStringUtils
import java.io.FileWriter
import java.math.BigDecimal
import java.util.*

class CreateUser : Simulation() {

    val baseUrl = "13.48.58.189"

    private val httpProtocol =
        http.baseUrl("http://$baseUrl:8080/bank").disableFollowRedirect().inferHtmlResources().acceptHeader("*/*")
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

    val objectMapper: ObjectMapper =
        ObjectMapper().registerKotlinModule().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

    val scenario = scenario("Add User").exec(
        http("Auth").post("/api/v1/auth?password=Demo123!&username=admin@demo.io").headers(genericHeader).check(
            jsonPath("$.authToken").saveAs("authToken")
        )
    ).feed(feeder)
        .exec(
            http("Post new user").post("/api/v1/user?role=USER").headers(genericHeader)
                .header("Authorization", "Bearer \${authToken}")
                .body(StringBody { session -> objectMapper.writeValueAsString(session.get<User>("user")) }).check(
                    bodyString().saveAs("returnBody")
                ).check(
                    jsonPath("$.id").saveAs("userId")
                )
        ).exec { session ->
            println("!!!! ${session.getString("returnBody")}")
            println("!!!! ${session.getString("userId")}")
            session
        }.exec(
            http("Post new role").put("/api/v1/user/\${userId}/role?role=API").headers(genericHeader)
                .header("Authorization", "Bearer \${authToken}")
        ).exec { session ->
            val user = session.get<User>("user")
            val myWriter = FileWriter("${System.getProperty("user.dir")}/src/gatling/resources/testData.csv", true)
            myWriter.write("\n${user.emailAddress};${user.password}")
            myWriter.close()
            session
        }.exec(
            http("Auth User").post { session ->
                "/api/v1/auth?password=${session.get<User>("user").password}&username=${
                    session.get<User>(
                        "user"
                    ).emailAddress
                }"
            }.headers(genericHeader).check(
                jsonPath("$.authToken").saveAs("authTokenUser")
            )
        ).exec(
            http("Create acoount").post("/api/v1/user/account").headers(genericHeader).body(StringBody {
                objectMapper.writeValueAsString(
                    //Oppgave 8
                    Account()
                )
            }).header("Authorization", "Bearer \${authTokenUser}")
        )

    init {
        setUp(scenario.injectOpen(atOnceUsers(1))).protocols(httpProtocol)
    }

}