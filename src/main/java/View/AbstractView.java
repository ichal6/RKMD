package View;

import Model.Product;
import Model.UserAbstract;

import java.util.List;
import java.util.TreeMap;

import java.util.HashMap;
import java.util.Map;


public abstract class AbstractView {

    public abstract void print(String message);

    public abstract void print(List<UserAbstract> PersonsList);

    public abstract void print(TreeMap<Product, Integer> productsList);

    public void print(String[] menuContent, String label) {
        System.out.println(label);
        for (String menuItem : menuContent) {
            System.out.println(menuItem);
        }
    }

    public void print(HashMap<Product, Integer> basket) {
        for (Map.Entry<Product, Integer> product : basket.entrySet()) {
            System.out.println(product.getKey().toString() + " " + product.getValue());
        }
    }
}


