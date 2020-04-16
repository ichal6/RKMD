package Input;

import View.TerminalView;

import java.util.Scanner;

public class InputManager {

    private TerminalView view;

    public InputManager(){
        view = new TerminalView();
    }

    public String getStringInput(String message){
        view.print(message);
        view.printEmptyChar();
        Scanner scanFromUser = new Scanner(System.in);
        String input = scanFromUser.nextLine();

        return input;
    }

    public int getIntInput(String message){
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
    public String askForAttribute(String attribute){
        String queryForUser = "";
        String usersNewAttribute = "";
        queryForUser = String.format("Could you please provide me with new %s?",
                attribute);
        usersNewAttribute = getStringInput(queryForUser);
        while(usersNewAttribute.length()<1){
            usersNewAttribute = getStringInput("Cannot be empty," + queryForUser);
        }
        return usersNewAttribute;
    }
}
