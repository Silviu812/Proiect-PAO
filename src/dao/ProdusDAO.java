package dao;

import models.Produs;
import utils.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdusDAO {
    private Connection connection = DBConnection.getConnection();
    private static int nextId = 1;
    private static List<Produs> produse = new ArrayList<>();

    public ProdusDAO() throws SQLException {
    }

    public void creareProdus(Produs produs) {
        String sql = "INSERT INTO produse (denumire, pret, categorie) VALUES (?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, produs.getDenumire());
            statement.setDouble(2, produs.getPret());
            statement.setString(3, produs.getCategorie());

            int rowsInserted = statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("A apărut o eroare la adăugarea produsului: " + e.getMessage());
        }
    }

    public Produs citireProdus(int id) {
        String sql = "SELECT * FROM produse WHERE idproduse = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Produs produs = new Produs(
                        resultSet.getInt("idproduse"),
                        resultSet.getString("denumire"),
                        resultSet.getDouble("pret"),
                        resultSet.getString("categorie")
                );
                return produs;
            } else {
                System.out.println("Produsul cu ID-ul " + id + " nu a fost găsit.");
                return null;
            }
        } catch (SQLException e) {
            System.out.println("A apărut o eroare la căutarea produsului: " + e.getMessage());
            return null;
        }
    }

    public void actualizareProdus(int id, Produs produsActualizat) {
        String sql = "UPDATE produse SET denumire = ?, pret = ?, categorie = ? WHERE idproduse = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, produsActualizat.getDenumire());
            statement.setDouble(2, produsActualizat.getPret());
            statement.setString(3, produsActualizat.getCategorie());
            statement.setInt(4, id);

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Produsul a fost actualizat cu succes.");
            } else {
                System.out.println("Nu s-a putut actualiza produsul.");
            }
        } catch (SQLException e) {
            System.out.println("A apărut o eroare la actualizarea produsului: " + e.getMessage());
        }
    }

    public void stergereProdus(int id) {
        String sql = "DELETE FROM produse WHERE idproduse = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Produsul cu ID-ul " + id + " a fost șters cu succes.");
            } else {
                System.out.println("Nu s-a putut șterge produsul cu ID-ul " + id + ".");
            }
        } catch (SQLException e) {
            System.out.println("A apărut o eroare la ștergerea produsului: " + e.getMessage());
        }
    }

    public List<Produs> gasesteToateProdusele() {
        List<Produs> produse = new ArrayList<>();
        String sql = "SELECT * FROM produse";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Produs produs = new Produs(
                        resultSet.getInt("idproduse"),
                        resultSet.getString("denumire"),
                        resultSet.getDouble("pret"),
                        resultSet.getString("categorie")
                );
                produse.add(produs);
            }
        } catch (SQLException e) {
            System.out.println("A apărut o eroare la găsirea tuturor produselor: " + e.getMessage());
        }
        return produse;
    }
}
