package daoservices;

import dao.AngajatDAO;
import models.Angajat;

import java.util.List;

public class AngajatRepositoryService {
    private final AngajatDAO angajatDAO = new AngajatDAO();

    public void adaugaAngajat(Angajat angajat) {
        angajatDAO.creareAngajat(angajat);
    }

    public Angajat getAngajatById(int id) {
        return angajatDAO.citireAngajat(id);
    }

    public void actualizeazaAngajat(int id, Angajat angajatActualizat) {
        angajatDAO.actualizareAngajat(id, angajatActualizat);
    }

    public void stergeAngajat(Angajat angajat) {
        angajatDAO.stergereAngajat(angajat);
    }

    public List<Angajat> getAllAngajati() {
        return angajatDAO.gasesteTotiAngajatii();
    }
}
