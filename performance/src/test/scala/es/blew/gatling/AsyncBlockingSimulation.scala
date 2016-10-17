package es.blew.gatling

class AsyncBlockingSimulation extends AbstractSimulation {
  override def baseName: String = "async-blocking"

  override def URI: String = "/async-blocking"
}
