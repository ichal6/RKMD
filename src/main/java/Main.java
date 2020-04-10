import DAO.AdminDatabaseDAO;
import Model.Admin;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        AdminDatabaseDAO db = new AdminDatabaseDAO();
        db.getAllAdmins();
        List<Admin> adminslist = db.getAdminList();
        for (Admin admin:adminslist
        ) {
            System.out.println(admin.toString());

        }
    }
}
