package DAO;

import Model.Client;
import Model.Order;
import Model.Product;

import java.util.HashMap;
import java.util.List;

public interface OrdersDAO {
    public List getAllOrders();
    public Order getSpecificOrder(Integer id);
    public void createOrder(HashMap<Product, Integer> basket, Client client);
}
