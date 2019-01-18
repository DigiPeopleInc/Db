package ru.digipeople.database.room.entity

/**
 * Сущность локальной БД с Id.
 *
 * @author Aleksandr Brazhkin
 */
interface EntityWithId<Id> {
    var id: Id
}