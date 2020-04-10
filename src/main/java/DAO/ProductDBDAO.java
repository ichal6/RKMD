package DAO;

import Model.Product;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductDBDAO implements ProductDAOAdmin {

    private List<Product> productsList = new ArrayList<>();


    @Override
    public void addProductToInventory(Product product) {

    }

    @Override
    public void deleteProduct(Product product) {

    }

    @Override
    public void updateInventory(Product product) {

    }

    @Override
    public ResultSet getAllProducts() {
        return null;
    }

    @Override
    public ResultSet searchProducts() {
        return null;
    }

    @Override
    public void decreaseQuantity(Product product) {

    }
}
