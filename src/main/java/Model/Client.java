package Model;


import java.util.HashMap;

public class Client extends UserAbstract {
    HashMap<Product, Integer> basket;

    public Client(String[] dataAboutClient) {
        super(dataAboutClient);
        basket = new HashMap<>();
    }

    public Client(Builder builder){
        super(builder);
        basket = new HashMap<>();
    }
    
    public HashMap<Product, Integer> getBasket() {
        return basket;
    }
}
