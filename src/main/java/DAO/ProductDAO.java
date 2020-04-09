package DAO;

import Model.Product;

import java.util.HashMap;
import java.util.List;

public interface ProductDAO {
    public HashMap<Product,Integer> getAllProducts();

    public HashMap<Product,Integer> searchProducts();

    public void decreaseQuantity(Product product);
}
