package Model;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Basket {

    Map<Product, Integer> mapOfProducts;

    public Basket(){
        mapOfProducts = new HashMap<>();

    }

    public Map<Product, Integer> getMapOfProducts() {
        return mapOfProducts;
    }

}
