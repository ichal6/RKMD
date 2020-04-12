package Controller;


import DAO.ClientsDatabaseDAO;
import DAO.ProductDAO;
import Interaction.InputManager;
import View.AbstractView;

public class ControllerShop {
    private AbstractView view;
    private InputManager input;
    private ProductDAO dao;
    private ControllerClient controllerClient;
    private String[] menuContent = new String[4];
    private String label = "Welcome to our shop";

    public ControllerShop(AbstractView view, InputManager input, ProductDAO dao) {
        controllerClient = new ControllerClient(view, input, new ClientsDatabaseDAO());
        this.view = view;
        this.input = input;
        this.dao = dao;
        fillMenuContent();

    }

    public void searchProducts() {

    }

    public void executeOrder() {
    }

    private boolean tryToLogIn() {
        String login = "straznik-kosmosu";//input.getStringInput("Please insert your login");
        String passw = "koniecswiata";//input.getStringInput("Please insert your password");
        return controllerClient.logIn(login, passw);
    }

    private void fillMenuContent() {
        menuContent[0] = "0. Log out";
        menuContent[1] = "1. Search product";
        menuContent[2] = "2. Display basket";
        menuContent[3] = "3. Checkout";
    }

    private boolean switchController() {
        Integer inputInt = input.getIntInput("Please provide option.");
        switch (inputInt) {
            case 0:
                return false;
            case 1:
                dao.searchProducts("asd");
                break;
            case 2:
                view.print(controllerClient.getBasket());
                break;
            case 3:

                break;
        }
        return true;
    }

    public void run() {
        boolean isLogIn = false;
        while (!isLogIn){
            isLogIn = tryToLogIn();
        }
        boolean isRun = true;
        do {
            view.print(menuContent, label);
            isRun = switchController();
        } while (isRun);
    }
}
