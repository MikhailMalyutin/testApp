package test.server.impl

import com.twitter.util.Future
import test.{Record, Tag, TestService}

object MainLogic extends TestService[Future] {
  val IdsToTags = collection.mutable.Map[Int, Tag]()
  val IdsToRecords = collection.mutable.Map[Int, Record]()
  val RecordIdToTagIds = collection.mutable.Map[Int, Set[Int]]()
  val TagIdRecordsIds = collection.mutable.Map[Int, Set[Int]]()

  override def addRecord(record: Record): Future[Int] = Future {
    IdsToRecords += record.id -> record
    record.id
  }

  override def cleadAllData(): Future[Unit] = Future {
    IdsToTags.clear()
    IdsToRecords.clear()
    RecordIdToTagIds.clear()
    TagIdRecordsIds.clear()
  }

  override def addTag(tag: test.Tag, recordId: Int) = Future {
    IdsToTags += tag.id -> tag
    RecordIdToTagIds.getOrElseUpdate(recordId, Set[Int]())+tag.id
    TagIdRecordsIds.getOrElseUpdate(recordId, Set[Int]())+recordId
    tag.id
  }

  override def getRecords(tags: Seq[Int]): Future[Seq[Record]] = Future{
      val some = tags.flatMap(TagIdRecordsIds.get).flatten
      some.map{recId => IdsToRecords.get(recId)}.map{case Some(record) => record}
  }

  override def removeTag(tagId: Int, recordId: Int): Future[Unit] = Future{
    //val recordsForTag = TagIdRecordsIds.get(tagId).map(case Some())
    //recordsForTag.re
  }

  override def getTags(recordId: Int): Future[Seq[Tag]] = Future{
    //IdsToRecords.get(recordId).map()
    Seq.empty
  }


}
