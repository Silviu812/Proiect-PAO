package daoservices;

import dao.FurnizorDAO;
import models.Furnizor;

import java.util.List;

public class FurnizorRepositoryService {
    private final FurnizorDAO furnizorDAO = new FurnizorDAO();

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
        furnizorDAO.stergereFurnizor(furnizor);
    }

    public List<Furnizor> getAllFurnizori() {
        return furnizorDAO.gasesteTotiFurnizorii();
    }
}

