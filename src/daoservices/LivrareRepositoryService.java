package daoservices;

import dao.LivrareDAO;
import models.Livrare;

import java.util.List;

public class LivrareRepositoryService {
    private final LivrareDAO livrareDAO = new LivrareDAO();

    public void adaugaLivrare(Livrare livrare) {
        livrareDAO.creareLivrare(livrare);
    }

    public Livrare getLivrareById(int id) {
        return livrareDAO.citireLivrare(id);
    }

    public void actualizeazaLivrare(int id, Livrare livrareActualizata) {
        livrareDAO.actualizareLivrare(id, livrareActualizata);
    }

    public void stergeLivrare(Livrare livrare) {
        livrareDAO.stergereLivrare(livrare);
    }

    public List<Livrare> getAllLivrari() {
        return livrareDAO.gasesteToateLivrariile();
    }
}
