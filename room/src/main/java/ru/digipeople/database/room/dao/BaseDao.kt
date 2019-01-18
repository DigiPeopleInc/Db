package ru.digipeople.database.room.dao

import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Update

/**
 * Базовый DAO.
 *
 * @author Aleksandr Brazhkin
 */
interface BaseDao<Entity> {
    /**
     * Вставляет [entity] в БД
     * @param entity Сущность
     */
    @Insert(onConflict = OnConflictStrategy.FAIL)
    fun insert(entity: Entity): Long

    /**
     * Вставляет список [entities] в БД
     * @param entities Список сущностей
     */
    @Insert(onConflict = OnConflictStrategy.FAIL)
    fun insert(entities: List<Entity>): LongArray

    /**
     * Обновляет [entity] в БД
     * @param entity Сущность
     */
    @Update(onConflict = OnConflictStrategy.FAIL)
    fun update(entity: Entity)

    /**
     * Удаляет [entity] из БД
     * @param entity Сущность
     */
    @Delete
    fun delete(entity: Entity)

    /**
     * Удаляет список [entities] из БД
     * @param entities Список сущностей
     */
    @Delete
    fun delete(entities: List<Entity>)
}