package DAO;

import Model.Product;

import java.util.HashMap;

public class ProductDBDAO implements ProductDAOAdmin {

    private HashMap<Product,Integer> productsList = new HashMap<>();


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
    public HashMap<Product,Integer> getAllProducts() {
        return null;
    }

    @Override
    public HashMap<Product,Integer> searchProducts() {
        return null;
    }

    @Override
    public void decreaseQuantity(Product product) {

    }
}
