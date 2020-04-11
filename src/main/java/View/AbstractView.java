package View;

import Model.Product;
import Model.UserAbstract;

import java.util.List;
import java.util.TreeMap;

public abstract class AbstractView {
    public abstract void  print(String message);
    public abstract void print(List<UserAbstract> PersonsList);
    public abstract void print(TreeMap<Product, Integer> productsList);
}
