package Model;

import org.junit.Test;

import static org.junit.Assert.*;

public class ClientTest {
    String[] dataOfClient = {"ID", "name", "surname", "login", "password"};
    String[] productData = {"name", "1234", "color", "typeOfFrame"};

    @Test
    public void should_add_two_count_of_simple_product_to_basket() {
        //given
        int quantitySimple = 2;
        Client clientSimple = new Client(dataOfClient);
        Product productSimple = new Product(productData);
        //when
        clientSimple.addToBasket(productSimple, quantitySimple);
        int expected = clientSimple.getBasket().get(productSimple);
        //then
        assertEquals(quantitySimple, expected);
    }

    @Test
    public void should_add_four_count_of_simple_product_to_basket() {
        //given
        int quantitySimple = 2;
        int expected = 4;
        Client clientSimple = new Client(dataOfClient);
        Product productSimple = new Product(productData);
        //when
        clientSimple.addToBasket(productSimple, quantitySimple);
        clientSimple.addToBasket(productSimple, quantitySimple);
        int result = clientSimple.getBasket().get(productSimple);
        //then
        assertEquals(result, expected);
    }

    @Test
    public void removeFromBasket() {
    }
}