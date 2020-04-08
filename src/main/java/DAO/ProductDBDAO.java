package DAO;

import Model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductDBDAO implements ProductDAOAdmin {

    private List<Product> productsList = new ArrayList<>();

    @Override
    public List<Product> getAllProducts() {
        return null;
    }

    @Override
    public List<Product> searchProducts() {
        return null;
    }

    @Override
    public void decreaseQuantity(Product product) {

    }

    @Override
    public void addProductToInventory(Product product) {

    }

    @Override
    public void deleteProduct(Product product) {

    }

    @Override
    public void updateInventory(Product product) {

    }
}
