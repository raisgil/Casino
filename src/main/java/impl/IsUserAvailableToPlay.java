package impl;

import app.Application;
import domain.User;

//  Вынес метод в отдельный UserCheker
public class IsUserAvailableToPlay extends Application {
    public static boolean userCheck(User user) {
        if (user.getMoney() <= 0) {
            System.out.println("На вашем счете недостаточно средств, пожалуйста, пополните ваш счет.");
            return false;
        } else if (user.getWinStreak() >= 5) {
            System.out.println("На сайте ведутся тех. работы, вернитесь позже");
            return false;
        } else {
            return true;
        }
    }
}
