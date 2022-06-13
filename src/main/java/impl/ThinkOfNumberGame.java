package impl;

import domain.Game;
import domain.User;

import java.util.Random;
import java.util.Scanner;

public class ThinkOfNumberGame extends GamesMethods implements Game {
    //методы вынесенены в абстрактный класс
    private static final int COUNT_OF_TRIES = 5;
    private int currentMaxNumber = 100;
    private int currentMinNumber = 0;
    private final User user;

    public ThinkOfNumberGame(User user) {
        super(user);
        this.user = user;
    } //инициализация пользователя

    @Override
    public void play() {
        long bet = makeBet();
        System.out.println("Загадайте число от 0 до 100 и вбейте 'Готов'.");
        checkUserReady();
        int currentTry = 0;
        int numberToGuess = new Random().nextInt(101);
        if (guessUsersNumber(numberToGuess, currentTry, currentMinNumber, currentMaxNumber)) {
            user.reduceMoney(bet);
        } else {
            user.increaseMoney(bet);
        }
    }

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
}
