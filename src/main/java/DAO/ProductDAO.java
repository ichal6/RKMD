package DAO;

import Model.Product;

import java.util.List;

public interface ProductDAO {
    public List<Product> getAllProducts();

    public List<Product> searchProducts();

    public void decreaseQuantity(Product product);
}
