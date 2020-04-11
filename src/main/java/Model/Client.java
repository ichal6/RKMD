package Model;


import java.util.HashMap;

public class Client extends UserAbstract {
    HashMap<Product, Integer> basket;

    public Client(String[] dataAboutClient) {
        super(dataAboutClient);
        basket = new HashMap<>();
    }

    public void addToBasket(Product newProduct){
        if(basket.containsKey(newProduct)){
            int count = basket.get(newProduct) + 1;
            basket.put(newProduct, count);
        }else{
            basket.put(newProduct, 1);
        }

    }

    public void removeFromBasket(Product deleteProduct){
        basket.remove(deleteProduct);
    }

    public HashMap<Product, Integer> getBasket() {
        return basket;
    }
}
