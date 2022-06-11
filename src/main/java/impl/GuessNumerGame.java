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

    @Override
    public void play() {
        System.out.print("Сделайте ставку: ");
        long bet = new Scanner(System.in).nextLong();
        System.out.println("Загадано число, требуется угадать за " + COUNT_OF_TRIES + " попыток.");
        int numberToGuess = new Random().nextInt(101);
        int currentTry = 0;
        while (true) {
            if (currentTry == COUNT_OF_TRIES) {
                System.out.println("Увы, попыток не осталось, вы проиграли. Было загадано число - " + numberToGuess);
                user.reduceMoney(bet);
                break;
            }

            System.out.print("Введите свой вариант: ");
            int enteredNumber = new Scanner(System.in).nextInt();
            if (enteredNumber > numberToGuess) {
                System.out.println("Загаданное число меньше.");
            }
            if (enteredNumber < numberToGuess) {
                System.out.println("Загаданное число больше.");
            }
            if (enteredNumber == numberToGuess) {
                // TODO добавить функцию x2 если число угадали с первой попытки.
                System.out.println("Поздравляю!\nВы угадали число!\nВы выграли " + bet + "\nУ вас на счете: "+ user.getMoney());
                user.increaseMoney(bet);
            }
            currentTry++;
        }
    }
}