package ru.digipeople.database.room.dao

import android.arch.persistence.db.SupportSQLiteQuery
import android.arch.persistence.room.*

/**
 * Базовый DAO.
 *
 * @author Aleksandr Brazhkin
 */
interface BaseDao<Entity> {
    /**
     * Возвращает запись из таблицы БД
     * @param query Запрос
     */
    @RawQuery
    fun getSingle(query: SupportSQLiteQuery): Entity?

    /**
     * Возвращает список записей из таблицы БД
     * @param query Запрос
     */
    @RawQuery
    fun getList(query: SupportSQLiteQuery): List<Entity>

    /**
     * Возвращает булево значение из запроса к БД
     * @param query Запрос
     */
    @RawQuery
    fun getBoolean(query: SupportSQLiteQuery): Boolean

    /**
     * Возвращает long значение из запроса к БД
     * @param query Запрос
     */
    @RawQuery
    fun getLong(query: SupportSQLiteQuery): Long

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