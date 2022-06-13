package impl;

import domain.Game;
import domain.User;

import java.util.Scanner;
import java.util.function.DoubleToIntFunction;


public abstract class GamesMethods implements Game {
    private final User user;
    private static final int COUNT_OF_TRIES = 5;

    protected GamesMethods(User user) {
        this.user = user;
    }

    protected long makeBet() {
        System.out.print("Сделайте ставку: ");
        long bet = new Scanner(System.in).nextLong();
        if (user.getMoney() < bet) {
            System.out.println("У вас нет столько денег! Баланс: " + user.getMoney());
            return makeBet();
        } else return bet;
    } //Проверка ставки

    protected void checkUserReady() {
        String ready = new Scanner(System.in).nextLine();
        if (!ready.equalsIgnoreCase("Готов")) {
            System.out.print("Просто введите 'Готов': ");
            checkUserReady();
        }
    } // Проверка на готовность

    protected boolean checkCountOfTries(int currentTry) {
        return currentTry == COUNT_OF_TRIES;
    } //учитывается количество попыток


}
