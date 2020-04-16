package Model;

import Controller.ControllerClient;
import Interaction.InputManager;
import View.TerminalView;
import org.junit.Test;

import static org.junit.Assert.*;

public class ClientTest {
    String[] productData = {"name", "1234", "color", "typeOfFrame"};
    ControllerClient controllerClient;

    public ClientTest(){
        controllerClient = new ControllerClient(new TerminalView(), new InputManager(), new ClientDAOFake());
        controllerClient.logIn("Simple","simple");
    }

    @Test
    public void should_add_two_count_of_simple_product_to_basket() {
        //given
        int quantitySimple = 2;
        Product productSimple = new Product(productData);
        //when
        controllerClient.addToBasket(productSimple, quantitySimple);
        int expected = controllerClient.getBasket().get(productSimple);
        //then
        assertEquals(quantitySimple, expected);
    }

    @Test
    public void should_add_four_count_of_simple_product_to_basket() {
        //given
        int quantitySimple = 2;
        int expected = 4;
        Product productSimple = new Product(productData);
        //when
        controllerClient.addToBasket(productSimple, quantitySimple);
        controllerClient.addToBasket(productSimple, quantitySimple);
        int result = controllerClient.getBasket().get(productSimple);
        //then
        assertEquals(result, expected);
    }

    @Test
    public void should_add_one_count_of_simple_product_to_basket() {
        //given
        int quantitySimple = 1;
        int expected = 1;
        Product productSimple = new Product(productData);
        //when
        controllerClient.addToBasket(productSimple, quantitySimple);
        int result = controllerClient.getBasket().get(productSimple);
        //then
        assertEquals(result, expected);
    }

    @Test
    public void should_remove_one_product_from_basket() {
        //given
        int quantitySimple = 1;
        Product productSimple = new Product(productData);
        //when
        controllerClient.addToBasket(productSimple, quantitySimple);
        controllerClient.removeFromBasket(productSimple);
        boolean isProduct = controllerClient.getBasket().containsKey(productSimple);
        //then
        assertFalse(isProduct);
    }
}