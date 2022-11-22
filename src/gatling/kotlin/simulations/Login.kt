package simulations

import io.gatling.javaapi.core.CoreDsl.*
import io.gatling.javaapi.core.Simulation
import io.gatling.javaapi.http.HttpDsl.http

class Login : Simulation() {

    private val httpProtocol = http
        .baseUrl("http://16.170.108.15:8080")
        .inferHtmlResources()
        .acceptHeader("*/*")
        .acceptEncodingHeader("gzip, deflate")
        .acceptLanguageHeader("nb-NO,nb;q=0.9,no;q=0.8,nn;q=0.7,en-US;q=0.6,en;q=0.5")
        .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/107.0.0.0 Safari/537.36")

    private val headers_0 = mapOf(
        "Accept" to "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9",
        "Proxy-Connection" to "keep-alive",
        "Upgrade-Insecure-Requests" to "1"
    )

    private val headers_25 = mapOf(
        "Accept" to "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9",
        "Cache-Control" to "max-age=0",
        "Origin" to "http://16.170.108.15:8080",
        "Proxy-Connection" to "keep-alive",
        "Upgrade-Insecure-Requests" to "1"
    )

    //Oppgave 2
    val userFeeder = TODO()

    private val scn = scenario("Login")
        .exec(
            http("request_0")
                .get("/bank")
                .headers(headers_0)
        )
        .pause(20)
        //Oppgave 3
        .exec(
            http("request_25")
                .post("/bank/login")
                .headers(headers_25)
                //Oppgave 3
                .formParam("username", "")
                .formParam("password", "")
        )
        .pause(16)
        .exec(
            http("request_57")
                .get("/bank/account/savings-view")
                .headers(headers_0)
        )

    init {
        setUp(scn.injectOpen(atOnceUsers(1))).protocols(httpProtocol)
    }
}
