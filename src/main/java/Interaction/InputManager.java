package Interaction;

import View.TerminalView;

import java.util.Scanner;

public class InputManager {
    private TerminalView view;

    public InputManager(){
        view = new TerminalView();
    }

    public String getStringInput(String message){
        view.print(message);
        view.printEmptyCgar();
        Scanner scnaFromUser = new Scanner(System.in);
        String input = scnaFromUser.nextLine();

        return input;
    }

    private int getIntInput(String message){
        view.print(message);
        view.printEmptyChar();
        int input = 0;
        Scanner scanFromUser = new Scanner(System.in);

        while(!scanFromUser.hasNextInt()){
            view.print("Wrong input! Please insert the integer number.");
            scanFromUser.next();
        }
        input = scanFromUser.nextInt();
        return input;
    }
}
