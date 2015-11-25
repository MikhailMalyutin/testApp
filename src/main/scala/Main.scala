package com.twitter.finagle.example.thrift

import com.twitter.finagle.Thrift
import com.twitter.util.{Await, Future}
import test.server.impl.MainLogic

object ThriftServer {
  def main(args: Array[String]) {
    //#thriftserverapi
    val server = Thrift.serveIface("localhost:8080", MainLogic)
    Await.ready(server)
    print("runned")
    //val client = Thrift.client.
    //#thriftserverapi
  }
}
