package ru.digipeople.database.room.repository

import android.arch.persistence.room.RoomDatabase
import ru.digipeople.database.repository.Repository

/**
 * Репозиторий.
 *
 * @author Aleksandr Brazhkin
 */
abstract class RepositoryImpl(protected val roomDb: RoomDatabase) : Repository {

    protected fun beginTransaction() {
        roomDb.beginTransaction()
    }

    protected fun endTransaction() {
        roomDb.endTransaction()
    }

    protected fun setTransactionSuccessful() {
        roomDb.setTransactionSuccessful()
    }
}