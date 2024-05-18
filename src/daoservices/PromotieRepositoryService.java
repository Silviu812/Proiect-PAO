package daoservices;

import dao.PromotieDAO;
import models.Promotie;

import java.util.List;

public class PromotieRepositoryService {
    private final PromotieDAO promotieDAO = new PromotieDAO();

    public void adaugaPromotie(Promotie promotie) {
        promotieDAO.crearePromotie(promotie);
    }

    public Promotie getPromotieById(int id) {
        return promotieDAO.citirePromotie(id);
    }

    public void actualizeazaPromotie(int id, Promotie promotieActualizata) {
        promotieDAO.actualizarePromotie(id, promotieActualizata);
    }

    public void stergePromotie(Promotie promotie) {
        promotieDAO.stergerePromotie(promotie);
    }

    public List<Promotie> getAllPromotii() {
        return promotieDAO.gasesteToatePromotiile();
    }
}
