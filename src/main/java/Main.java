import Controller.ControllerShop;
import DAO.ProductDBDAO;
import Interaction.InputManager;
import View.TerminalView;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        ControllerShop shop = null;
        try {
            shop = new ControllerShop(new TerminalView(),new InputManager(), new ProductDBDAO());
            shop.run();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
