package DAO;

import Model.Client;

import java.util.ArrayList;
import java.util.List;

public interface ClientsDAO {
    List<Client> ClientList = new ArrayList<>();

    void getAllClients();
    void addClient(String [] clientToAdd);
    void updateClient(Integer user_ID, String [] newAttributes);
    void deleteClient(Integer user_ID);

    List<Client> getClientList();

    boolean checkIsClient(String login, String password);
    Client getClient(String login, String password);
}
