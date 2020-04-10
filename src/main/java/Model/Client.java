package Model;


import java.util.HashMap;

public class Client extends UserAbstract {
    HashMap<Product, Integer> basket;

    public Client(String[] dataAboutClient) {
        basket = new HashMap<>();
        super.ID = dataAboutClient[0];
        super.name = dataAboutClient[1];
        super.surname = dataAboutClient[2];
        super.login = dataAboutClient[3];
        super.password = dataAboutClient[4];
    }

    public void addToBasket(Product newProduct){
        if(basket.containsKey(newProduct)){
            int count = basket.get(newProduct) + 1;
            basket.put(newProduct, count);
        }else{
            basket.put(newProduct, 1);
        }

    }
}
