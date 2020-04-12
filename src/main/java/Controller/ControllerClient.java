package Controller;

import DAO.ClientsDAO;
import DAO.ClientsDatabaseDAO;
import Interaction.InputManager;
import Model.Client;
import Model.Product;
import View.AbstractView;

import java.util.HashMap;

public class ControllerClient {
    private AbstractView view;
    private InputManager input;
    private ClientsDAO dao;
    private Client client;

    public ControllerClient(AbstractView view, InputManager input, ClientsDAO dao) {
        this.view = view;
        this.input = input;
        this.dao = dao;
    }

    private void addToBasket(Product product, Integer quantity){
        client.addToBasket(product,quantity);
    }

    public HashMap<Product,Integer> getBasket(){
        return client.getBasket();
    }

    public boolean logIn(String login, String password) {
        if(dao.checkIsClient(login,password)){
            client = dao.getClient(login,password);
            return true;
        }else{
            return false;
        }
    }
}
