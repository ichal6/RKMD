package View;

import Model.Admin;
import Model.UserAbstract;
import View.AbstractView;

import java.sql.SQLOutput;
import java.util.List;

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

    public void printEmptyChar(){
        System.out.println(" ");
    }
}
