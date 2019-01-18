package ru.digipeople.db

/**
 * Транзакция БД.
 *
 * @author Aleksandr Brazhkin
 */
interface DbTransaction {
    /**
     * Начинает транзакцию
     */
    fun begin()

    /**
     * Завершает транзакцию
     */
    fun end()

    /**
     * Применяет изменения
     */
    fun setSuccessful()

    /**
     * Выполняет блок кода в транзакции
     */
    fun <T> callInTx(body: () -> T): T
}