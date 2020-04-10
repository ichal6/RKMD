package DAO;

import Model.Product;

public interface ProductDAOAdmin extends ProductDAO {

    public void addProductToInventory(Product product);

    public void deleteProduct(Product product);

    public void updateInventory(Product product);
}
