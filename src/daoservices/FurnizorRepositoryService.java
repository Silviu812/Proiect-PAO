package daoservices;

import dao.FurnizorDAO;
import models.Furnizor;

import java.sql.SQLException;
import java.util.List;

public class FurnizorRepositoryService {
    private final FurnizorDAO furnizorDAO = new FurnizorDAO();

    public FurnizorRepositoryService() throws SQLException {
    }

    public void adaugaFurnizor(Furnizor furnizor) {
        furnizorDAO.creareFurnizor(furnizor);
    }

    public Furnizor getFurnizorById(int id) {
        return furnizorDAO.citireFurnizor(id);
    }

    public void actualizeazaFurnizor(int id, Furnizor furnizorActualizat) {
        furnizorDAO.actualizareFurnizor(id, furnizorActualizat);
    }

    public void stergeFurnizor(Furnizor furnizor) {
        furnizorDAO.stergereFurnizor(furnizor.getId());
    }

    public List<Furnizor> getAllFurnizori() {
        return furnizorDAO.gasesteTotiFurnizorii();
    }
}

