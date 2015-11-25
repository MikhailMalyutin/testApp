package test.client

import com.twitter.finagle.Thrift
import com.twitter.util.Await
import com.typesafe.scalalogging.slf4j.LazyLogging
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpec}
import test.TestService
import test.server.impl.config.Config

object TestClient extends WordSpec with Matchers with BeforeAndAfterAll with LazyLogging {
  val client = Thrift.newIface[TestService.FutureIface](Config.SERVER_URL)

  "Test client" must {
    "get records" in {
      val r = client.getRecords()
      val result = Await.result(r)
    }
  }

}
