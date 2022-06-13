package impl;

import domain.Game;
import domain.User;

import java.util.Random;
import java.util.Scanner;

public class ThinkOfNumberGame implements Game {

    private static final int COUNT_OF_TRIES = 5;
    private int currentMaxNumber = 100;
    private int currentMinNumber = 0;

    private final User user;

    public ThinkOfNumberGame(User user) {
        this.user = user;
    }

    private long makeBet() {
        System.out.print("Сделайте ставку: ");
        long bet = new Scanner(System.in).nextLong();
        if (user.getMoney() < bet) {
            System.out.println("У вас нет столько денег! Баланс: " + user.getMoney());
            return makeBet();
        } else return bet;
    } //Проверка ставки

    @Override
    public void play() {
        long bet = makeBet();
        checkUserReady();
        int currentTry = 0;
        int numberToGuess = new Random().nextInt(101);
        if (guessUsersNumber(numberToGuess, currentTry, currentMinNumber, currentMaxNumber)) {
            user.reduceMoney(bet);
        } else {
            user.increaseMoney(bet);
        }
    }

    // TODO Вынести методы в абстрактный класс

    private void checkUserReady() {
        System.out.println("Загадайте число от 0 до 100 и вбейте 'Загадал'.");
        String ready = new Scanner(System.in).nextLine();
        if (!ready.equalsIgnoreCase("Загадал")) {
            checkUserReady();
        }
    } // Проверка на готовность

    private boolean guessUsersNumber(int numberToGuess, int currentTry, int currentMinNumber, int currentMaxNumber) {
        if (checkCountOfTries(currentTry)) {
            System.out.println("Программа исчерпала кол-во попыток, Вы выиграли!");
            return false;
        } else {
            System.out.println("Ваше число - " + numberToGuess + "? \n " +
                    "Введите:\n" +
                    "Да\n" +
                    "Больше\n" +
                    "Меньше");
            String userAnswer = new Scanner(System.in).nextLine();
            if (userAnswer.equalsIgnoreCase("да")) {
                System.out.println("Проигрыш! Программа угадала число");
                return true;
            } else if (userAnswer.equalsIgnoreCase("больше")) {
                //применено бинарное сечение
                int newNumberToGuess = (currentMaxNumber + numberToGuess) / 2;
                if (numberToGuess == newNumberToGuess) {
                    newNumberToGuess++;
                }
                guessUsersNumber(newNumberToGuess, ++currentTry, ++numberToGuess, currentMaxNumber);
            } else if (userAnswer.equalsIgnoreCase("меньше")) {
                int newNumberToGuess = (currentMinNumber + numberToGuess) / 2;
                if (numberToGuess == newNumberToGuess) {
                    newNumberToGuess--;
                }
                guessUsersNumber(newNumberToGuess, ++currentTry, currentMinNumber, --numberToGuess);
            }
            return false;
        }
    }


    private boolean checkCountOfTries(int currentTry) {
        return currentTry == COUNT_OF_TRIES;
    } //учитывается количество попыток
}
