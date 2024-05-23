package dao;

import models.Furnizor;
import utils.DBConnection;

import java.sql.Connection;
import java.sql.SQLException;
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
        furnizor.setId(nextId++);
        furnizori.add(furnizor);
    }

    public Furnizor citireFurnizor(int id) {
        for (Furnizor furnizor : furnizori) {
            if (furnizor.getId() == id) {
                return furnizor;
            }
        }
        return null;
    }

    public void actualizareFurnizor(int id, Furnizor furnizorActualizat) {
        Optional<Furnizor> furnizorOptional = furnizori.stream()
                .filter(furnizor -> furnizor.getId() == id)
                .findFirst();

        furnizorOptional.ifPresent(furnizor -> {
            furnizori.remove(furnizor);
            furnizorActualizat.setId(id);
            furnizori.add(furnizorActualizat);
        });
    }

    public void stergereFurnizor(Furnizor furnizor) {
        furnizori.remove(furnizor);
    }

    public List<Furnizor> gasesteTotiFurnizorii() {
        return new ArrayList<>(furnizori);
    }
}
