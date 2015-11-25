package test.server.impl

import com.twitter.util.Future
import test.{Record, Tag, TestService}

object MainLogic extends TestService[Future] {
  val IdsToTags = collection.mutable.Map[Int, Tag]()
  val IdsToRacords = collection.mutable.Map[Int, Record]()
  val RecordIdToTagIds = collection.mutable.Map[Int, Int]()
  val TagIdRecordsIds = collection.mutable.Map[Int, Int]()

  override def addRecord(record: Record): Future[Int] = Future {
    IdsToRacords += record.id -> record
    record.id
  }

  override def addTag(tag: test.Tag, recordId: Int) = Future {
    IdsToTags += tag.id -> tag
    RecordIdToTagIds += recordId -> tag.id
    tag.id
  }

  override def getRecords(tags: Seq[Int]): Future[Seq[Record]] = Future{
      tags.map((tagId: Int) => TagIdRecordsIds.get(tagId))
        .map{case Some(recId) => IdsToRacords.get(recId)}
        .map{case Some(record) => record}
  }

  override def removeTag(tagId: Int, recordId: Int): Future[Unit] = Future{
  }

  override def getTags(recordId: Int): Future[Seq[Tag]] = Future{
    //IdsToRacords.get(recordId).foreach{case Some(recodt) => rec}
    Seq.empty
  }
}
