package dao;

import models.Angajat;
import utils.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


import java.util.Optional;


public class AngajatDAO {
    private Connection connection = DBConnection.getConnection();
    private static int nextId = 1;
    private static List<Angajat> angajati = new ArrayList<>();

    public AngajatDAO() throws SQLException {
    }

    public void creareAngajat(Angajat angajat) {
        String sql = "INSERT INTO angajati (nume, prenume, email, parola, salariu, rol) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, angajat.getNume());
            statement.setString(2, angajat.getPrenume());
            statement.setString(3, angajat.getEmail());
            statement.setString(4, angajat.getParola());
            statement.setDouble(5, angajat.getSalariu());
            statement.setString(6, angajat.getRol());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Angajat adăugat cu succes.");
            }
        } catch (SQLException e) {
            System.out.println("A apărut o eroare la adăugarea angajatului: " + e.getMessage());
        }
    }

    public Angajat citireAngajat(int id) {
        String sql = "SELECT * FROM angajati WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Angajat angajat = new Angajat(
                        resultSet.getInt("id"),
                        resultSet.getString("nume"),
                        resultSet.getString("prenume"),
                        resultSet.getString("email"),
                        resultSet.getString("parola"),
                        resultSet.getDouble("salariu"),
                        resultSet.getString("rol")
                );
                System.out.println("Detalii angajat: " + angajat);
                return angajat;
            } else {
                System.out.println("Angajatul cu ID-ul " + id + " nu a fost găsit.");
                return null;
            }
        } catch (SQLException e) {
            System.out.println("A apărut o eroare la căutarea angajatului: " + e.getMessage());
            return null;
        }
    }


    public void actualizareAngajat(int id, Angajat angajatActualizat) {
        String sql = "UPDATE angajati SET salariu = ?, rol = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDouble(1, angajatActualizat.getSalariu());
            statement.setString(2, angajatActualizat.getRol());
            statement.setInt(3, id);

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Angajat actualizat cu succes.");
            } else {
                System.out.println("Nu s-a putut actualiza angajatul.");
            }
        } catch (SQLException e) {
            System.out.println("A apărut o eroare la actualizarea angajatului: " + e.getMessage());
        }
    }

    public void stergereAngajat(int id) {
        String sql = "DELETE FROM angajati WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Angajatul cu ID-ul " + id + " a fost șters cu succes.");
            } else {
                System.out.println("Nu s-a putut șterge angajatul cu ID-ul " + id + ".");
            }
        } catch (SQLException e) {
            System.out.println("A apărut o eroare la ștergerea angajatului: " + e.getMessage());
        }
    }

    public List<Angajat> gasesteTotiAngajatii() {
        List<Angajat> angajati = new ArrayList<>();
        String sql = "SELECT * FROM angajati";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Angajat angajat = new Angajat(
                        resultSet.getInt("id"),
                        resultSet.getString("nume"),
                        resultSet.getString("prenume"),
                        resultSet.getString("email"),
                        resultSet.getString("parola"),
                        resultSet.getDouble("salariu"),
                        resultSet.getString("rol")
                );
                angajati.add(angajat);
            }
        } catch (SQLException e) {
            System.out.println("A apărut o eroare la găsirea tuturor angajaților: " + e.getMessage());
        }
        return angajati;
    }
    public List<Angajat> getAngajatiByRol(String rol) {
        List<Angajat> angajati = new ArrayList<>();
        String sql = "SELECT * FROM angajati WHERE rol = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, rol);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Angajat angajat = new Angajat(
                        resultSet.getInt("id"),
                        resultSet.getString("nume"),
                        resultSet.getString("prenume"),
                        resultSet.getString("email"),
                        resultSet.getString("parola"),
                        resultSet.getDouble("salariu"),
                        resultSet.getString("rol")
                );
                angajati.add(angajat);
                System.out.println("Angajat: " + angajat); // Afiseaza angajatul gasit
            }
        } catch (SQLException e) {
            System.out.println("A apărut o eroare la preluarea angajaților după rol: " + e.getMessage());
        }
        return angajati;
    }

    public List<Angajat> getAngajatiBySalariuMinim(double salariuMinim) {
        List<Angajat> angajati = new ArrayList<>();
        String sql = "SELECT * FROM angajati WHERE salariu >= ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDouble(1, salariuMinim);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Angajat angajat = new Angajat(
                        resultSet.getInt("id"),
                        resultSet.getString("nume"),
                        resultSet.getString("prenume"),
                        resultSet.getString("email"),
                        resultSet.getString("parola"),
                        resultSet.getDouble("salariu"),
                        resultSet.getString("rol")
                );
                angajati.add(angajat);
                System.out.println("Angajat: " + angajat);
            }
        } catch (SQLException e) {
            System.out.println("A apărut o eroare la preluarea angajaților după salariu minim: " + e.getMessage());
        }
        return angajati;
    }

}
