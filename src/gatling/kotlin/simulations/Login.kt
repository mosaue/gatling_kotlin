package simulations

import java.time.Duration

import io.gatling.javaapi.core.*
import io.gatling.javaapi.http.*
import io.gatling.javaapi.jdbc.*

import io.gatling.javaapi.core.CoreDsl.*
import io.gatling.javaapi.http.HttpDsl.*
import io.gatling.javaapi.jdbc.JdbcDsl.*

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
  
  private val headers_1 = mapOf(
    "Accept" to "text/css,*/*;q=0.1",
    "Proxy-Connection" to "keep-alive"
  )
  
  private val headers_10 = mapOf("Proxy-Connection" to "keep-alive")
  
  private val headers_19 = mapOf(
    "Accept" to "image/avif,image/webp,image/apng,image/svg+xml,image/*,*/*;q=0.8",
    "Proxy-Connection" to "keep-alive"
  )
  
  private val headers_25 = mapOf(
    "Accept" to "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9",
    "Cache-Control" to "max-age=0",
    "Origin" to "http://16.170.108.15:8080",
    "Proxy-Connection" to "keep-alive",
    "Upgrade-Insecure-Requests" to "1"
  )
  
  private val headers_49 = mapOf(
    "Origin" to "http://16.170.108.15:8080",
    "Proxy-Connection" to "keep-alive"
  )


  private val scn = scenario("Login")
    .exec(
      http("request_0")
        .get("/bank")
        .headers(headers_0)
        .resources(
          http("request_1")
            .get("/bank/css/normalize.css")
            .headers(headers_1),
          http("request_2")
            .get("/bank/css/cs-skin-elastic.css")
            .headers(headers_1),
          http("request_3")
            .get("/bank/css/font-awesome.min.css")
            .headers(headers_1),
          http("request_4")
            .get("/bank/css/themify-icons.css")
            .headers(headers_1),
          http("request_5")
            .get("/bank/scss/style.css")
            .headers(headers_1),
          http("request_6")
            .get("/bank/css/flag-icon.min.css")
            .headers(headers_1),
          http("request_7")
            .get("/bank/css/bootstrap.min.css")
            .headers(headers_1),
          http("request_8")
            .get("/bank/css/google-fonts-opensans.css")
            .headers(headers_1),
          http("request_9")
            .get("/bank/css/jquery-ui.css")
            .headers(headers_1),
          http("request_10")
            .get("/bank/js/plugins.js")
            .headers(headers_10),
          http("request_11")
            .get("/bank/js/popper.min.js")
            .headers(headers_10),
          http("request_12")
            .get("/bank/css/animate.css")
            .headers(headers_1),
          http("request_13")
            .get("/bank/js/vendor/jquery-1.12.4.js")
            .headers(headers_10),
          http("request_14")
            .get("/bank/js/vendor/jquery-2.1.4.min.js")
            .headers(headers_10),
          http("request_15")
            .get("/bank/js/dashboard.js")
            .headers(headers_10),
          http("request_16")
            .get("/bank/js/main.js")
            .headers(headers_10),
          http("request_17")
            .get("/bank/js/lib/vector-map/jquery.vmap.js")
            .headers(headers_10),
          http("request_18")
            .get("/bank/js/widgets.js")
            .headers(headers_10),
          http("request_19")
            .get("/bank/images/logo.png")
            .headers(headers_19),
          http("request_20")
            .get("/bank/js/lib/vector-map/jquery.vmap.min.js")
            .headers(headers_10),
          http("request_21")
            .get("/bank/js/lib/vector-map/country/jquery.vmap.world.js")
            .headers(headers_10),
          http("request_22")
            .get("/bank/js/lib/vector-map/jquery.vmap.sampledata.js")
            .headers(headers_10),
          http("request_23")
            .get("/bank/js/lib/chart-js/Chart.bundle.js")
            .headers(headers_10),
          http("request_24")
            .get("/bank/js/vendor/jquery-ui.js")
            .headers(headers_10)
        )
    )
    .pause(20)
    .exec(
      http("request_25")
        .post("/bank/login")
        .headers(headers_25)
        .formParam("username", "jsmith@demo.io")
        .formParam("password", "Demo123!")
        .resources(
          http("request_26")
            .get("/bank/css/normalize.css")
            .headers(headers_1),
          http("request_27")
            .get("/bank/css/cs-skin-elastic.css")
            .headers(headers_1),
          http("request_28")
            .get("/bank/css/font-awesome.min.css")
            .headers(headers_1),
          http("request_29")
            .get("/bank/css/themify-icons.css")
            .headers(headers_1),
          http("request_30")
            .get("/bank/css/flag-icon.min.css")
            .headers(headers_1),
          http("request_31")
            .get("/bank/css/google-fonts-opensans.css")
            .headers(headers_1),
          http("request_32")
            .get("/bank/css/jquery-ui.css")
            .headers(headers_1),
          http("request_33")
            .get("/bank/css/bootstrap.min.css")
            .headers(headers_1),
          http("request_34")
            .get("/bank/scss/style.css")
            .headers(headers_1),
          http("request_35")
            .get("/bank/js/popper.min.js")
            .headers(headers_10),
          http("request_36")
            .get("/bank/js/vendor/jquery-2.1.4.min.js")
            .headers(headers_10),
          http("request_37")
            .get("/bank/css/animate.css")
            .headers(headers_1),
          http("request_38")
            .get("/bank/js/plugins.js")
            .headers(headers_10),
          http("request_39")
            .get("/bank/js/main.js")
            .headers(headers_10),
          http("request_40")
            .get("/bank/js/vendor/jquery-1.12.4.js")
            .headers(headers_10),
          http("request_41")
            .get("/bank/js/widgets.js")
            .headers(headers_10),
          http("request_42")
            .get("/bank/js/dashboard.js")
            .headers(headers_10),
          http("request_43")
            .get("/bank/js/lib/vector-map/jquery.vmap.js")
            .headers(headers_10),
          http("request_44")
            .get("/bank/js/lib/vector-map/jquery.vmap.min.js")
            .headers(headers_10),
          http("request_45")
            .get("/bank/images/logo.png")
            .headers(headers_19),
          http("request_46")
            .get("/bank/js/lib/vector-map/country/jquery.vmap.world.js")
            .headers(headers_10),
          http("request_47")
            .get("/bank/js/lib/vector-map/jquery.vmap.sampledata.js")
            .headers(headers_10),
          http("request_48")
            .get("/bank/js/vendor/jquery-ui.js")
            .headers(headers_10),
          http("request_49")
            .get("/bank/fonts/themify.woff?-fvbane")
            .headers(headers_49),
          http("request_50")
            .get("/bank/images/flags/4x3/us.svg")
            .headers(headers_19),
          http("request_51")
            .get("/bank/js/lib/chart-js/Chart.bundle.js")
            .headers(headers_10),
          http("request_52")
            .get("/bank/images/admin.jpg")
            .headers(headers_19),
          http("request_53")
            .get("/bank/fonts/fontawesome-webfont.woff2?v=4.7.0")
            .headers(headers_49),
          http("request_54")
            .get("/bank/images/logo2.png")
            .headers(headers_19),
          http("request_55")
            .get("/bank/favicon.ico")
            .headers(headers_19),
          http("request_56")
            .get("/bank/images/apple-icon.png")
            .headers(headers_19)
        )
    )
    .pause(16)
    .exec(
      http("request_57")
        .get("/bank/account/savings-view")
        .headers(headers_0)
        .resources(
          http("request_58")
            .get("/bank/css/normalize.css")
            .headers(headers_1),
          http("request_59")
            .get("/bank/css/cs-skin-elastic.css")
            .headers(headers_1),
          http("request_60")
            .get("/bank/css/themify-icons.css")
            .headers(headers_1),
          http("request_61")
            .get("/bank/scss/style.css")
            .headers(headers_1),
          http("request_62")
            .get("/bank/css/google-fonts-opensans.css")
            .headers(headers_1),
          http("request_63")
            .get("/bank/css/flag-icon.min.css")
            .headers(headers_1),
          http("request_64")
            .get("/bank/css/font-awesome.min.css")
            .headers(headers_1),
          http("request_65")
            .get("/bank/css/jquery-ui.css")
            .headers(headers_1),
          http("request_66")
            .get("/bank/css/animate.css")
            .headers(headers_1),
          http("request_67")
            .get("/bank/css/bootstrap.min.css")
            .headers(headers_1),
          http("request_68")
            .get("/bank/js/vendor/jquery-2.1.4.min.js")
            .headers(headers_10),
          http("request_69")
            .get("/bank/js/popper.min.js")
            .headers(headers_10),
          http("request_70")
            .get("/bank/js/main.js")
            .headers(headers_10),
          http("request_71")
            .get("/bank/js/vendor/jquery-1.12.4.js")
            .headers(headers_10),
          http("request_72")
            .get("/bank/js/plugins.js")
            .headers(headers_10),
          http("request_73")
            .get("/bank/js/dashboard.js")
            .headers(headers_10),
          http("request_74")
            .get("/bank/js/widgets.js")
            .headers(headers_10),
          http("request_75")
            .get("/bank/images/flags/4x3/us.svg")
            .headers(headers_19),
          http("request_76")
            .get("/bank/fonts/themify.woff?-fvbane")
            .headers(headers_49),
          http("request_77")
            .get("/bank/fonts/fontawesome-webfont.woff2?v=4.7.0")
            .headers(headers_49),
          http("request_78")
            .get("/bank/images/logo.png")
            .headers(headers_19),
          http("request_79")
            .get("/bank/js/vendor/jquery-ui.js")
            .headers(headers_10),
          http("request_80")
            .get("/bank/js/lib/chart-js/Chart.bundle.js")
            .headers(headers_10),
          http("request_81")
            .get("/bank/images/admin.jpg")
            .headers(headers_19),
          http("request_82")
            .get("/bank/js/lib/vector-map/jquery.vmap.min.js")
            .headers(headers_10),
          http("request_83")
            .get("/bank/js/lib/vector-map/jquery.vmap.js")
            .headers(headers_10),
          http("request_84")
            .get("/bank/js/lib/vector-map/jquery.vmap.sampledata.js")
            .headers(headers_10),
          http("request_85")
            .get("/bank/js/lib/vector-map/country/jquery.vmap.world.js")
            .headers(headers_10),
          http("request_86")
            .get("/bank/js/lib/data-table/datatables.min.js")
            .headers(headers_10),
          http("request_87")
            .get("/bank/js/lib/data-table/buttons.bootstrap.min.js")
            .headers(headers_10),
          http("request_88")
            .get("/bank/js/lib/data-table/dataTables.bootstrap.min.js")
            .headers(headers_10),
          http("request_89")
            .get("/bank/js/lib/data-table/jszip.min.js")
            .headers(headers_10),
          http("request_90")
            .get("/bank/js/lib/data-table/dataTables.buttons.min.js")
            .headers(headers_10),
          http("request_91")
            .get("/bank/js/lib/data-table/buttons.print.min.js")
            .headers(headers_10),
          http("request_92")
            .get("/bank/js/lib/data-table/buttons.html5.min.js")
            .headers(headers_10),
          http("request_93")
            .get("/bank/js/lib/data-table/datatables-init.js")
            .headers(headers_10),
          http("request_94")
            .get("/bank/js/lib/data-table/buttons.colVis.min.js")
            .headers(headers_10),
          http("request_95")
            .get("/bank/images/logo2.png")
            .headers(headers_19),
          http("request_96")
            .get("/bank/js/lib/data-table/pdfmake.min.js")
            .headers(headers_10),
          http("request_97")
            .get("/bank/favicon.ico")
            .headers(headers_19),
          http("request_98")
            .get("/bank/js/lib/data-table/vfs_fonts.js")
            .headers(headers_10)
        )
    )

  init {
	  setUp(scn.injectOpen(atOnceUsers(1))).protocols(httpProtocol)
  }
}
