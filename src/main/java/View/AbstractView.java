package View;

import Model.Product;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractView {

    public AbstractView(){

    }
    public void print(String[] menuContent, String label){
        System.out.println(label);
        System.out.println(menuContent);
    }

    public void print(HashMap<Product,Integer> basket) {
        for(Map.Entry<Product,Integer> product : basket.entrySet()){
            System.out.println(product.getKey().toString() + " " + product.getValue());
        }
    }
}
