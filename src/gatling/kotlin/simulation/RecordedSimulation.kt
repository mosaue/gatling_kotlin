package simulation

import io.gatling.javaapi.core.*

import io.gatling.javaapi.core.CoreDsl.*
import io.gatling.javaapi.http.HttpDsl.*
import org.apache.commons.lang3.RandomStringUtils

class RecordedSimulation : Simulation() {

    /*val userFeeder = generateSequence {
        val email = "jsmith@demo.io"//RandomStringUtils.randomAlphanumeric(20) + "@foo.com"
        val pwd = "Demo123!"//RandomStringUtils.randomAlphabetic(16)
        mapOf(
            "email" to email,
            "pwd" to pwd
        )
    }.iterator()*/

    val userFeeder = separatedValues("testData.csv", ';').circular()

    private val httpProtocol = http
        .baseUrl("http://192.168.1.93:8080")
        .disableFollowRedirect()
        .inferHtmlResources()
        .acceptHeader("*/*")
        .acceptEncodingHeader("gzip, deflate")
        .acceptLanguageHeader("nb-NO,nb;q=0.9,no;q=0.8,nn;q=0.7,en-US;q=0.6,en;q=0.5")
        .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/105.0.0.0 Safari/537.36")

    private val headers_1 = mapOf(
        "Accept" to "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9",
        "Cache-Control" to "no-cache",
        "Pragma" to "no-cache",
        "Upgrade-Insecure-Requests" to "1"
    )

    private val headers_2 = mapOf(
        "Accept" to "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9",
        "Cache-Control" to "no-cache",
        "Origin" to "http://192.168.1.93:8080",
        "Pragma" to "no-cache",
        "Upgrade-Insecure-Requests" to "1"
    )

    private val scn = scenario("RecordedSimulation")
        .exec(
            http("request_10")
                .get("/bank")
                .headers(headers_1)
                .check(status().shouldBe(302))
                .resources(
                    http("request_11")
                        .get("/bank/")
                        .headers(headers_1)
                        .check(status().shouldBe(302)),
                    http("request_12")
                        .get("/bank/home")
                        .headers(headers_1)
                        .check(status().shouldBe(302)),
                    http("request_13")
                        .get("/bank/login")
                        .headers(headers_1)
                )
        )
        .exec(
            http("request_38")
                .get("/bank/login")
                .headers(headers_1)
        )
        .feed(userFeeder)
        .exec(
            http("request_65")
                .post("/bank/login")
                .headers(headers_2)
                .form { session ->
                    mapOf(
                        "username" to session.getString("user"),
                        "password" to session.getString("pwd")
                    )
                }
                .check(status().shouldBe(302))
        ).exec(
            http("request_66")
                .get("/bank/home")
                .headers(headers_1)
                .check(status().shouldBe(200))
        )
        /*.exec( //TODO: Create account
            http("request_134")
                .post("/bank/account/withdraw")
                .headers(headers_2)
                .formParam("id", "124")
                .formParam("amount", "25.00")
                .check(status().shouldBe(302))
                .resources(
                    http("request_135")
                        .get("/bank/account/savings-view?selectSwitch=124")
                        .headers(headers_1)
                        .check(status().shouldBe(200))
                )
        )*/

    init {
        setUp(scn.injectOpen(
            rampUsersPerSec(20.0).to(40.0).during(10),
            constantUsersPerSec(40.0).during(10)
        )).protocols(httpProtocol)
        //setUp(scn.injectOpen(atOnceUsers(5))).protocols(httpProtocol)
    }
}
