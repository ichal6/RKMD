package View;

import View.AbstractView;

public class TerminalView extends AbstractView {

    @Override
    public void print(String message) {
        System.out.println("\n " + message);
    }

    public void printEmptyChar(){
        System.out.println(" ");
    }
}
