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
    String[] newAdmin = new String[]{"Admin Name","Admin SureName","Admin Login","Admin password"};

    ControllerAdmin AC;

    {
        try {
            AC = new ControllerAdmin(new TerminalView(), new InputManager(),new AdminDatabaseDAO(), new ClientsDatabaseDAO(), new ProductDBDAO());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void should_create_Admin(){
        //when
        AC.addAdmin(newAdmin);
        List<User> adminsList;
        adminsList = AC.getSpecificAdmin("Admin Name");
        User createdAdmin = adminsList.get(0);
        //then
        assertEquals(createdAdmin.getName(),newAdmin[0]);
        assertEquals(createdAdmin.getSurname(),newAdmin[1]);
        assertEquals(createdAdmin.getLogin(),newAdmin[2]);
        assertEquals(createdAdmin.getPassword(),newAdmin[3]);
    }



}
