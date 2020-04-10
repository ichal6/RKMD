package DAO;

import Model.Product;

import java.sql.ResultSet;

public interface ProductDAO {
    public ResultSet getAllProducts();

    public ResultSet searchProducts();

    public void decreaseQuantity(Product product);
}
