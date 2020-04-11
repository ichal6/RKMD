package View;

import Model.Admin;
import View.AbstractView;

import java.util.List;

public class TerminalView extends AbstractView {

    @Override
    public void print(String message) {
        System.out.println("\n " + message);
    }

    @Override
    public void print(List<Admin> AdminsList) {
        for (Admin person: AdminsList
        ) {
            System.out.println(person.toString());
        }
    }

    public void printEmptyChar(){
        System.out.println(" ");
    }
}
