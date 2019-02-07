package ru.digipeople.database.room.dao

import android.arch.persistence.db.SupportSQLiteQuery
import android.arch.persistence.room.RawQuery
import android.database.Cursor

/**
 * @author Aleksandr Brazhkin
 */
interface KeyValueDao {
    /**
     * Возвращает курсор
     * @param query Запрос
     */
    @RawQuery
    fun getCursor(query: SupportSQLiteQuery): Cursor
}