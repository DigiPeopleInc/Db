package ru.digipeople.db.room

import android.arch.persistence.room.RoomDatabase
import ru.digipeople.db.DbTransaction

/**
 * Транзакция БД.
 *
 * @author Aleksandr Brazhkin
 */
class DbTransactionImpl(private val roomDb: RoomDatabase) : DbTransaction {
    override fun begin() {
        roomDb.beginTransaction()
    }

    override fun end() {
        roomDb.endTransaction()
    }

    override fun setSuccessful() {
        roomDb.setTransactionSuccessful()
    }

    override fun <T> callInTx(body: () -> T): T {
        return roomDb.runInTransaction(body)
    }
}