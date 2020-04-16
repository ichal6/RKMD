package DAO;

import Model.User;
import View.TerminalView;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AdminDatabaseDAO implements AdminDAO {
    private String url;
    private String user;
    private String password;
    private List<User> AdminList;
    private TerminalView view;

    public AdminDatabaseDAO() throws IOException {
        Properties prop = loginData.readProperties("src/main/resources/database.properties");
        url = prop.getProperty("db.url");
        user = prop.getProperty("db.user");
        password = prop.getProperty("db.passwd");
        view = new TerminalView();
    }


    private void deleteFromAccountDetails(Integer user_ID){
        String deleteStatement= ("DELETE FROM accountdetails WHERE accountdetails_id = ?");
        try (Connection con = DriverManager.getConnection(url, user, password);
            PreparedStatement pst = con.prepareStatement(deleteStatement))
        {
            pst.setInt(1,user_ID);
            pst.executeUpdate();

        } catch (SQLException throwables) {
//            throwables.printStackTrace();
            view.print("Something went wrong in DB AccountDetails");
        }
    }


    private void addAdminToAccountDetails(String [] adminToAdd){
        java.util.Date now = new Date();
        String AddToAccountDetailsStatement = ("INSERT INTO accountdetails VALUES (DEFAULT, ?, ?, ?)");
        try (Connection con = DriverManager.getConnection(url, user, password);
        PreparedStatement pst = con.prepareStatement(AddToAccountDetailsStatement))
        {
            pst.setDate(1,new java.sql.Date(now.getTime()));
            pst.setString(2,adminToAdd[3]);
            pst.setString(3,adminToAdd[2]);
            pst.executeUpdate();
            con.close();
        } catch (SQLException throwables) {
//            throwables.printStackTrace();
            view.print("Something went wrong in DB AccountDetail");
        }
    }


    private void updateAdminsAccountDetails(Integer acc_ID, String[] newAttributes){
       String updateStatement = ("UPDATE accountdetails SET password = ?, login = ? WHERE accountdetails_id = ?");
        try(Connection con = DriverManager.getConnection(url, user, password);
            PreparedStatement pst = con.prepareStatement(updateStatement))
        {
            pst.setString(1,newAttributes[2]);
            pst.setString(2,newAttributes[3]);
            pst.setInt(3,acc_ID);
            pst.executeUpdate();

        } catch (SQLException throwables) {
//            throwables.printStackTrace();
            view.print("Something went wrong in DB accountDetails");
        }
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
            User admin = new User(adminAttributes);
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
        String AddToUser_tableStatement = "INSERT INTO User_table VALUES (DEFAULT, ?, ?, '1', DEFAULT)";
        try (Connection con = DriverManager.getConnection(url, user, password);
              PreparedStatement pst = con.prepareStatement(AddToUser_tableStatement))
        {
          pst.setString(1, adminToAdd[0]);
          pst.setString(2, adminToAdd[1]);
          pst.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            view.print("Something went wrong in DB User");
        }
    }


    @Override
    public void updateAdmin(Integer user_ID, String[] newAttributes) {
        updateAdminsAccountDetails(user_ID, newAttributes);
        String updateStatement = ("UPDATE user_table SET first_name = ?, last_name = ? WHERE user_id = ?");
        try(Connection con = DriverManager.getConnection(url, user, password);
            PreparedStatement pst = con.prepareStatement(updateStatement))
        {
            pst.setString(1,newAttributes[0]);
            pst.setString(2,newAttributes[1]);
            pst.setInt(3,user_ID);
            pst.executeUpdate();

        } catch (SQLException throwables) {
//            throwables.printStackTrace();
            view.print("Something went wrong in DB User");
        }
    }


    @Override
    public void deleteAdmin(Integer user_ID) {
        String deleteFromUserStatement = ("DELETE FROM User_table WHERE user_id = ?");
        try(Connection con = DriverManager.getConnection(url, user, password);
            PreparedStatement pst = con.prepareStatement(deleteFromUserStatement))
        {
            pst.setInt(1,user_ID);
            pst.executeUpdate();

        } catch (SQLException throwables) {
//            throwables.printStackTrace();
            view.print("Something went wrong in DB User");
        }
        deleteFromAccountDetails(user_ID);
    }


    @Override
    public boolean checkIsAdmin(String login, String password) {
        try
            (Connection con = DriverManager.getConnection(url, user, this.password);
            PreparedStatement pst = con.prepareStatement("SELECT user_id, first_name, last_name from user_table INNER JOIN accountdetails ON user_table.account_details_id = accountdetails.accountdetails_id WHERE admin_user = '1' AND login = ? AND password = ?");
                ){
                pst.setString(1, login);
                pst.setString(2, password);
                ResultSet rs = pst.executeQuery();

                if(rs.next()){
                    return true;
            }

        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(AdminDatabaseDAO.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return false;
    }


    @Override
    public void getSpecificAdmin(String word) {
        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = con.prepareStatement("SELECT user_id, first_name, last_name, login, password FROM user_table INNER JOIN accountdetails ON user_table.account_details_id = accountdetails.accountdetails_id WHERE admin_user = '1' AND first_name = ? OR last_name = ? OR login = ?")
        ) {
            pst.setString(1, word);
            pst.setString(2, word);
            pst.setString(3, word);
            ResultSet rs = pst.executeQuery();

            int attributesNumber = rs.getMetaData().getColumnCount();
            AdminList = new ArrayList<>();
            String[] adminAttributes = new String[attributesNumber];

            while (rs.next()) {
                for (int index = 0; index < attributesNumber; index++) {
                    adminAttributes[index] = rs.getString(index + 1);
                }
                User admin = new User(adminAttributes);
                AdminList.add(admin);
            }
        } catch (SQLException throwables) {
//        throwables.printStackTrace();
            System.out.println("Something went wrong in DB");
        }
    }


        @Override
    public List<User> getAdminList() {
        return AdminList;
    }
}
