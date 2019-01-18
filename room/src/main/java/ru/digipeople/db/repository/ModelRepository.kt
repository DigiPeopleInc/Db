package ru.digipeople.db.repository

import ru.digipeople.db.model.ModelWithId

/**
 * Репозиторий для классической сущности локальной БД.
 *
 * @author Aleksandr Brazhkin
 */
interface ModelRepository<Model, Id> : Repository where Model : ModelWithId<Id> {
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
}