package ru.digipeople.database.room.repository

import android.arch.persistence.db.SimpleSQLiteQuery
import android.arch.persistence.room.RoomDatabase
import ru.digipeople.database.model.ModelWithId
import ru.digipeople.database.repository.ModelRepository
import ru.digipeople.database.room.dao.BaseDao
import ru.digipeople.database.room.entity.EntityWithId
import ru.digipeople.database.room.mapper.BaseMapper

/**
 * Базовый репозиторий для моделей с Id.
 *
 * @author Aleksandr Brazhkin
 */
abstract class ModelRepositoryImpl<Model : ModelWithId<Id>, Entity : EntityWithId<Id>, Id>
protected constructor(roomDb: RoomDatabase) : RepositoryImpl(roomDb), ModelRepository<Model, Id> {

    open val tableName by lazy(LazyThreadSafetyMode.NONE) {
        val className = dao::class.java.simpleName
        className.substring(0, className.length - "Dao_Impl".length)
    }
    open val idColumnName = "id"
    abstract val dao: BaseDao<Entity>
    abstract val mapper: BaseMapper<Model, Entity>

    override fun getById(id: Id): Model? {
        val query = SimpleSQLiteQuery("SELECT * FROM $tableName WHERE $idColumnName = ?", arrayOf(id as Any))
        val entity = dao.getSingle(query)
        return mapper.entityToModel(entity)
    }

    override fun getAll(): List<Model> {
        val query = SimpleSQLiteQuery("SELECT * FROM $tableName")
        val entities = dao.getList(query)
        return mapper.entityListToModelList(entities)!!
    }

    override fun insert(model: Model) {
        val entity = mapper.modelToEntity(model)
        dao.insert(entity!!)
    }

    override fun insert(models: List<Model>) {
        val entities = mapper.modelListToEntityList(models)
        dao.insert(entities!!)
    }

    override fun update(model: Model) {
        dao.update(mapper.modelToEntity(model)!!)
    }

    override fun delete(model: Model) {
        dao.delete(mapper.modelToEntity(model)!!)
    }

    override fun delete(models: List<Model>) {
        val entities = mapper.modelListToEntityList(models)
        dao.delete(entities!!)
    }

    override fun deleteAll() {
        roomDb.openHelper.writableDatabase.execSQL("DELETE FROM $tableName")
    }

    override fun exists(id: Id): Boolean {
        val query = SimpleSQLiteQuery("SELECT EXISTS(SELECT * FROM $tableName WHERE $idColumnName = ?)", arrayOf(id as Any))
        return dao.getBoolean(query)
    }
}
