package es.blew.gatling

class SyncSimulation extends AbstractSimulation {
  override def baseName: String = "sync"

  override def URI: String = "/sync"
}
