package com.twitter.finagle.example.thrift

import com.twitter.finagle.Thrift
import com.twitter.util.{Await, Future}
import test.TestService
import test.server.impl.MainLogic

object ThriftServer {
  def main(args: Array[String]) {
    //#thriftserverapi
    val server = Thrift.serveIface("localhost:8080", MainLogic)
    val client = Thrift.newIface[TestService.FutureIface]("localhost:8080")
    val r = client.getRecords()
    val result = Await.result(r)
    println(result.toString)
    print("test ok")
  //  Await.ready(server)
    print("runned")
    //val client = Thrift.client.
    //#thriftserverapi
  }
}
