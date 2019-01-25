package ru.digipeople.database.repository

import ru.digipeople.database.model.ModelWithId

/**
 * Репозиторий для классической сущности локальной БД.
 *
 * @author Aleksandr Brazhkin
 */
interface ModelRepository<Model, Id> : Repository where Model : ModelWithId<Id> {
    /**
     * Возвращает запись по Id
     * @param id Id записи
     */
    fun getById(id: Id): Model?

    /**
     * Возвращает все записи из таблицы БД
     */
    fun getAll(): List<Model>

    /**
     * Выполняет вставку [model] в БД
     * @param model Модель
     */
    fun insert(model: Model)

    /**
     * Выполняет вставку списка [models] в БД
     * @param models Список моделей
     */
    fun insert(models: List<Model>)

    /**
     * Выполняет обновление [model] в БД
     * @param model Модель
     */
    fun update(model: Model)

    /**
     * Удаляет [model] из БД
     * @param model Модель
     */
    fun delete(model: Model)

    /**
     * Удаляет список [models] из БД
     * @param models Список моделей
     */
    fun delete(models: List<Model>)

    /**
     * Удаляет все записи из таблицы БД
     */
    fun deleteAll()

    /**
     * Проверяет наличие записи по Id
     * @param id Id записи
     */
    fun exists(id: Id): Boolean
}