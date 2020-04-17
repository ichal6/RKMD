package Model;

import Controller.ControllerAdmin;
import DAO.AdminDatabaseDAO;
import DAO.ClientsDatabaseDAO;
import DAO.ProductDBDAO;
import Input.InputManager;
import View.TerminalView;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import static org.junit.Assert.*;
public class AdminTest {
    String[] newAdmin = new String[]{"1","Admin Name","Admin SureName","Admin Login","Admin password"};
    String[] newAdminForDB= new String[]{"Admin Name","Admin SureName","Admin Login","Admin password"};

    ControllerAdmin AC;

    {
        try {
            AC = new ControllerAdmin(new TerminalView(), new InputManager(),new AdminDatabaseDAO(), new ClientDAOFake(), new ProductDBDAO());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void should_add_Admin_into_DB(){
        //when
        AC.addAdmin(newAdminForDB);
        List<User> adminsList;
        adminsList = AC.getSpecificAdmin("Admin Name");
        User createdAdmin = adminsList.get(0);
        //then
        assertEquals(createdAdmin.getName(),newAdminForDB[0]);
        assertEquals(createdAdmin.getSurname(),newAdminForDB[1]);
        assertEquals(createdAdmin.getLogin(),newAdminForDB[2]);
        assertEquals(createdAdmin.getPassword(),newAdminForDB[3]);
    }
    @Test
    public void should_create_Admin(){
        User admin = new User(newAdmin);
        assertEquals(newAdmin[0], String.valueOf(admin.getID()));
        assertEquals(newAdmin[1], admin.getName());
        assertEquals(newAdmin[2],admin.getSurname());
        assertEquals(newAdmin[3],admin.getLogin());
        assertEquals(newAdmin[4],admin.getPassword());
    }


}
