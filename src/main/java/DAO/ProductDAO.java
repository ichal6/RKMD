package DAO;

import Model.Product;

import java.util.HashMap;
import java.util.TreeMap;

public interface ProductDAO {
    public TreeMap<Product,Integer> getAllProducts();

    public void searchProducts(String word);

    public void decreaseQuantity(Product product, Integer quantity);
}
