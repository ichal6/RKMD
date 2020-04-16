package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class Product implements Comparable<Product> {

    private int productId;
    private String productName;
    private float productPrice;
    private String productColor;
    private String frameType;

    public Product() {
    }

    public static final class Builder {
        private int productId;
        private String productName;
        private float productPrice;
        private String productColor;
        private String frameType;

        public Builder productId(int productId) {
            this.productId = productId;
            return this;
        }

        public Builder productName(String productName) {
            this.productName = productName;
            return this;
        }

        public Builder productPrice(float productPrice) {
            this.productPrice = productPrice;
            return this;
        }

        public Builder productColor(String productColor) {
            this.productColor = productColor;
            return this;
        }

        public Builder frameType(String frameType) {
            this.frameType = frameType;
            return this;
        }

        public Product build() {

            Product product = new Product();
            product.productId = this.productId;
            product.productName = this.productName;
            product.productPrice = this.productPrice;
            product.productColor = this.productColor;
            product.frameType = this.frameType;
            return product;
        }
    }


    public Product(String[] productAttributes) {
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
        String returnValue;
        returnValue = String.format("| %-5d | %-20s | %-20.2f | %-20s | %-20s ",
                getProductId(),
                getProductName(),
                getPrice(),
                getColor(),
                getFrameType());
        return returnValue;
    }

    @Override
    public int compareTo(Product product) {
        return this.getProductId() - product.getProductId();
    }
}
