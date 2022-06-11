package impl;

import domain.Registrator;
import domain.User;

import java.util.Scanner;

public class RegistratorImpl implements Registrator {
    @Override
    public User registerUser() {
        System.out.print("Привет, представьтесь: ");
        // TODO Добавить проверку имени и спрашивать его до тех пор, пока не введут корректно.
        //   Валидность имени состоит только из букв и только из одного слова.
        //   Если пользователь вводит имя с маленькой буквы - сделать первую заглавной.
        String name = new Scanner(System.in).nextLine();
        return new UserImpl(name);
    }
}
