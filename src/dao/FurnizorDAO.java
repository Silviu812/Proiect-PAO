package dao;

import models.Furnizor;
import utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FurnizorDAO {
    private static int nextId = 1;
    private static List<Furnizor> furnizori = new ArrayList<>();
    private Connection connection = DBConnection.getConnection();

    public FurnizorDAO() throws SQLException {
    }

    public void creareFurnizor(Furnizor furnizor) {
        String sql = "INSERT INTO furnizori (nume, adresa) VALUES (?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, furnizor.getNume());
            statement.setString(2, furnizor.getAdresa());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Furnizor adăugat cu succes.");
                furnizor.setId(nextId++);
                furnizori.add(furnizor);
            } else {
                System.out.println("A apărut o eroare la adăugarea furnizorului.");
            }
        } catch (SQLException e) {
            System.out.println("A apărut o eroare la adăugarea furnizorului: " + e.getMessage());
        }
    }

    public Furnizor citireFurnizor(int id) {
        String sql = "SELECT * FROM furnizori WHERE idfurnizori = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int idFurnizori = resultSet.getInt("idfurnizori");
                String nume = resultSet.getString("nume");
                String adresa = resultSet.getString("adresa");

                return new Furnizor(idFurnizori, nume, adresa);
            }
        } catch (SQLException e) {
            System.out.println("A apărut o eroare la citirea furnizorului cu ID-ul " + id + ": " + e.getMessage());
        }

        return null;
    }

    public void actualizareFurnizor(int id, Furnizor furnizorActualizat) {
        String sql = "UPDATE furnizori SET nume = ?, adresa = ? WHERE idfurnizori = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, furnizorActualizat.getNume());
            statement.setString(2, furnizorActualizat.getAdresa());
            statement.setInt(3, id);

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Furnizor actualizat cu succes.");

                for (int i = 0; i < furnizori.size(); i++) {
                    if (furnizori.get(i).getId() == id) {
                        furnizori.set(i, furnizorActualizat);
                        break;
                    }
                }
            } else {
                System.out.println("Nu a fost găsit niciun furnizor cu ID-ul " + id + " pentru actualizare.");
            }
        } catch (SQLException e) {
            System.out.println("A apărut o eroare la actualizarea furnizorului cu ID-ul " + id + ": " + e.getMessage());
        }
    }

    public void stergereFurnizor(int id) {
        String sql = "DELETE FROM furnizori WHERE idfurnizori = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Furnizor șters cu succes cu ID-ul " + id + ".");

                furnizori.removeIf(furnizor -> furnizor.getId() == id);
            } else {
                System.out.println("Nu a fost găsit niciun furnizor cu ID-ul " + id + " pentru ștergere.");
            }
        } catch (SQLException e) {
            System.out.println("A apărut o eroare la ștergerea furnizorului cu ID-ul " + id + ": " + e.getMessage());
        }
    }

    public List<Furnizor> gasesteTotiFurnizorii() {
        List<Furnizor> totiFurnizorii = new ArrayList<>();

        String sql = "SELECT * FROM furnizori";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int idFurnizori = resultSet.getInt("idfurnizori");
                String nume = resultSet.getString("nume");
                String adresa = resultSet.getString("adresa");

                Furnizor furnizor = new Furnizor(idFurnizori, nume, adresa);
                totiFurnizorii.add(furnizor);
            }
        } catch (SQLException e) {
            System.out.println("A apărut o eroare la citirea furnizorilor: " + e.getMessage());
        }

        return totiFurnizorii;
    }
}
