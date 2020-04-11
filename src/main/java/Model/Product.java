package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class Product implements Comparable<Product>{

    private int productId;
    private String productName;
    private float productPrice;
    private String productColor;
    private String frameType;

    public Product(String[] productAttributes){
            this.productName = productAttributes[0];
            this.productPrice = Integer.parseInt(productAttributes[1]);
            this.productColor = productAttributes[2];
            this.frameType = productAttributes[3];
    }

    public Product(ResultSet resultSet) throws SQLException {
        int productId = 1;
        int productName = 2;
        int productPrice = 3;
        int productColor = 4;
        int frameType = 5;

        this.productId = Integer.parseInt(resultSet.getString(productId));
        this.productName = resultSet.getString(productName);
        this.productPrice = resultSet.getFloat(productPrice);
        this.productColor = resultSet.getString(productColor);
        this.frameType = resultSet.getString(frameType);
    }

    public Integer getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public float getPrice() {
        return productPrice;
    }

    public String getColor() {
        return productColor;
    }

    public String getFrameType() {
        return frameType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return productId == product.productId &&
                Float.compare(product.productPrice, productPrice) == 0 &&
                productName.equals(product.productName) &&
                productColor.equals(product.productColor) &&
                frameType.equals(product.frameType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, productName, productPrice, productColor, frameType);
    }

    @Override
    public String toString() {
        return productId + " " + productName + " " + productPrice + " " + productColor + " " + frameType;
    }

    @Override
    public int compareTo(Product product) {
//        return this.productName.compareTo(product.productName);
        return this.getProductId() - product.getProductId();
    }
}
