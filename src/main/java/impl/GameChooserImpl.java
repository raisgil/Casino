package impl;

import domain.Game;
import domain.GameChooser;
import domain.User;

import java.util.Scanner;

public class GameChooserImpl implements GameChooser {

    @Override
    public Game chooseGame(User user) {
        System.out.print("--------------------------\n" +
                "Ваш баланс: " + user.getMoney() + "\n" +
                "Выберите игру:\n" +
                " * Угадай число \n" +
                " * Загадай число \n" +
                " * Кости \n" +
                "--------------------------\n" +
                "Ваш вариант: "
        );
        String gameName = new Scanner(System.in).nextLine();
        if (gameName.equalsIgnoreCase("Угадай число")) {
            return new GuessNumerGame(user);
        } else if (gameName.equalsIgnoreCase("Загадай число")) {
            return new ThinkOfNumberGame(user);
        } else if (gameName.equalsIgnoreCase("Кости")) {
            return new DiceGame(user);
        } else {
            System.out.println("Корректно введите название игры!");
        }
        return chooseGame(user);
    }
}




