package es.blew.gatling

class AsyncNonBlockingSimulation extends AbstractSimulation {
  override def baseName: String = "async-nonblocking"

  override def URI: String = "/async-nonblocking"
}
