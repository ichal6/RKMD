import DAO.AdminDatabaseDAO;
import Model.Admin;
import org.w3c.dom.ls.LSOutput;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        AdminDatabaseDAO db = new AdminDatabaseDAO();
//        db.getAllAdmins();
//        List<Admin> adminslist = db.getAdminList();
//        for (Admin admin:adminslist
//        ) {
//            System.out.println(admin.toString());
//
//        }
        String[] newAdmin = new String[5];
        newAdmin[0] = "Natalka";
        newAdmin[1] = "Raba";
        newAdmin[2] = "kaya";
        newAdmin[3] = "gogogo";
        newAdmin[4] = "1";

//        db.addAdmin(newAdmin);
            db.deleteAdmin(5);
//        db.updateAdmin(5,newAdmin);
    }

}
