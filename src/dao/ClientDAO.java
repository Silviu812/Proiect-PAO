package dao;

import models.Client;
import utils.*;

import java.sql.*;
import java.sql.SQLException;
import java.util.*;

public class ClientDAO {
    private static int nextId = 1;
    private static List<Client> clienti = new ArrayList<>();
    private Connection connection = DBConnection.getConnection();

    public ClientDAO() throws SQLException {
    }

    public void creareClient(Client client) {
        String sql = "INSERT INTO clienti (nume, prenume, email, parola, adresalivrare) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, client.getNume());
            statement.setString(2, client.getPrenume());
            statement.setString(3, client.getEmail());
            statement.setString(4, client.getParola());
            statement.setString(5, client.getAdresaLivrare());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Client adăugat cu succes.");
            } else {
                System.out.println("A apărut o eroare la adăugarea clientului.");
            }
        } catch (SQLException e) {
            System.out.println("A apărut o eroare la adăugarea clientului: " + e.getMessage());
        }
    }

    public Client citireClient(int id) {
        String sql = "SELECT * FROM clienti WHERE idclienti = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Client client = new Client(
                        resultSet.getInt("idclienti"),
                        resultSet.getString("nume"),
                        resultSet.getString("prenume"),
                        resultSet.getString("email"),
                        resultSet.getString("parola"),
                        resultSet.getString("adresalivrare")
                );
                return client;
            } else {
                System.out.println("Clientul cu ID-ul " + id + " nu a fost găsit.");
                return null;
            }
        } catch (SQLException e) {
            System.out.println("A apărut o eroare la căutarea clientului: " + e.getMessage());
            return null;
        }
    }

    public void actualizareClient(int id, Client clientActualizat) {
        String sql = "UPDATE clienti SET nume = ?, prenume = ?, email = ?, parola = ?, adresalivrare = ? WHERE idclienti = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, clientActualizat.getNume());
            statement.setString(2, clientActualizat.getPrenume());
            statement.setString(3, clientActualizat.getEmail());
            statement.setString(4, clientActualizat.getParola());
            statement.setString(5, clientActualizat.getAdresaLivrare());
            statement.setInt(6, id);

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Clientul a fost actualizat cu succes.");
            } else {
                System.out.println("Nu s-a putut actualiza clientul.");
            }
        } catch (SQLException e) {
            System.out.println("A apărut o eroare la actualizarea clientului: " + e.getMessage());
        }
    }

    public void stergereClient(int id) {
        String sql = "DELETE FROM clienti WHERE idclienti = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Clientul cu ID-ul " + id + " a fost șters cu succes.");
            } else {
                System.out.println("Nu s-a găsit clientul cu ID-ul " + id + " pentru ștergere.");
            }
        } catch (SQLException e) {
            System.out.println("A apărut o eroare la ștergerea clientului: " + e.getMessage());
        }
    }

    public List<Client> gasesteTotiClientii() {
        List<Client> clienti = new ArrayList<>();
        String sql = "SELECT * FROM clienti";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Client client = new Client(
                        resultSet.getInt("idclienti"),
                        resultSet.getString("nume"),
                        resultSet.getString("prenume"),
                        resultSet.getString("email"),
                        resultSet.getString("parola"),
                        resultSet.getString("adresalivrare")
                );
                clienti.add(client);
            }
        } catch (SQLException e) {
            System.out.println("A apărut o eroare la găsirea tuturor clienților: " + e.getMessage());
        }
        return clienti;
    }


}
