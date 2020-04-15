import Controller.ControllerMain;
import DAO.AdminDatabaseDAO;
import Interaction.InputManager;
import View.TerminalView;
import java.io.IOException;


public class Main {

    public static void main(String[] args) {
//        ControllerMain main = null;
//        try {
//            main = new ControllerMain(new TerminalView(),new InputManager());
//            main.run();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        try {
            AdminDatabaseDAO db = new AdminDatabaseDAO();
//            String[] newUser = new String[]{"Daroslawa","MiroslawAAA","zow ocowa","goracydaroslaw"};
//            db.updateAdmin(9,newUser);
            db.deleteAdmin(8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}