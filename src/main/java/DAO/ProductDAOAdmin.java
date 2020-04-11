package DAO;

import Model.Product;

public interface ProductDAOAdmin extends ProductDAO {

    public void addProductToInventory(Product product, Integer quantity);

    public void deleteProduct(Integer id);

    public void updateInventory(Product product, Integer quantity);
}
