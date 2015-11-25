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
   i32 addTag(Tag tag),
   void removeTag(i32 tagId),
   list<Record> getRecords(list<i32> tags),
   list<Tag> getTags(i32 recordId)
}