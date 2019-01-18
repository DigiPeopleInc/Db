package ru.digipeople.database.room.mapper

/**
 * Базовый маппер.
 *
 * @author Aleksandr Brazhkin
 */
interface BaseMapper<M, E> {
    fun entityToModel(entity: E?): M?

    fun modelToEntity(model: M?): E?

    fun entityListToModelList(entities: List<E>?): List<M>?

    fun modelListToEntityList(models: List<M>?): List<E>?
}