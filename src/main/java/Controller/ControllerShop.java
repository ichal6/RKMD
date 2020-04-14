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
    private String[] menuContent = new String[5];
    private String label = "Welcome to our shop";

    public ControllerShop(AbstractView view, InputManager input, ProductDAO dao) throws IOException {
        controllerClient = new ControllerClient(view, input, new ClientsDatabaseDAO());
        this.view = view;
        this.input = input;
        this.dao = dao;
        fillMenuContent();

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

    public void addUser(String[] clientToAdd){
        controllerClient.addUser(clientToAdd);
    }

    public TreeMap<Product, Integer> searchProducts() {
        String wordToSearch = input.getStringInput("Please insert phrase to search: ");
        return dao.searchProducts(wordToSearch);
    }

    public void executeOrder() {
        HashMap<Product, Integer> mapOfProducts = controllerClient.getBasket();
        for(Map.Entry<Product,Integer> product : mapOfProducts.entrySet()){
            dao.decreaseQuantity(product.getKey(), product.getValue());
        }
        controllerClient.clearBasket();
        // We must call method add new row to table order. Do we need new DAO for Order?
    }

    private void removeProductFromBasket(){
        HashMap<Product, Integer> basket = controllerClient.getBasket();
        view.print(basket);
        String nameOfProduct = input.getStringInput("Please provide name of product to remove from basket:");

        for (Map.Entry<Product, Integer> product : basket.entrySet()) {
            if(product.getKey().getProductName().equals(nameOfProduct)){
                controllerClient.removeFromBasket(product.getKey());
                return;
            }
        }
        view.print("Product not found! Nothing delete from basket.");
    }

    private void chooseProduct(TreeMap<Product, Integer> MapOfProducts){
        HashMap<Product, Integer> basket = controllerClient.getBasket();

        view.print(MapOfProducts);
        String nameOfProduct = input.getStringInput("Please choose product:");

        int quantity = input.getIntInput("Please choose quantity of product:");

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

    private boolean tryToLogIn() {
        String login = input.getStringInput("Please insert your login");
        String passw = input.getStringInput("Please insert your password");
        return controllerClient.logIn(login, passw);
    }

    private void fillMenuContent() {
        menuContent[0] = "0. Log out";
        menuContent[1] = "1. Search product";
        menuContent[2] = "2. Display basket";
        menuContent[3] = "3. Checkout";
        menuContent[4] = "4. Remove from basket";
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
            case 4:
                removeProductFromBasket();
                break;
        }
        return true;
    }

}
