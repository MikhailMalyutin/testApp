package test.server.impl

import test.{Record, Tag, TestService}

object MainLogic extends TestService {
  override def addTag(tag: Tag): MM[Int] = {
    0
  }

  override def getRecords(tags: Seq[Int]): MM[Seq[Record]] = {
    Seq
  }

  override def removeTag(tagId: Int): MM[Unit] = {
  }

  override def getTags(recordId: Int): MM[Seq[Tag]] = {
    Seq
  }
}
