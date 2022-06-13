package app;
//TODO Нарисовать UML на проект

import domain.Game;
import domain.GameChooser;
import domain.Registrator;
import domain.User;
import impl.GameChooserImpl;
import impl.IsUserAvailableToPlay;
import impl.RegistratorImpl;
import impl.IsUserAvailableToPlay;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;

public class Application {

    public static void main(String[] args) {
        Registrator registrator = new RegistratorImpl();
        GameChooser gameChooser = new GameChooserImpl();
        User user = registrator.registerUser();

        do {
            Game game = gameChooser.chooseGame(user);
            game.play();
            System.out.println(user.getName() + ", у Вас на счете " + user.getMoney());
        } while (IsUserAvailableToPlay.userCheck(user));
    }
}


