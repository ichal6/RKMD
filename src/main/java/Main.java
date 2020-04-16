import Controller.ControllerMain;
import Interaction.InputManager;
import View.TerminalView;
import java.io.IOException;


public class Main {
    public static void main(String[] args) {
        ControllerMain main = null;
        try {
            main = new ControllerMain(new TerminalView(),new InputManager());
            main.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}