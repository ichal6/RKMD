package DAO;

import Model.Admin;

import java.util.ArrayList;
import java.util.List;

public interface AdminDAO {
    public List<Admin> AdminList = new ArrayList<>();

    public void getAllAdmins();   // return type need to be change for List<Admin>
    public void addAdmin(String [] adminToAdd);
    public void updateAdmin();
    public void deleteAdmin();

    public List<Admin> getAdminList();
}
