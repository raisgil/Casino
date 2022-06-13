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
            System.out.println("У вас нет столько денег! Баланс: "+user.getMoney());
            return makeBet();
        } else return bet;
    }

    @Override
    public void play() {
        long bet = makeBet();
        // TODO После рефакторинга метода checkUserReady убрать неиспользуюмую переменную isUserRady.
        String isUserReady = checkUserReady();
        int currentTry = 0;
        int numberToGuess = new Random().nextInt(101);
        if (guessUsersNumber(numberToGuess, currentTry)) {
            user.reduceMoney(bet);
        } else {
            user.increaseMoney(bet);
        }
    }

    // TODO Вынести методы в абстрактный класс

    private String checkUserReady() {
        //TODO Проверить что ввел пользователь.
        //   Если пользователь ввел что-то кроме 'Загадал' или ничего, то указать ему что надо ввести 'Загадал' и попрость заново.
        //   Метод должен ничего не возвращать, т.е. String станет void.
        System.out.println("Загадайте число от 0 до 100 и вбейте 'Загадал'.");
        return new Scanner(System.in).nextLine();
    }

    private boolean guessUsersNumber(int numberToGuess, int currentTry) {
        //TODO оличество попыток не учитывается
        //   Программа угадывает число больше, чем максимальное количество попыток.
        if (checkCountOfTries(currentTry)) {
            System.out.println("Программа исчерпала кол-во попыток");
            return false;
        }
        System.out.println("Ваше число - " + numberToGuess + "? \n " +
                "Введите:\n" +
                "Да\n" +
                "Больше\n" +
                "Меньше");
        String userAnswer = new Scanner(System.in).nextLine();
        if (userAnswer.equalsIgnoreCase("да")) {
            System.out.println("Проигрыш! Программа угадала число");
        } else if (userAnswer.equalsIgnoreCase("больше")) {
            //TODO применить бинарное сечение
            int newNumberToGuess = (currentMaxNumber - numberToGuess) / 2 + numberToGuess;
            currentMinNumber = numberToGuess;
            guessUsersNumber(newNumberToGuess, currentTry++);
        } else if (userAnswer.equalsIgnoreCase("меньше")) {
            int newNumberToGuess = numberToGuess - (numberToGuess - currentMinNumber);
            currentMaxNumber = numberToGuess;
            guessUsersNumber(newNumberToGuess, currentTry++);
        }
        return false;
    }

    private boolean checkCountOfTries(int currentTry) {
        return currentTry == COUNT_OF_TRIES;
    }
}
