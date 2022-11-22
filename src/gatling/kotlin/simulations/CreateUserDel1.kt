package simulations

import io.gatling.javaapi.core.CoreDsl.*
import io.gatling.javaapi.core.Simulation
import io.gatling.javaapi.http.HttpDsl.http

class CreateUserDel1 : Simulation() {

    //Oppgave 1
    val baseUrl = ""

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

    //Oppgave 2 + 3
    val scenario = scenario("Sett inn passende navn").exec(
        http("Auth")
    )

    init {
        setUp(scenario.injectOpen(atOnceUsers(1))).protocols(httpProtocol)
    }

}