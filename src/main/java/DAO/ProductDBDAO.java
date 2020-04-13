package DAO;

import Model.Product;

import java.sql.*;
import java.util.Map;
import java.util.TreeMap;

public class ProductDBDAO implements ProductDAOAdmin {

    private TreeMap<Product, Integer> productsList;
    private Connection connectionToDB = null;
    private ResultSet resultSet;
    PreparedStatement preparedStatement;
    public ProductDBDAO() {
        connectToDB();
    }

    String url = "jdbc:postgresql://localhost:5432/online_shop";
    String user = "dariusz";
    String password = "polska";

    private Connection connectToDB() {
        try {
            connectionToDB = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println("Something went wrong.");
        }
        return connectionToDB;
    }

    private ResultSet askForAllBikes() {
        try {
            Statement statement = connectionToDB.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM bike_product;");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return resultSet;
    }

    private void addProductToList(Product product, Integer quantity, TreeMap<Product, Integer> productsList) {
        productsList.put(product, quantity);
    }

    public void display() {
        for(Map.Entry<Product,Integer> product : productsList.entrySet()){
            System.out.println(product.getKey().toString() + " " + product.getValue());
        }
    }
    private boolean checkIfProductExistInList(Product product){
        String query = "select * from bike_product where name like ? and price = ? and color like ? and Type_of_frame like ?;";
        try {
            PreparedStatement preparedStatement = connectionToDB.prepareStatement(query);
            preparedStatement.setString(1,product.getProductName());
            preparedStatement.setFloat(2, product.getPrice());
            preparedStatement.setString(3,product.getColor());
            preparedStatement.setString(4,product.getFrameType());
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
    public Integer getCurrentQuantity(Product product){
        Integer currentQuantity = 0;
        String query =
                "select Quantity from bike_product where name like ? or color like ?";
        try {
            preparedStatement = connectionToDB.prepareStatement(query);
            preparedStatement.setString(1,product.getProductName());
            preparedStatement.setString(2,product.getColor());
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                return currentQuantity = resultSet.getInt(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return currentQuantity;
    }

    //
//    private boolean checkIfProductExistInList(Product product){
//        getAllProducts();
//        for (HashMap.Entry<Product, Integer> productInList : productsList.entrySet()) {
//            if(productInList.getKey().equals(product)){
//                System.out.println("Product is already in list.");
//                return true;
//            }
//        }
//        return false;
//    }

    @Override
    public void addProductToInventory(Product product, Integer quantity) {
        if (checkIfProductExistInList(product) == false) {
            String query = "INSERT INTO bike_product VAlUES(DEFAULT, ?,?,?,?,?);";
            try {
                PreparedStatement preparedStatement = connectionToDB.prepareStatement(query);
                preparedStatement.setString(1,product.getProductName());
                preparedStatement.setFloat(2, product.getPrice());
                preparedStatement.setString(3,product.getColor());
                preparedStatement.setString(4,product.getFrameType());
                preparedStatement.setInt(5,quantity);
                preparedStatement.executeUpdate();
            } catch (SQLException throwables) {

                throwables.printStackTrace();
            }
        }else{
            updateInventory(product,quantity);
        }
    }

    @Override
    public void deleteProduct(Integer id) {
        String query = "delete from bike_product where Product_ID = ?";
        try {
            preparedStatement = connectionToDB.prepareStatement(query);
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void updateInventory(Product product, Integer quantity) {
        Integer currentQuantity =  getCurrentQuantity(product);
        Integer newQuantity = currentQuantity + quantity;
        String query = "update bike_product set Quantity = ? where name = ? and color = ? and Type_of_frame = ?";
        try {
            preparedStatement = connectionToDB.prepareStatement(query);
            preparedStatement.setInt(1,newQuantity);
            preparedStatement.setString(2,product.getProductName());
            preparedStatement.setString(3,product.getColor());
            preparedStatement.setString(4,product.getFrameType());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public TreeMap<Product, Integer> getAllProducts() {
        productsList = new TreeMap<>();
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
    public void searchProducts(String word) {
        productsList = new TreeMap<>();
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
    }

    @Override
    public void decreaseQuantity(Product product,Integer quantity) {
        Integer currentQuantity =  getCurrentQuantity(product);
        Integer newQuantity = currentQuantity - quantity;
        String query = "update bike_product set Quantity = ? where name = ? and color = ? and Type_of_frame = ?";
        try {
            preparedStatement = connectionToDB.prepareStatement(query);
            preparedStatement.setInt(1,newQuantity);
            preparedStatement.setString(2,product.getProductName());
            preparedStatement.setString(3,product.getColor());
            preparedStatement.setString(4,product.getFrameType());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
