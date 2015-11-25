namespace java test

struct Record {
 1: i32 id,
 2: string name
}

struct Tag {
 1: i32 id,
 2: string name
}

service TestService {
   i32 addRecord(Record record),

   i32 addTag(Tag tag, i32 recordId),
   void removeTag(i32 tagId, i32 recordId),
   list<Record> getRecords(list<i32> tags),
   list<Tag> getTags(i32 recordId)
}