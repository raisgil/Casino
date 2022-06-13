package impl;

import domain.Registrator;
import domain.User;

import java.util.Scanner;

public class RegistratorImpl implements Registrator {
    @Override
    public User registerUser() {
        System.out.print("Привет, представьтесь: ");
        //  проверка имени и спрос его до тех пор, пока не введут корректно.
        //  Валидность имени состоит только из букв и только из одного слова.
        String name = new Scanner(System.in).nextLine().toLowerCase();
        if (name.contains(" ") || (name.equals(""))) {
            return registerUser();
        } else {
            for (int i = 0; i < name.length(); i++) {
                boolean result;
                char[] array = name.toCharArray();
                result = (Character.isLetter(array[i]));
                if (!result) {
                    System.out.println("Введите ваше ИМЯ!");
                    return registerUser();
                }

            }
            String resname = ((name.substring(0, 1).toUpperCase()) + name.substring(1, name.length())).toString();
            System.out.println("Приветсвуем Вас, " + resname);
            return new UserImpl(resname);
        }
    }
}

