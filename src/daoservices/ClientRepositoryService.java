package daoservices;

import models.Client;
import dao.ClientDAO;
import models.Comanda;
import dao.ComandaDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
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

    public List<Comanda> getComenziByClientId(int id) {
        return ComandaDAO.gasesteComenzileClientului(id);
    }


}
