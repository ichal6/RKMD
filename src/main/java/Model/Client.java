package Model;


import java.util.HashMap;

public class Client extends UserAbstract {
    HashMap<Product, Integer> basket;

    public Client(String[] dataAboutClient) {
        super(dataAboutClient);
        basket = new HashMap<>();
    }

    public void addToBasket(Product newProduct, Integer quantity){
        if(basket.containsKey(newProduct)){
            int count = basket.get(newProduct) + quantity;
            basket.put(newProduct, count);
        }else{
            basket.put(newProduct, quantity);
        }

    }

    public void removeFromBasket(Product deleteProduct){
        basket.remove(deleteProduct);
    }

    public void clearBasket(){
        basket.clear();
    }

    public HashMap<Product, Integer> getBasket() {
        return basket;
    }
}
