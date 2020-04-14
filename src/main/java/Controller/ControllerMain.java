package Controller;

import DAO.ProductDAO;
import DAO.ProductDBDAO;
import Interaction.InputManager;
import View.AbstractView;
import View.TerminalView;

import java.io.IOException;

public class ControllerMain {
    private String[] menuContent = new String[5];
    private String label = "Please choose options";
    private ControllerShop controllerShop;
    private AbstractView view;
    private InputManager input;

    public ControllerMain(AbstractView view, InputManager input) throws IOException {
        controllerShop = new ControllerShop(view, input, new ProductDBDAO());
        this.view = view;
        this.input = input;
        fillMenuContent();

    }


    private void fillMenuContent() {
        menuContent[0] = "0. Exit";
        menuContent[1] = "1. Login as User";
        menuContent[2] = "2. Login as Admin";
        menuContent[3] = "3. Create new user";
        menuContent[4] = "4. Create new Admin";
    }

    private boolean switchController() throws IOException {
        view.print(menuContent, label);
        Integer inputInt = input.getIntInput("Please provide option.");
        switch (inputInt) {
            case 0:
                return false;
            case 1:
                controllerShop.run();
                break;
            case 2:
                //controllerShop.
                break;
            case 3:

                break;
            case 4:
                break;
        }
        return true;
    }

    public void run() throws IOException{

        boolean isRun;
        do{

            isRun = switchController();
        }while(isRun);
    }
}
