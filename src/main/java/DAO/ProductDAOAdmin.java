package DAO;

import Model.Product;

import java.util.TreeMap;

public interface ProductDAOAdmin extends ProductDAO {
    public TreeMap<Product, Integer> productsList = new TreeMap<>();
    public void addProductToInventory(Product product, Integer quantity);

    public void deleteProduct(Integer id);

    public void updateInventory(Product product, Integer quantity);
    public TreeMap<Product, Integer> getProductsList();
}
