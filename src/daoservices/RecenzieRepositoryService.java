package daoservices;

import dao.RecenzieDAO;
import models.Recenzie;

import java.util.List;

public class RecenzieRepositoryService {
    private final RecenzieDAO recenzieDAO = new RecenzieDAO();

    public void adaugaRecenzie(Recenzie recenzie) {
        recenzieDAO.creareRecenzie(recenzie);
    }

    public Recenzie getRecenzieById(int id) {
        return recenzieDAO.citireRecenzie(id);
    }

    public void actualizeazaRecenzie(int id, Recenzie recenzieActualizata) {
        recenzieDAO.actualizareRecenzie(id, recenzieActualizata);
    }

    public void stergeRecenzie(Recenzie recenzie) {
        recenzieDAO.stergereRecenzie(recenzie);
    }

    public List<Recenzie> getAllRecenzii() {
        return recenzieDAO.gasesteToateRecenziile();
    }
}
