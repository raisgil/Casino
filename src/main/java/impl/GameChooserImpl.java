package impl;

import domain.Game;
import domain.GameChooser;
import domain.User;

import java.util.Scanner;

public class GameChooserImpl implements GameChooser {
    @Override
    public Game chooseGame(User user) {
        System.out.print("Выберите игру:\n" +
                " * Угадай число \n" +
                " * Загадай число \n" +
                "Ваш вариант: ");

        String gameName = new Scanner(System.in).nextLine();
        if (gameName.equalsIgnoreCase("Угадай число")) {
            return new GuessNumerGame(user);
        } else if (gameName.equalsIgnoreCase("Загадай число")) {
            return new ThinkOfNumberGame(user);
        }
        // TODO Возвращается null, если введено некорректное игры
        //   Должно запрашиваться название игры, пока не будет введено корректно.
        //   Возврат null происходить не должен.
        System.out.println("Введено некорректное название игры:");
        return null;
    }
}

