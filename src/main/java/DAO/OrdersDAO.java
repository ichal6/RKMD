package DAO;

import Model.Client;
import Model.Product;

import java.util.HashMap;

public interface OrdersDAO {
    public void createOrder(HashMap<Product, Integer> basket, Client client);
}
