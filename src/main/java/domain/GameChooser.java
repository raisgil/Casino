package domain;

/**
 * Базовый интерфейс для выбора игр.
 */

public interface GameChooser {
    /**
     * Базовый интерфейс выбора Игры.
     *
     * @param user
     * @return
     */

    Game chooseGame(User user);

}
