package Controller;

import DAO.AdminDAO;
import DAO.ClientsDAO;
import DAO.ProductDAOAdmin;
import Interaction.InputManager;
import View.AbstractView;

public class ControllerAdmin {
    private AbstractView view;
    private InputManager input;
    private AdminDAO adminDAO;
    private ClientsDAO clientDAO;
    private ProductDAOAdmin productDAOAdmin;
    private String[] menuContent = new String[7];

    public ControllerAdmin(AbstractView view, InputManager input, AdminDAO adminDAo, ClientsDAO clientDAO, ProductDAOAdmin productDAOAdmin ){
    this.view = view;
    this.input = input;
    this.adminDAO= adminDAo;
    this.clientDAO = clientDAO;
    this.productDAOAdmin = productDAOAdmin;
        fillMenuContent();
    }

    public void fillMenuContent(){
        menuContent[0] = "0. Log out";
        menuContent[1] = "1. All Admins";
        menuContent[2] = "2. All Clients";
        menuContent[3] = "3. All Products";
        menuContent[4] = "4. Add Product";
        menuContent[5] = "5. Update Product";
        menuContent[6] = "6. Delete Product";

    }
}
