package ru.digipeople.database.repository

/**
 * Репозиторий Ключ-Значение.
 *
 * @author Aleksandr Brazhkin
 */
interface KeyValueRepository : Repository {
    /**
     * Возвращает значение по ключу
     *
     * @param key          Ключ
     * @return Значение
     */
    fun get(key: String): String?

    /**
     * Устанавливает значение по ключу
     *
     * @param key   Ключ
     * @param value Значение
     */
    fun set(key: String, value: String?)

    /**
     * Возращает значения для всех ключей
     *
     * @return Значения для ключей
     */
    fun load(): Map<String, String?>

    /**
     * Добавляет новые ключи, обновляет существующие
     */
    fun merge(map: Map<String, String?>)

    /**
     * Добавляет новые ключи, удаляет существующие
     */
    fun rewrite(map: Map<String, String?>)

    /**
     * Удаляет ключ
     *
     * @param key Ключ
     */
    fun delete(key: String)

    /**
     * Удаляет все ключи
     */
    fun deleteAll()

    /**
     * Проверяет наличие ключа
     *
     * @param key Ключ
     *
     * @return true если существует, false - иначе
     */
    fun exists(key: String): Boolean
}