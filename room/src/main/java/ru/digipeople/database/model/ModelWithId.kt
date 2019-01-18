package ru.digipeople.database.model

/**
 * Модель локальной БД с Id.
 *
 * @author Aleksandr Brazhkin
 */
interface ModelWithId<Id> {
    var id: Id
}