# DbRepository
Repository pattern around database

Usage
--------
###### Example of domain model
```kotlin
class Book : ModelWithId<Long> {
    override var id = 0L
    var name = ""
}
```
###### Example of room entity
```kotlin
@Entity(tableName = "Book")
class BookEntity : EntityWithId<Long> {
    @PrimaryKey
    override var id = 0L
    var name = ""
}
```
###### Example of mapper Model <-> RoomEntity (Use [MapStruct](http://mapstruct.org/) as better solution)
```kotlin
class BookMapper : BaseMapper<Book, BookEntity> {
    override fun entityListToModelList(entities: List<BookEntity>?): List<Book>? {...}
    override fun entityToModel(entity: BookEntity?): Book? {...}
    override fun modelListToEntityList(models: List<Book>?): List<BookEntity>? {...}
    override fun modelToEntity(model: Book?): BookEntity? {...}
}

```
###### Example of RoomDatabase declaration
```kotlin
@Database(entities = [BookEntity::class])
abstract class AppDatabase : RoomDatabase() {
    abstract fun bookDao(): BookDao
}
```
###### Example of repository
```kotlin
class BookRepositoryImpl(appDatabase: AppDatabase)
    : ModelRepositoryWithLongId<Book, BookEntity>(appDatabase), BookRepository {
    override val dao = appDatabase.bookDao()
    override val mapper = BookMapper()
}
```


Download
------------
###### Room
```groovy
dependencies {
    implementation 'ru.digipeople.database:room:1.0.1'
}
```