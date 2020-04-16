package Controller;

import DAO.AdminDAO;
import DAO.ClientsDAO;
import DAO.ProductDAOAdmin;
import Interaction.InputManager;
import Model.Product;
import Model.User;
import View.AbstractView;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.TreeMap;

public class ControllerAdmin {
    private AbstractView view;
    private InputManager input;
    private AdminDAO adminDAO;
    private ClientsDAO clientDAO;
    private ProductDAOAdmin productDAOAdmin;
    private String[] menuContent = new String[10];
    private String label;
    private String login;
    private String password;

    public ControllerAdmin(AbstractView view, InputManager input, AdminDAO adminDAo, ClientsDAO clientDAO, ProductDAOAdmin productDAOAdmin ){
    this.view = view;
    this.input = input;
    this.adminDAO= adminDAo;
    this.clientDAO = clientDAO;
    this.productDAOAdmin = productDAOAdmin;
        fillMenuContent();
    }


    public void fillMenuContent(){
        label = "Welcome to Admin Controller";
        menuContent[0] = "0. Log out";
        menuContent[1] = "1. All Admins";
        menuContent[2] = "2. All Clients";
        menuContent[3] = "3. All Products";
        menuContent[4] = "4. Add Product";
        menuContent[5] = "5. Update Product";
        menuContent[6] = "6. Delete Product";
        menuContent[7] = "7. Search specific Admin";
        menuContent[8] = "8. Search specific Products";
        menuContent[9] = "9. Change password";

    }
    public boolean tryToLogIn(){
        login = input.getStringInput("Please provide with login");
        password = input.getStringInput("Please provide with password");
        return adminDAO.checkIsAdmin(login, password);
    }

    public void addAdmin(String[] adminToAdd){
        adminDAO.addAdmin(adminToAdd);
    }

    private String[] newProductAttributes(){
    String[] attributes = new String[4];
    attributes[0] = input.getStringInput("Please provide with new product name");
    attributes[1] = String.valueOf(input.getIntInput("Please provide with new product price"));
    attributes[2] = input.getStringInput("Please provide with new product color");
    attributes[3] = input.getStringInput("Please provide with new product frame M/F");
    return attributes;
    }

    private Product getSpecificProduct() {
        String word = input.getStringInput("Please provide searching product");
        productDAOAdmin.searchProducts(word);
        TreeMap<Product, Integer> product = productDAOAdmin.getProductsList();
        view.print(product);
        Product updateProduct = null;
        try {
            updateProduct = product.firstKey();
        } catch (NoSuchElementException ex) {
            view.print("No such product");
            }
        return updateProduct;
    }
    private void updateQuantityOfProduct(Product updateProduct){
        if (updateProduct != null) {
            int updateQuantity = input.getIntInput("Please provide with negative number or positive number to update quantity od product in inventory");
            if (updateQuantity > 0) {
                productDAOAdmin.updateInventory(updateProduct, updateQuantity);
            } else {
                productDAOAdmin.decreaseQuantity(updateProduct, Math.abs(updateQuantity));
            }
        }
        view.print("Sorry, please try once again.\n\n ");
    }


    public List<User> getSpecificAdmin(String searchingWord) {
        List<User> adminList= new ArrayList<>();
        adminDAO.getSpecificAdmin(searchingWord);
        if (adminDAO.getAdminList().isEmpty()) {
            view.print("There is no such Admin\n");
        } else {
            adminList = adminDAO.getAdminList();
            view.print(adminList);
        }
        return adminList;
    }
    private void changePassword(){
        String[] updateAdmin = new String[4];
        adminDAO.getSpecificAdmin(login);
        User admin = adminDAO.getAdminList().get(0);
        updateAdmin[0] = admin.getName();
        updateAdmin[1] = admin.getSurname();
        updateAdmin[2] = input.getStringInput("please provide with new password");
        updateAdmin[3] = login;
        adminDAO.updateAdmin(admin.getID(),updateAdmin);
    }

    private boolean switchController(){
        int inputUser = input.getIntInput("Please provide with option to choose");
        switch (inputUser){
            case 0:
                return false;
            case 1:
                adminDAO.getAllAdmins();
                view.print(adminDAO.getAdminList());
                break;
            case 2:
                clientDAO.getAllClients();
                view.print(clientDAO.getClientList());
                break;
            case 3:
                view.print(productDAOAdmin.getAllProducts());
                break;
            case 4:
                Product newProduct = new Product(newProductAttributes());
                int quantity = input.getIntInput("Please provide with quantity of product");
                productDAOAdmin.addProductToInventory(newProduct, quantity);
                break;
            case 5:
                updateQuantityOfProduct(getSpecificProduct());
                break;
            case 6:
                int ID = input.getIntInput("Please provide product ID to be delete");
                productDAOAdmin.deleteProduct(ID);
                break;
            case 7:
                String searchingWord = input.getStringInput("Please provide with searching word");
                getSpecificAdmin(searchingWord);
                break;
            case 8:
                getSpecificProduct();
            case 9:
                changePassword();
        }
        return true;
    }

    public void run() {
        boolean isLogIn;
        isLogIn = tryToLogIn();
        while (!isLogIn){
            view.print("Sorry you provide with incorrect login or password");
            String userChoice = input.getStringInput("To Exit press '0' or any key to try one more time");
            if(userChoice.equals("0")){
                break;
            }else{
                isLogIn = tryToLogIn();
            }
        }
        boolean isRun;
        do {
            if(!isLogIn){
                break;
            }
            view.print(menuContent, label);
            isRun = switchController();
        } while (isRun);
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
