package DAO;

import Model.Product;

import java.util.HashMap;

public interface ProductDAO {
    public HashMap<Product,Integer> getAllProducts();

    public HashMap<Product,Integer> searchProducts(String word);

    public void decreaseQuantity(Product product);
}
