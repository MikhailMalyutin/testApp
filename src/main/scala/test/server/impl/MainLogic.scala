package test.server.impl

import com.twitter.util.{Await, Future}
import test.{Record, Tag, TestService}

object MainLogic extends TestService[Future] {
  override def addTag(tag: Tag): Future[Int] = Future.value(0)

  override def getRecords(tags: Seq[Int]): Future[Seq[Record]] = Future{Seq.empty}

  override def removeTag(tagId: Int): Future[Unit] = Future{
  }

  override def getTags(recordId: Int): Future[Seq[Tag]] = Future{
    Seq.empty
  }
}
