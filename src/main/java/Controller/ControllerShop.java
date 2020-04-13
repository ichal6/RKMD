package Controller;


import DAO.ClientsDatabaseDAO;
import DAO.ProductDAO;
import Interaction.InputManager;
import Model.Product;
import View.AbstractView;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class ControllerShop {
    private AbstractView view;
    private InputManager input;
    private ProductDAO dao;
    private ControllerClient controllerClient;
    private String[] menuContent = new String[4];
    private String label = "Welcome to our shop";

    public ControllerShop(AbstractView view, InputManager input, ProductDAO dao) throws IOException {
        controllerClient = new ControllerClient(view, input, new ClientsDatabaseDAO());
        this.view = view;
        this.input = input;
        this.dao = dao;
        fillMenuContent();

    }

    public TreeMap<Product, Integer> searchProducts() {
        view.print("Please insert phrase to search: ");
        String wordToSearch = "Kross"; // here It have to input
        return dao.searchProducts(wordToSearch);
    }

    private void chooseProduct(TreeMap<Product, Integer> MapOfProducts){
        HashMap<Product, Integer> basket = controllerClient.getBasket();
        view.print("Please choose product:");
        view.print(MapOfProducts);
        String nameOfProduct = "Kross";//here must be input
        view.print("Please choose quantity of product:");
        int quantity = 2; //here must be input
        for(Map.Entry<Product,Integer> product : MapOfProducts.entrySet()){
            if(product.getKey().getProductName().equals(nameOfProduct) && product.getValue() >= quantity ){
                if(basket.containsKey(product.getKey())){
                    if(basket.get(product.getKey()) + quantity <= product.getValue()){
                        controllerClient.addToBasket(product.getKey(), quantity);
                        break;
                    }
                }else{
                    controllerClient.addToBasket(product.getKey(), quantity);
                    break;
                }
            }
        }
    }

    public void executeOrder() {
        HashMap<Product, Integer> mapOfProducts = controllerClient.getBasket();
        for(Map.Entry<Product,Integer> product : mapOfProducts.entrySet()){
            dao.decreaseQuantity(product.getKey(), product.getValue());
        }
        controllerClient.clearBasket();
        // We must call method add new row to table order. Do we need new DAO for Order?
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
                chooseProduct(searchProducts());
                break;
            case 2:
                view.print(controllerClient.getBasket());
                break;
            case 3:
                executeOrder();
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
