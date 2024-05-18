package daoservices;

import dao.ClientDAO;
import models.Client;

import java.util.List;

public class ClientRepositoryService {
    private final ClientDAO clientDAO = new ClientDAO();

    public void adaugaClient(Client client) {
        clientDAO.creareClient(client);
    }

    public Client getClientById(int id) {
        return clientDAO.citireClient(id);
    }

    public void actualizeazaClient(int id, Client clientActualizat) {
        clientDAO.actualizareClient(id, clientActualizat);
    }

    public void stergeClient(Client client) {
        clientDAO.stergereClient(client);
    }

    public List<Client> getAllClienti() {
        return clientDAO.gasesteTotiClientii();
    }
}
