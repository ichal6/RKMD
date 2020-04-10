package DAO;

import Model.Product;

import java.sql.*;
import java.util.HashMap;

public class ProductDBDAO implements ProductDAOAdmin {

    private HashMap<Product, Integer> productsList;
    private Connection connectionToDB = null;
    private ResultSet resultSet;

    public ProductDBDAO() {
        connectToDB();
    }

    String url = "jdbc:postgresql://localhost:5432/online_shop";
    String user = "rafal";
    String password = "rafal";

    private Connection connectToDB() {
        try {
            connectionToDB = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
//            e.printStackTrace();
            System.out.println("Something went wrong.");
        }
        return connectionToDB;
    }

    private ResultSet askForAllBikes() {
        try {
            Statement statement = connectionToDB.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM bike_product");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return resultSet;
    }

    private void addProductToList(Product product, Integer quantity, HashMap<Product, Integer> productsList) {
        productsList.put(product, quantity);
    }

//    public void display() {
//        for (HashMap.Entry<Product, Integer> product : productsList.entrySet()) {
//            System.out.println(product.getKey().toString() + " " + product.getValue());
//        }
//    }


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
    public HashMap<Product, Integer> getAllProducts() {
        productsList = new HashMap<>();
        resultSet = askForAllBikes();
        try {
            while (resultSet.next()) {
                Product product = new Product(resultSet);
                Integer quantity = resultSet.getInt(6);
                addProductToList(product, quantity, productsList);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return productsList;
    }

    @Override
    public HashMap<Product, Integer> searchProducts(String word) {
        productsList = new HashMap<>();
        String query =
                "select * from bike_product where name like ? or cast(price as text) like ? or color like ?";

        try {
            PreparedStatement preparedStatement = connectionToDB.prepareStatement(query);
            preparedStatement.setString(1, word);
            preparedStatement.setString(2, word);
            preparedStatement.setString(3, word);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next() == false) {
                System.out.println("No result to display");
            } else {
                while (resultSet.next()) {
                    Product product = new Product(resultSet);
                    Integer quantity = resultSet.getInt(6);
                    addProductToList(product, quantity, productsList);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void decreaseQuantity(Product product) {

    }
}
