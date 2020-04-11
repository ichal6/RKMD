import Controller.ControllerShop;
import Interaction.InputManager;
import View.TerminalView;

public class Main {

    public static void main(String[] args) {
        ControllerShop shop =  new ControllerShop(new TerminalView(),new InputManager());
        shop.run();
    }
}
