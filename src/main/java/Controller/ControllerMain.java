package Controller;

import DAO.*;
import Input.InputManager;
import View.AbstractView;

import java.io.IOException;

public class ControllerMain {
    private String[] menuContent = new String[5];
    private String label = "Please choose options";
    private ControllerShop controllerShop;
    private ControllerAdmin controllerAdmin;
    private AbstractView view;
    private InputManager input;

    public ControllerMain(AbstractView view, InputManager input) throws IOException {
        controllerShop = new ControllerShop(view, input, new ProductDBDAO());
        controllerAdmin = new ControllerAdmin(view, input, new AdminDatabaseDAO(), new ClientsDatabaseDAO(),new ProductDBDAO());
        this.view = view;
        this.input = input;
        fillMenuContent();

    }

    public void run() throws IOException{
        boolean isRun;
        do{
            isRun = switchController();
        }while(isRun);
    }

    private void fillMenuContent() {
        menuContent[0] = "0. Exit";
        menuContent[1] = "1. Login as User";
        menuContent[2] = "2. Login as Admin";
        menuContent[3] = "3. Create new user";
        menuContent[4] = "4. Create new Admin";
    }

    private boolean switchController() throws IOException {
        view.print(menuContent, label);
        Integer inputInt = input.getIntInput("Please provide option.");
        switch (inputInt) {
            case 0:
                return false;
            case 1:
                controllerShop.run();
                break;
            case 2:
                controllerAdmin.run();
                break;
            case 3:
                String[] dataAboutClient = createNewUser();
                controllerShop.addUser(dataAboutClient);
                break;
            case 4:
                createNewAdmin();
                break;
        }
        return true;
    }

    private String[] createNewUser(){
        String[] data = new String[4];
        String[] questions = {"name", "surname", "login", "password"};
        for(int index = 0; index < questions.length; index++){
            data[index] = input.getStringInput(String.format("Please provide %s:", questions[index]));
            if(data[index].length() == 0){
                view.print("You cannot provide empty value!");
                index--;
            }
        }
        return data;
    }

    private void createNewAdmin(){
        view.print("Please log in to actual admin: ");
        if(controllerAdmin.tryToLogIn()){
            view.print("Login successful! Please provide new admin:");
            controllerAdmin.addAdmin(createNewUser());
        }else{
            view.print("You provide wrong login or password! Back to main menu.");
        }
    }

}
