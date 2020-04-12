import Controller.ControllerShop;
import DAO.ProductDBDAO;
import Interaction.InputManager;
import View.TerminalView;

public class Main {

    public static void main(String[] args) {
        ControllerShop shop =  new ControllerShop(new TerminalView(),new InputManager(), new ProductDBDAO());
        shop.run();
    }
}
