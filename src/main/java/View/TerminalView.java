package View;

import Model.Product;
import Model.UserAbstract;
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
        String ID = "ID";
        String First_Name = "First Name";
        String Last_Name = "Last Name";
        String Login = "Login";
        String Menu = String.format("| %-5s | %-20s | %-20s | %-20s |",
                                    ID,
                                    First_Name,
                                    Last_Name,
                                    Login);
        System.out.println(Menu);
        for (UserAbstract person: PersonsList
        ) {
            System.out.println(person.toString());
        }
    }

    @Override
    public void print(TreeMap<Product, Integer> productsList) {
        String ID = "ID";
        String Product_Name = "Product Name";
        String Product_Price = "Product Price";
        String Product_Color = "Product Color";
        String Frame_Type = "Frame Type";
        String Quantity = "Quantity";
        String Menu = String.format("| %-5s | %-20s | %-20s | %-20s | %-20s | %s",
                ID,
                Product_Name,
                Product_Price,
                Product_Color,
                Frame_Type,
                Quantity);
        System.out.println(Menu);
        for (Map.Entry<Product,Integer> product: productsList.entrySet()
        ) {
            System.out.println(product.getKey().toString() + "| " + product.getValue());
        }
    }

    @Override
    public void print(String[] menuContent, String label) {
        System.out.println(label);
        for (String menuLine:menuContent
             ) {
            System.out.println(menuLine);
        }
    }


    public void printEmptyChar(){
        System.out.println(" ");
    }
}
