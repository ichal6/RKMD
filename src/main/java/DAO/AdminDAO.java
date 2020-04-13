package DAO;

import Model.Admin;
import Model.UserAbstract;

import java.util.ArrayList;
import java.util.List;

public interface AdminDAO {
     List<UserAbstract> AdminList = new ArrayList<>();

     void getAllAdmins();   // return type need to be change for List<Admin>
     void addAdmin(String [] adminToAdd);
     void updateAdmin(Integer user_ID, String[] newAttributes);
     void deleteAdmin(Integer user_ID);

     List<UserAbstract> getAdminList();
}
