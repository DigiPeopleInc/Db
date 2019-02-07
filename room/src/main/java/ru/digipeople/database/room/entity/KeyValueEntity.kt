package ru.digipeople.database.room.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.PrimaryKey

/**
 * @author Aleksandr Brazhkin
 */
abstract class KeyValueEntity {
    /**
     * Ключ
     */
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id = ""
    /**
     * Значение
     */
    @ColumnInfo(name = "value")
    var value: String? = null
}