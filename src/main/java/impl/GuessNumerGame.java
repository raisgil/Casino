package impl;

import domain.Game;
import domain.User;

import java.util.Random;
import java.util.Scanner;

public class GuessNumerGame implements Game {

    private static final int COUNT_OF_TRIES = 5;

    private final User user;

    public GuessNumerGame(User user) {
        this.user = user;
    }

    private long makeBet() {
        System.out.print("Сделайте ставку: ");
        long bet = new Scanner(System.in).nextLong();
        if (user.getMoney() < bet) {
            System.out.println("У вас нет столько денег! Баланс: "+user.getMoney());
            return makeBet();
        } else return bet;
    }

    @Override
    public void play() {
        long bet = makeBet();
        System.out.println("Загадано число, требуется угадать за " + COUNT_OF_TRIES + " попыток.");
        int numberToGuess = new Random().nextInt(101);
        int currentTry = 0;
        while (true) {
            System.out.print("Введите свой вариант: ");
            int enteredNumber = new Scanner(System.in).nextInt();
            //Реализован выигрыш х2, если угадали с первой попытки.
            if (enteredNumber == numberToGuess && currentTry == 0) {
                user.increaseMoney(bet * 2);
                user.winSreak(1);
                System.out.println("Поздравляю!\n" +
                        "Вы угадали число c ПЕРВОЙ ПОПЫТКИ!\n" +
                        "Вы выграли " + bet * 2 + "\n");
                break;
            }
            if (enteredNumber > numberToGuess) {
                System.out.println("Загаданное число меньше.");
                currentTry++;
            }
            if (enteredNumber < numberToGuess) {
                System.out.println("Загаданное число больше.");
                currentTry++;
            }
            if (enteredNumber == numberToGuess) {
                user.increaseMoney(bet);
                user.winSreak(1);
                System.out.println("Поздравляю!\n" +
                        "Вы угадали число!\n" +
                        "Вы выграли " + bet + "\n");
                break;
            }
            if (currentTry == COUNT_OF_TRIES & numberToGuess != enteredNumber) {
                System.out.println("Увы, попыток не осталось, вы проиграли. Было загадано число - " + numberToGuess);
                user.reduceMoney(bet);
                user.winSreak(-1 * (user.getWinStreak()));
                break;
            }
        }
    }
}