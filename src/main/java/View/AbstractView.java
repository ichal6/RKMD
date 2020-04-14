package View;

import Model.Product;
import Model.UserAbstract;

import java.util.List;
import java.util.TreeMap;

import java.util.HashMap;


public abstract class AbstractView {

    public abstract void print(String message);

    public abstract void print(List<UserAbstract> PersonsList);

    public abstract void print(TreeMap<Product, Integer> productsList);

    public abstract void print(String[] menuContent, String label);

    public abstract void print(HashMap<Product, Integer> basket);

}


