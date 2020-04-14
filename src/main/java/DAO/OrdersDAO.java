package DAO;

import Model.Client;
import Model.Product;

import java.util.HashMap;

public interface OrdersDAO {
//    public List getAllOrders(); Do we really need this on this stage?
//    public Order getSpecificOrder(Integer id); Do we really need this on this stage?
    public void createOrder(HashMap<Product, Integer> basket, Client client);
}
