package DAO;

import Model.Client;
import Model.UserAbstract;

import java.util.ArrayList;
import java.util.List;

public interface ClientsDAO {
    List<UserAbstract> ClientList = new ArrayList<>();

    void getAllClients();
    void addClient(String [] clientToAdd);
    void updateClient(Integer user_ID, String [] newAttributes);
    void deleteClient(Integer user_ID);

    List<UserAbstract> getClientList();
}
