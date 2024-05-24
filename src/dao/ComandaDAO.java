package dao;

import models.Produs;
import models.Client;
import models.Comanda;
import utils.DBConnection;
import daoservices.ClientRepositoryService;
import daoservices.ProdusRepositoryService;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ComandaDAO {
    private static int nextId = 1;
    private static List<Comanda> comenzi = new ArrayList<>();
    private Connection connection = DBConnection.getConnection();
    private ClientRepositoryService clientRepositoryService = new ClientRepositoryService();
    private ProdusRepositoryService produsRepositoryService = new ProdusRepositoryService();

    public ComandaDAO() throws SQLException {
    }

    public void creareComanda(Comanda comanda) throws SQLException {
        String sqlComanda = "INSERT INTO comenzi (idclienti) VALUES (?)";
        String sqlComandaProduse = "INSERT INTO comenzi_produse (idcomenzi, idproduse) VALUES (?, ?)";

        try (PreparedStatement comandaStatement = connection.prepareStatement(sqlComanda);
             PreparedStatement comandaProduseStatement = connection.prepareStatement(sqlComandaProduse)) {

            comandaStatement.setInt(1, comanda.getClient().getId());
            int rowsInsertedComanda = comandaStatement.executeUpdate();

            if (rowsInsertedComanda > 0) {
                int comandaId = nextId++;

                comanda.setId(comandaId);

                List<Produs> produse = comanda.getProduse();
                if (produse != null) {
                    for (Produs produs : produse) {
                        comandaProduseStatement.setInt(1, comandaId);
                        comandaProduseStatement.setInt(2, produs.getId());
                        comandaProduseStatement.executeUpdate();
                    }
                }

                comenzi.add(comanda);

                System.out.println("Comanda creată cu succes. ID: " + comandaId);
            } else {
                System.out.println("Eroare la crearea comenzii.");
            }
        }
    }

    public Comanda citireComanda(int id) throws SQLException {
        String sqlComanda = "SELECT * FROM comenzi WHERE idcomenzi = ?";
        try (PreparedStatement statement = connection.prepareStatement(sqlComanda)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int clientId = resultSet.getInt("idclienti");
                    Client client = clientRepositoryService.getClientById(clientId);
                    if (client == null) {
                        return null;
                    }
                    Comanda comanda = new Comanda();
                    comanda.setId(id);
                    comanda.setClient(client);
                    // Retrieve products for the order
                    List<Produs> produse = getProduseForComanda(id);
                    comanda.setProduse(produse);
                    return comanda;
                }
            }
        }
        return null;
    }

    private List<Produs> getProduseForComanda(int comandaId) throws SQLException {
        List<Produs> produse = new ArrayList<>();
        String sqlProduse = "SELECT p.idproduse, p.denumire, p.pret FROM produse p "
                + "INNER JOIN comenzi_produse cp ON p.idproduse = cp.idproduse "
                + "WHERE cp.idcomenzi = ?";
        try (PreparedStatement statement = connection.prepareStatement(sqlProduse)) {
            statement.setInt(1, comandaId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Produs produs = new Produs();
                    produs.setId(resultSet.getInt("idproduse"));
                    produs.setDenumire(resultSet.getString("denumire"));
                    produs.setPret(resultSet.getDouble("pret"));
                    produse.add(produs);
                }
            }
        }
        return produse;
    }



    public void stergereComanda(Comanda comanda) throws SQLException {
        String sqlDeleteComandaProduse = "DELETE FROM comenzi_produse WHERE idcomenzi = ?";
        String sqlDeleteComanda = "DELETE FROM comenzi WHERE idcomenzi = ?";

        try (PreparedStatement deleteComandaProduseStatement = connection.prepareStatement(sqlDeleteComandaProduse);
             PreparedStatement deleteComandaStatement = connection.prepareStatement(sqlDeleteComanda)) {

            connection.setAutoCommit(false);

            try {
                deleteComandaProduseStatement.setInt(1, comanda.getId());
                deleteComandaProduseStatement.executeUpdate();

                deleteComandaStatement.setInt(1, comanda.getId());
                deleteComandaStatement.executeUpdate();

                connection.commit();


                comenzi.remove(comanda);

                System.out.println("Comanda stearsa cu succes. ID: " + comanda.getId());
            } catch (SQLException e) {
                System.out.println("A aparut o eroare la stergerea comenzii: " + e.getMessage());
                connection.rollback();
                throw e;
            } finally {
                connection.setAutoCommit(true);
            }
        }
    }


    public List<Comanda> gasesteToateComenzile() throws SQLException {
        List<Comanda> comenziList = new ArrayList<>();
        String sqlComenzi = "SELECT * FROM comenzi";

        try (PreparedStatement statement = connection.prepareStatement(sqlComenzi);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int comandaId = resultSet.getInt("idcomenzi");
                int clientId = resultSet.getInt("idclienti");

                Client client = clientRepositoryService.getClientById(clientId);
                if (client == null) {
                    continue;
                }

                Comanda comanda = new Comanda();
                comanda.setId(comandaId);
                comanda.setClient(client);

                List<Produs> produse = getProduseForComanda(comandaId);
                comanda.setProduse(produse);

                comenziList.add(comanda);
            }
        }

        return comenziList;
    }

    public static List<Comanda> gasesteComenzileClientului(int idClient) {
        List<Comanda> comenziClient = new ArrayList<>();
        for (Comanda comanda : comenzi) {
            if (comanda.getClient().getId() == idClient) {
                comenziClient.add(comanda);
            }
        }
        return comenziClient;
    }
}
