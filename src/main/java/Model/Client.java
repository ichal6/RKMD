package Model;

import java.util.HashMap;
import java.util.Map;

public class Client extends User {
    Basket basket;
    public Client(String[] dataAboutClient) {
        super(dataAboutClient);
        basket = new Basket();
    }

    public Client(Builder builder){
        super(builder);
        basket = new Basket();
    }
    
    public HashMap<Product, Integer> getBasket() {
        return (HashMap<Product, Integer>) basket.getMapOfProducts();
    }
}
