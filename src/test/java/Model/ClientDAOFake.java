package Model;

import DAO.ClientsDAO;

import java.util.List;

public class ClientDAOFake implements ClientsDAO {

    @Override
    public void getAllClients() {

    }

    @Override
    public void addClient(String[] clientToAdd) {

    }

    @Override
    public void updateClient(Integer user_ID, String[] newAttributes) {

    }

    @Override
    public void deleteClient(Integer user_ID) {

    }

    @Override
    public boolean checkIsClient(String login, String password) {
        return true;
    }

    @Override
    public Client getClient(String login, String password) {
        Builder builder = new Builder().withID("1").withName("Name").withSurname("Surname")
                .withLogin("login").withPassword("Password");
        Client client = new Client(builder);
        return client;
    }

    @Override
    public List<User> getClientList() {
        return null;
    }
}
