package Controller;

import DAO.OrdersDAO;
import Model.Client;
import Model.Order;
import Model.Product;

import java.sql.*;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


public class OrdersController implements OrdersDAO {

    private String url = "jdbc:postgresql://localhost:5432/online_shop";
    private String user = "rafal";
    private String password = "rafal";


    private float calculateValueOfBasket(HashMap<Product, Integer> basket){
        float totalValue=0;
        for(HashMap.Entry<Product,Integer> product : basket.entrySet()){
            totalValue += product.getKey().getPrice() * product.getValue();
        }
        return totalValue;
    }

private Integer getLatestId(){
        Integer lastesId = 0;
        String query = "SELECT order_id FROM order_table ORDER BY order_id DESC LIMIT 1";
    try {
        Connection con = DriverManager.getConnection(url,user,password);
        PreparedStatement prepSt = con.prepareStatement(query);
        ResultSet resultSet = prepSt.executeQuery();
        if(resultSet.next()){
            return resultSet.getInt(1);
        }
    } catch (SQLException throwables) {
        throwables.printStackTrace();
    }
    return lastesId;
    }

    @Override
    public List getAllOrders() {
        return null;
    }

    @Override
    public Order getSpecificOrder(Integer id) {
        return null;
    }

    @Override
    public void createOrder(HashMap<Product, Integer> basket, Client client) {

        java.util.Date now = new Date();
        String query = "insert into order_table\n" +
                "values(default, ?,?);";
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            PreparedStatement prepSt = con.prepareStatement(query);
            prepSt.setFloat(1,calculateValueOfBasket(basket));
            prepSt.setDate(2,new java.sql.Date(now.getTime()));
            System.out.println(prepSt);
            prepSt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        String query2 = "insert into order_product\n" +
                "values(?,?,?)";
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            for(HashMap.Entry<Product,Integer> product : basket.entrySet()){
                PreparedStatement prepSt = con.prepareStatement(query2);
                prepSt.setInt(1,getLatestId());
                prepSt.setInt(2,product.getKey().getProductId());
                prepSt.setInt(3,product.getValue());
                prepSt.executeUpdate();

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        String query3 = "insert into customer_order\n" +
                "values(default,?,?)";
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            PreparedStatement prepSt = con.prepareStatement(query3);
            prepSt.setInt(1,client.getID());
            prepSt.setInt(2,getLatestId());
            prepSt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
