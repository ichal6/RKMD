package Model;

import DAO.AdminDatabaseDAO;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class AdminTest {
    String[] newAdmin = new String[]{"1", "Admin Name", "Admin SureName", "Admin Login", "Admin password"};
    String[] newAdminForDB = new String[]{"Admin Name", "Admin SureName", "Admin Login", "Admin password"};
    AdminDatabaseDAO adminDAO;

    {
        try {
            adminDAO = new AdminDatabaseDAO("src/main/resources/fakedatabase.properties");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void should_add_Admin_into_DB() {
        //when
        adminDAO.addAdmin(newAdminForDB);
        List<User> adminsList;
        adminDAO.getSpecificAdmin("Admin Name");
        adminsList = adminDAO.getAdminList();
        User createdAdmin = adminsList.get(0);
        //then
        assertEquals(createdAdmin.getName(), newAdminForDB[0]);
        assertEquals(createdAdmin.getSurname(), newAdminForDB[1]);
        assertEquals(createdAdmin.getLogin(), newAdminForDB[2]);
        assertEquals(createdAdmin.getPassword(), newAdminForDB[3]);
    }

    @Test
    public void should_create_Admin() {
        User admin = new User(newAdmin);
        assertEquals(newAdmin[0], String.valueOf(admin.getID()));
        assertEquals(newAdmin[1], admin.getName());
        assertEquals(newAdmin[2], admin.getSurname());
        assertEquals(newAdmin[3], admin.getLogin());
        assertEquals(newAdmin[4], admin.getPassword());
    }

//    @Test
//    public void check_if_list_are_the_same() {
//        List<User> adminsList = new ArrayList<>();
//        adminsList.add(new User(new Builder().withID("10")
//                .withName("Michal")
//                .withSurname("Lechowicz")
//                .withLogin("goracymichal")
//                .withPassword("michalpass1")));
//        adminsList.add(new User(new Builder().withID("11")
//                .withName("Dariusz")
//                .withSurname("Raba")
//                .withLogin("goracydariusz")
//                .withPassword("dariuszpass1")));
//        adminsList.add(new User(new String[]{"10","Michal","Lechowicz","goracymichal","michalpass1"}));
//        adminsList.add(new User(new String[]{"11","Dariusz","Raba","goracydariusz","dariuszpass1"}));
//
//        adminDAO.getAllAdmins();
//        List<User> adminsListFromDB = adminDAO.getAdminList();
//
//        assertEquals(adminsList, adminsListFromDB);
//    }


}
