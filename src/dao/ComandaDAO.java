package dao;

import models.Comanda;
import utils.DBConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ComandaDAO {
    private static int nextId = 1;
    private static List<Comanda> comenzi = new ArrayList<>();
    private Connection connection = DBConnection.getConnection();

    public ComandaDAO() throws SQLException {
    }

    public void creareComanda(Comanda comanda) {
        comanda.setId(nextId++);
        comenzi.add(comanda);
    }

    public Comanda citireComanda(int id) {
        for (Comanda comanda : comenzi) {
            if (comanda.getId() == id) {
                return comanda;
            }
        }
        return null;
    }

    public void actualizareComanda(int id, Comanda comandaActualizata) {
        Optional<Comanda> comandaOptional = comenzi.stream()
                .filter(comanda -> comanda.getId() == id)
                .findFirst();

        comandaOptional.ifPresent(comanda -> {
            comenzi.remove(comanda);
            comandaActualizata.setId(id);
            comenzi.add(comandaActualizata);
        });
    }

    public void stergereComanda(Comanda comanda) {
        comenzi.remove(comanda);
    }

    public List<Comanda> gasesteToateComenzile() {
        return new ArrayList<>(comenzi);
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
