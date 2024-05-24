package daoservices;

import dao.AngajatDAO;
import models.Angajat;

import java.sql.SQLException;
import java.util.List;

public class AngajatRepositoryService {
    private final AngajatDAO angajatDAO = new AngajatDAO();

    public AngajatRepositoryService() throws SQLException {
    }

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
        angajatDAO.stergereAngajat(angajat.getId());
    }

    public List<Angajat> getAllAngajati() {
        return angajatDAO.gasesteTotiAngajatii();
    }

    public List<Angajat> getAngajatiByRol(String rol) {
        return angajatDAO.getAngajatiByRol(rol);
    }

    public List<Angajat> getAngajatiBySalariuMinim(double salariuMinim) {
        return angajatDAO.getAngajatiBySalariuMinim(salariuMinim);
    }
}
