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


    public String getActualDate(){
        java.util.Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(date);
    }


    public void deleteFromAccountDetails(Integer user_ID){
        String deleteStatement= String.format("DELETE FROM accountdetails WHERE accountdetails_id = %d" ,
                user_ID);
        updateDB(deleteStatement);
    }


    public void addAdminToAccountDetails(String [] adminToAdd){
        String date = getActualDate();
        String AddToAccountDetailsStatement = String.format("INSERT INTO accountdetails VALUES (DEFAULT, '%s', '%s', '%s')",
                date,
                adminToAdd[2],
                adminToAdd[3]);
        updateDB(AddToAccountDetailsStatement);
    }


    public void updateAdminsAccountDetails(Integer acc_ID, String[] newAttributes){
       String updateStatement = String.format("UPDATE accountdetails SET password = '%s', login = '%s' WHERE accountdetails_id = %d",
            newAttributes[2],
            newAttributes[3],
            acc_ID);
       updateDB(updateStatement);
    }


    @Override
    public void getAllAdmins() {
        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = con.prepareStatement("select user_id, first_name, last_name, login, password from user_table inner join accountdetails on user_table.account_details_id=accountdetails.accountdetails_id where admin_user = '1'");
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
        addAdminToAccountDetails(adminToAdd);
        String AddToUser_tableStatement = String.format("INSERT INTO User_table VALUES (DEFAULT, '%s', '%s', '%d', DEFAULT)",
            adminToAdd[0],
            adminToAdd[1],
            Integer.parseInt(adminToAdd[4]));
        updateDB(AddToUser_tableStatement);
    }


    @Override
    public void updateAdmin(Integer user_ID, String[] newAttributes) {
        updateAdminsAccountDetails(user_ID, newAttributes);        //since accountdetail_ID will be always same as user_ID
        String updateStatement = String.format("UPDATE user_table SET first_name = '%s', last_name = '%s' WHERE user_id = %d",
               newAttributes[0],
                newAttributes[1],
                user_ID);
        updateDB(updateStatement);
    }

    @Override
    public void deleteAdmin(Integer user_ID) {
        String deleteFromUserStatement = String.format("DELETE FROM User_table WHERE user_id = '%d'",
            user_ID);
        updateDB(deleteFromUserStatement);
        deleteFromAccountDetails(user_ID);
    }


    @Override
    public List<Admin> getAdminList() {
        return AdminList;
    }
}
