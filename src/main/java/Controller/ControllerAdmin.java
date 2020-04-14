package Controller;

import DAO.AdminDAO;
import DAO.ClientsDAO;
import DAO.ProductDAOAdmin;
import Interaction.InputManager;
import Model.Product;
import View.AbstractView;

import java.util.TreeMap;

public class ControllerAdmin {
    private AbstractView view;
    private InputManager input;
    private AdminDAO adminDAO;
    private ClientsDAO clientDAO;
    private ProductDAOAdmin productDAOAdmin;
    private String[] menuContent = new String[7];
    private String label;

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

    }
    public boolean tryToLogIn(){
        String login = "goracykonrad";
        String password = "konradpass1";
        return adminDAO.checkIsAdmin(login, password);
    }

    private String[] newProductAttributes(){
    String[] attributes = new String[4];
    attributes[0] = input.getStringInput("Please provide with new product name");
    attributes[1] = String.valueOf(input.getIntInput("Please provide with new product price"));
    attributes[2] = input.getStringInput("Please provide with new product color");
    attributes[3] = input.getStringInput("Please provide with new product frame M/F");
    return attributes;
    }
    private void updateQuantityOdProduct(){
        String word = input.getStringInput("Please provide searching product");
        productDAOAdmin.searchProducts(word);
        TreeMap<Product, Integer> product = productDAOAdmin.getProductsList();
        view.print(product);
        Product updateProduct = product.firstKey();
        int updateQuantity = input.getIntInput("Please provide with negative number or positive number to update quantity od product in inventory");
        if(updateQuantity>0){
            productDAOAdmin.updateInventory(updateProduct, updateQuantity);
        }else{
            productDAOAdmin.decreaseQuantity(updateProduct, Math.abs(updateQuantity));
        }
    }

    private boolean switchController(){
        Integer inputuser = input.getIntInput("Please provide with option to choose");
        switch (inputuser){
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
                System.out.println("update product");
                updateQuantityOdProduct();
                break;
            case 6:
                int ID = input.getIntInput("Please provide product ID to be delete");
                productDAOAdmin.deleteProduct(ID);
                break;

        }
        return true;
    }

    public void run() {
        boolean isLogIn = false;
        while (!isLogIn){
            isLogIn = tryToLogIn();
        }
        boolean isRun = true;
        do {
            view.print(menuContent, label);
            isRun = switchController();
        } while (isRun);
    }
}
