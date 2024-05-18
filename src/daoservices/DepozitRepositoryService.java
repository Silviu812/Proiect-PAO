package daoservices;

import dao.DepozitDAO;
import models.Depozit;

import java.util.List;

public class DepozitRepositoryService {
    private final DepozitDAO depozitDAO = new DepozitDAO();

    public void adaugaDepozit(Depozit depozit) {
        depozitDAO.creareDepozit(depozit);
    }

    public Depozit getDepozitById(int id) {
        return depozitDAO.citireDepozit(id);
    }

    public void actualizeazaDepozit(int id, Depozit depozitActualizat) {
        depozitDAO.actualizareDepozit(id, depozitActualizat);
    }

    public void stergeDepozit(Depozit depozit) {
        depozitDAO.stergereDepozit(depozit);
    }

    public List<Depozit> getAllDepozite() {
        return depozitDAO.gasesteToateDepozitele();
    }
}

