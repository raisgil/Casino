package impl;

import domain.Game;
import domain.User;

import java.util.Random;
import java.util.Scanner;

public class DiceGame extends GamesMethods implements Game {
    private final User user;

    public DiceGame(User user) {
        super(user);
        this.user = user;
    } //инициализация пользователя

    @Override
    public void play() throws InterruptedException {
        long bet = makeBet();
        if (rollTheDice()) {
            System.out.println("Увы, но вы проиграли.");
            user.reduceMoney(bet);
        } else {
            System.out.println("Поздравляем, вы выиграли!");
            user.increaseMoney(bet);
        }
    }

    private boolean rollTheDice() throws InterruptedException {
        System.out.print("Казино бросает кости ");
        Thread.sleep(1000);
        System.out.println("⌛");
        Thread.sleep(1000);
        int casinoDice1 = (new Random().nextInt(6) + 1);
        System.out.print("Казино выпало: " + casinoDice1);
        Thread.sleep(1000);
        System.out.println(" ⌛");
        Thread.sleep(1000);
        int casinoDice2 = (new Random().nextInt(6) + 1);
        int casinoResult = casinoDice1 + casinoDice2;
        int userResult = 0;
        System.out.print("Казино выпало: " + casinoDice2 + ", Итого у Казино: " + casinoResult);
        System.out.print("\nВаша очередь, чтобы бросить кости, напишите 'Готов': ");
        checkUserReady();
        Thread.sleep(1000);
        int userDice1 = (new Random().nextInt(6) + 1);
        System.out.print("Вам выпало: " + userDice1);
        Thread.sleep(1000);
        System.out.println(" ⌛");
        Thread.sleep(1000);
        int userDice2 = (new Random().nextInt(6) + 1);
        userResult = userDice1 + userDice2;
        System.out.println("Вам выпало: " + userDice2 + ", Итого: " + userResult);
        Thread.sleep(1000);
        if (casinoResult == userResult) {
            System.out.println("Перекидываем");
            return rollTheDice();
        }
        if (casinoResult > userResult) {
            return true;
        } else {
            return false;
        }
    }
}
