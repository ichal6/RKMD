package Controller;

import DAO.*;
import Model.Client;
import Model.Product;

import java.io.IOException;
import java.sql.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;


public class ControllerOrder implements OrdersDAO {

    private String url;
    private String user;
    private String password;

    public ControllerOrder() throws IOException {
        Properties prop = LoginData.readProperties("src/main/resources/database.properties");
        url = prop.getProperty("db.url");
        user = prop.getProperty("db.user");
        password = prop.getProperty("db.passwd");
    }

    private float calculateValueOfBasket(HashMap<Product, Integer> basket) {
        float totalValue = 0;
        for (HashMap.Entry<Product, Integer> product : basket.entrySet()) {
            totalValue += product.getKey().getPrice() * product.getValue();
        }
        return totalValue;
    }

    private Integer getLatestId() {
        Integer lastesId = 0;
        String query = "SELECT order_id FROM order_table ORDER BY order_id DESC LIMIT 1";
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            PreparedStatement prepSt = con.prepareStatement(query);
            ResultSet resultSet = prepSt.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return lastesId;
    }

    @Override
    public void createOrder(HashMap<Product, Integer> basket, Client client) {
        addRecordToOrderTable(basket);
        addRecordToOrderProduct(basket);
        addRecordToCustomerOrder(client);
    }

    public void addRecordToOrderTable(HashMap<Product, Integer> basket) {
        java.util.Date now = new Date();
        String query = "insert into order_table\n" +
                "values(default, ?,?);";
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            PreparedStatement prepSt = con.prepareStatement(query);
            prepSt.setFloat(1, calculateValueOfBasket(basket));
            prepSt.setDate(2, new java.sql.Date(now.getTime()));
            prepSt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void addRecordToOrderProduct(HashMap<Product, Integer> basket) {
        String query = "insert into order_product\n" +
                "values(?,?,?)";
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            for (HashMap.Entry<Product, Integer> product : basket.entrySet()) {
                PreparedStatement prepSt = con.prepareStatement(query);
                prepSt.setInt(1, getLatestId());
                prepSt.setInt(2, product.getKey().getProductId());
                prepSt.setInt(3, product.getValue());
                prepSt.executeUpdate();

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void addRecordToCustomerOrder(Client client) {
        String query = "insert into customer_order\n" +
                "values(default,?,?)";
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            PreparedStatement prepSt = con.prepareStatement(query);
            prepSt.setInt(1, client.getID());
            prepSt.setInt(2, getLatestId());
            prepSt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
