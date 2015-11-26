package test.client

import com.twitter.finagle.Thrift
import com.twitter.util.Await
import com.typesafe.scalalogging.slf4j.LazyLogging
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpec}
import test.{Tag, Record, TestService}
import test.server.impl.config.Config

class TestClient extends WordSpec with Matchers with BeforeAndAfterAll with LazyLogging {
  val client = Thrift.newIface[TestService.FutureIface](Config.SERVER_URL)

  def setUp() = {
    Await.result(client.cleadAllData())
    Await.result(client.addRecord(Record.apply(1, "record 1")))
    Await.result(client.addRecord(Record.apply(2, "record 2")))
    Await.result(client.addRecord(Record.apply(3, "record 3")))
    Await.result(client.addRecord(Record.apply(4, "record 4")))
    Await.result(client.addRecord(Record.apply(5, "record 5")))
    Await.result(client.addRecord(Record.apply(6, "record 6")))
    Await.result(client.addRecord(Record.apply(7, "record 7")))
    Await.result(client.addRecord(Record.apply(8, "record 8")))
    Await.result(client.addRecord(Record.apply(9, "record 9")))
    Await.result(client.addTag(Tag.apply(11, "First tag"), 2))
    Await.result(client.addTag(Tag.apply(12, "Second tag"), 2))
  }

  "Test client" must {
    "add tag" in {
      setUp()
      val tag = Await.result(client.addTag(Tag.apply(11, "First tag"), 1))
      val afterCount = Await.result(client.getTags(1)).length
      afterCount shouldBe 1
    }

    "remove tag" in {
      setUp()
      val tag = Await.result(client.removeTag(11, 2))
      val afterCount = Await.result(client.getTags(2)).length
      afterCount shouldBe 1
    }

    "getRecords" in {
      setUp()
      val beforeCount = Await.result(client.getRecords(Seq(11))).length
      beforeCount shouldBe 1
    }

    "getTags" in {
      setUp()
      val beforeCount = Await.result(client.getTags(2)).length
      beforeCount shouldBe 2
    }
  }

}
