package DAO;

import Model.User;

import java.util.ArrayList;
import java.util.List;

public interface AdminDAO {
     List<User> AdminList = new ArrayList<>();

     void getAllAdmins();   // return type need to be change for List<Admin>
     void addAdmin(String [] adminToAdd);
     void updateAdmin(Integer user_ID, String[] newAttributes);
     void deleteAdmin(Integer user_ID);
     boolean checkIsAdmin(String login, String password);
     void getSpecificAdmin(String word);
     List<User> getAdminList();
}
