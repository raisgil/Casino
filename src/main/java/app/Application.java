package app;
//TODO Нарисовать UML на проект
import domain.Game;
import domain.GameChooser;
import domain.Registrator;
import domain.User;
import impl.GameChooserImpl;
import impl.RegistratorImpl;

public class Application {

    public static void main(String[] args) {
        Registrator registrator = new RegistratorImpl();
        GameChooser gameChooser = new GameChooserImpl();
        User user = registrator.registerUser();

        do {
            Game game = gameChooser.chooseGame(user);
            game.play();
            System.out.println(user.getName() + ", у Вас на счете " + user.getMoney());
        } while (isUserAvailableToPlay(user));
    }
    //TODO вынести метод в отдельный UserCheker
    public static boolean isUserAvailableToPlay(User user){
        //TODO сделать проверку, что пользователь может играть в казино.
        //   Если игрок сделал х5, то вывести тЕх работы.
        //   Если закончились денбги, предложить пополнить.
        return true;
    }
}

