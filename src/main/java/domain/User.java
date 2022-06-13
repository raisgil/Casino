package domain;

/**
 * Базовый интерфейс пользователя.
 */
public interface User {
    /**
     * Увеличение счета пользователя.
     *
     * @param amount Сумма
     */
    void increaseMoney(long amount);

    /**
     * Уменьшение счета пользователя.
     *
     * @param amount Сумма
     */

    void reduceMoney(long amount);

    /**
     * Слежка за количеством побед
     *
     * @param winstreak кол-во побед подряд
     */

    void winSreak(int winstreak);

    int getWinStreak();

    /**
     * Имя пользователя.
     */
    String getName();

    /**
     * Счет пользователя.
     */
    long getMoney();


}
