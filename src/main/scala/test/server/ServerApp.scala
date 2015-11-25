package test.server

import com.twitter.finagle.Thrift
import com.twitter.util.Await
import test.server.impl.MainLogic
import test.server.impl.config.Config

object ServerApp {
  def runServer(): Unit = {
    val server = Thrift.serveIface(Config.ServerUrl, MainLogic)
    print("Server started")
    Await.ready(server)
    print("Server stopped")
  }

}
