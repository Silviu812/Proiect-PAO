package daoservices;

import dao.ComandaDAO;
import models.Comanda;

import java.util.List;


public class ComandaRepositoryService {
    private final ComandaDAO comandaDAO = new ComandaDAO();

    public void adaugaComanda(Comanda comanda) {
        comandaDAO.creareComanda(comanda);
    }

    public Comanda getComandaById(int id) {
        return comandaDAO.citireComanda(id);
    }

    public void actualizeazaComanda(int id, Comanda comandaActualizata) {
        comandaDAO.actualizareComanda(id, comandaActualizata);
    }

    public void stergeComanda(Comanda comanda) {
        comandaDAO.stergereComanda(comanda);
    }

    public List<Comanda> getAllComenzi() {
        return comandaDAO.gasesteToateComenzile();
    }

}

