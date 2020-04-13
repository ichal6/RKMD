package View;

import Model.Product;
import Model.UserAbstract;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class TerminalView extends AbstractView {

    @Override
    public void print(String message) {
        System.out.println("\n " + message);
    }

    @Override
    public void print(List<UserAbstract> PersonsList) {
        System.out.println("| ID | First Name | Last Name | Login | Password |\n");
        for (UserAbstract person: PersonsList
        ) {
            System.out.println(person.toString());
        }
    }

    @Override
    public void print(TreeMap<Product, Integer> productsList) {
        for (Map.Entry<Product,Integer> product: productsList.entrySet()
        ) {
            System.out.println(product.getKey().toString() + " " + product.getValue());
        }
    }

    @Override
    public void print(String[] menuContent, String label) {
        System.out.println(label);
        for (String menuItem : menuContent) {
            System.out.println(menuItem);
        }
    }

    @Override
    public void print(HashMap<Product, Integer> basket) {
        for (Map.Entry<Product, Integer> product : basket.entrySet()) {
            System.out.println(product.getKey().toString() + " " + product.getValue());
        }
    }


    public void printEmptyChar(){
        System.out.println(" ");
    }
}
