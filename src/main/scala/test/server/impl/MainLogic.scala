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
    RecordIdToTagIds += recordId -> (RecordIdToTagIds.getOrElseUpdate(recordId, Set[Int]()) + tag.id)
    TagIdRecordsIds += tag.id -> (TagIdRecordsIds.getOrElseUpdate(tag.id, Set[Int]()) + recordId)
    tag.id
  }

  override def getRecords(tags: Seq[Int]): Future[Seq[Record]] = Future {
    tags.flatMap(TagIdRecordsIds.get)
      .flatten
      .flatMap(IdsToRecords.get)
  }

  override def removeTag(tagId: Int, recordId: Int): Future[Unit] = Future {
    TagIdRecordsIds
      .get(tagId)
      .foreach(recordsForTag
          => TagIdRecordsIds += tagId -> (recordsForTag - recordId))
    RecordIdToTagIds
      .get(recordId)
      .foreach(tagsForRecord
          => RecordIdToTagIds += recordId -> (tagsForRecord - tagId))
  }

  override def getTags(recordId: Int): Future[Seq[Tag]] = Future {
    RecordIdToTagIds.get(recordId)
      .get
      .flatMap(IdsToTags.get)
      .toSeq
  }


}
