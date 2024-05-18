package daoservices;

import dao.ProdusDAO;
import models.Produs;

import java.util.List;

public class ProdusRepositoryService {
    private final ProdusDAO produsDAO = new ProdusDAO();

    public void adaugaProdus(Produs produs) {
        produsDAO.creareProdus(produs);
    }

    public Produs getProdusById(int id) {
        return produsDAO.citireProdus(id);
    }

    public void actualizeazaProdus(int id, Produs produsActualizat) {
        produsDAO.actualizareProdus(id, produsActualizat);
    }

    public void stergeProdus(Produs produs) {
        produsDAO.stergereProdus(produs);
    }

    public List<Produs> getAllProduse() {
        return produsDAO.gasesteToateProdusele();
    }
}
