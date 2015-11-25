package test.server.impl

import com.twitter.util.Future
import test.{Record, Tag, TestService}

object MainLogic extends TestService[Future] {
  val IdsToTags = collection.mutable.Map[Int, Tag]()
  val IdsToRacords = collection.mutable.Map[Int, Record]()
  val RecordIdToTagIds = collection.mutable.Map[Int, Int]()
  val TagIdRecordsIds = collection.mutable.Map[Int, Int]()

  override def addTag(tag: Tag): Future[Int] = Future {
    tag.id
  }

  override def getRecords(tags: Seq[Int]): Future[Seq[Record]] = Future{
    Seq.empty
  }

  override def removeTag(tagId: Int): Future[Unit] = Future{
  }

  override def getTags(recordId: Int): Future[Seq[Tag]] = Future{
    Seq.empty
  }
}
