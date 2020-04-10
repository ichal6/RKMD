package DAO;

import Model.Admin;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AdminDatabaseDAO implements AdminDAO {
    private String url = "jdbc:postgresql://localhost:5432/online_shop";
    private String user = "dariusz";
    private String password = "polska";
    private List<Admin> AdminList;

    public void updateDB(String query){
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            PreparedStatement pst = con.prepareStatement(query);
            pst.executeUpdate();
            con.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void getAllAdmins() {
        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = con.prepareStatement("SELECT * FROM user_table");
             ResultSet rs = pst.executeQuery()) {
            int attributesNumber = rs.getMetaData().getColumnCount();
            AdminList = new ArrayList<>();
            String[] adminAttributes = new String[attributesNumber];
            while (rs.next()) {
            for(int index = 0;index < attributesNumber; index++){
                adminAttributes[index] = rs.getString(index+1);
            }
            Admin admin = new Admin(adminAttributes);
            AdminList.add(admin);
            con.close();
            }

        } catch (SQLException ex) {

            Logger lgr = Logger.getLogger(AdminDatabaseDAO.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }


    @Override
    public void addAdmin(String [] adminToAdd) {
        String date = getActualDate();
        String AddToAccountDetailsStatement = String.format("INSERT INTO accountdetails VALUES (DEFAULT, '%s', '%s', '%s')",
                date,
                adminToAdd[2],
                adminToAdd[3]);
        String AddToUser_tableStatement = String.format("INSERT INTO User_table VALUES (DEFAULT, '%s', '%s', '%d', DEFAULT)",
            adminToAdd[0],
            adminToAdd[1],
            Integer.parseInt(adminToAdd[4])
            );
        updateDB(AddToAccountDetailsStatement);
        updateDB(AddToUser_tableStatement);
    }
    public String getActualDate(){
        java.util.Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(date);
    }

    @Override
    public void updateAdmin() {

    }

    @Override
    public void deleteAdmin(Integer user_ID) {
    String deleteFromUserStatement = String.format("DELETE FROM User_table WHERE user_id = '%d'",
            user_ID);
    String deleteFromAccountDetail = String.format("DELETE FROM accountdetails WHERE accountdetails_id = '%d'" ,
                user_ID);
    updateDB(deleteFromUserStatement);
    updateDB(deleteFromAccountDetail);
    }


    @Override
    public List<Admin> getAdminList() {
        return AdminList;
    }
}
