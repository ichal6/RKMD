package Model;


import java.util.HashMap;

public class Client extends UserAbstract {
    HashMap<Product, Integer> basket;

    public Client(String[] dataAboutClient) {
        basket = new HashMap<>();
        ID = dataAboutClient[0];
        name = dataAboutClient[1];
        surname = dataAboutClient[2];
        login = dataAboutClient[3];
        password = dataAboutClient[4];
    }
}
