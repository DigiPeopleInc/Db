package ru.digipeople.database.room.repository

import android.arch.persistence.db.SimpleSQLiteQuery
import android.arch.persistence.room.RoomDatabase
import ru.digipeople.database.repository.KeyValueRepository
import ru.digipeople.database.room.dao.KeyValueDao

/**
 * @author Aleksandr Brazhkin
 */
abstract class KeyValueRepositoryImpl
protected constructor(roomDb: RoomDatabase) : RepositoryImpl(roomDb), KeyValueRepository {

    open val tableName by lazy(LazyThreadSafetyMode.NONE) {
        val className = dao::class.java.simpleName
        className.substring(0, className.length - "Dao_Impl".length)
    }
    abstract val dao: KeyValueDao

    override fun get(key: String): String? {
        val sql = "SELECT value FROM $tableName WHERE key = ?)"
        val query = SimpleSQLiteQuery(sql, arrayOf(key as Any))
        dao.getCursor(query).use { cursor ->
            if (cursor.moveToFirst()) {
                return cursor.getString(0)
            }
        }
        return null
    }

    override fun set(key: String, value: String?) {
        val sql = "INSERT or REPLACE INTO $tableName VALUES (?, ?)"
        roomDb.openHelper.writableDatabase.execSQL(sql, arrayOf<Any?>(key, value))
    }

    override fun load(): Map<String, String?> {
        val sql = "SELECT id, value FROM $tableName"
        val query = SimpleSQLiteQuery(sql)
        val map = HashMap<String, String>()
        dao.getCursor(query).use { cursor ->
            while (cursor.moveToNext()) {
                map[cursor.getString(0)] = cursor.getString(1)
            }
        }
        return map
    }

    override fun merge(map: Map<String, String?>) {
        beginTransaction()
        try {
            map.forEach { set(it.key, it.value) }
            setTransactionSuccessful()
        } finally {
            endTransaction()
        }
    }

    override fun rewrite(map: Map<String, String?>) {
        beginTransaction()
        try {
            deleteAll()
            merge(map)
            setTransactionSuccessful()
        } finally {
            endTransaction()
        }
    }

    override fun delete(key: String) {
        val sql = "DELETE FROM $tableName WHERE key = ?)"
        roomDb.openHelper.writableDatabase.execSQL(sql, arrayOf(key as Any))
    }

    override fun deleteAll() {
        val sql = "DELETE FROM $tableName"
        roomDb.openHelper.writableDatabase.execSQL(sql)
    }

    override fun exists(key: String): Boolean {
        val sql = "SELECT EXISTS(SELECT * FROM $tableName WHERE id = ?)"
        val query = SimpleSQLiteQuery(sql, arrayOf(key as Any))
        dao.getCursor(query).use { cursor ->
            if (cursor.moveToFirst()) {
                return cursor.getInt(0) == 1
            }
        }
        return false
    }
}