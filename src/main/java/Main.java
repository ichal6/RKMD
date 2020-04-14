import Controller.ControllerAdmin;
import DAO.AdminDatabaseDAO;
import DAO.ClientsDatabaseDAO;
import DAO.ProductDBDAO;
import Interaction.InputManager;
import View.TerminalView;

public class Main {

    public static void main(String[] args) {
        ControllerAdmin ctrl = new ControllerAdmin(new TerminalView(), new InputManager(), new AdminDatabaseDAO(), new ClientsDatabaseDAO(), new ProductDBDAO());
        ctrl.run();
//                         AdminDatabaseDAO db = new AdminDatabaseDAO();
//
//                         String[] person = new String[5];
//                         person[0] = "Jan 3";
//                         person[1] = "Sobieski";
//                         person[2] = "password";
//                         person[3] = "login";
//                         person[4] = "1";
//                         db.addAdmin(person);
//        ProductDBDAO db = new ProductDBDAO();
//        db.searchProducts("Wigry");
//        TerminalView view = new TerminalView();
//        view.print(db.getProductsList());
//        db.getSpecificAdmin("goracymichal");
//        view.print(db.getAdminList());

    }
}