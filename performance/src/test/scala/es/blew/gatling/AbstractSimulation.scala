package es.blew.gatling

/**
  * Created by wantez on 10/09/16.
  */
abstract class AbstractSimulation extends Simulation {

  val rampUpDurationSecs = 60
  val testDurationSecs = 90
  val users = 1000
  val requestName = baseName + "-request"
  val scenarioName = baseName + "-scenario"
  val baseURL = "http://localhost:9001"
  val scn = scenario(scenarioName)
    .during(testDurationSecs) {
      exec(
        http(requestName)
          .get(URI)
          .check(status.is(200))
      )
    }
  val httpConf = http.baseURL(baseURL);

  def baseName: String

  def URI: String

  setUp(scn.inject(rampUsers(users) over rampUpDurationSecs).protocols(httpConf))

}