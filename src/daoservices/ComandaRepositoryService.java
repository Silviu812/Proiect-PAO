package daoservices;

import dao.ComandaDAO;
import models.Comanda;

import java.sql.SQLException;
import java.util.List;


public class ComandaRepositoryService {
    private final ComandaDAO comandaDAO = new ComandaDAO();

    public ComandaRepositoryService() throws SQLException {
    }

    public void adaugaComanda(Comanda comanda) throws SQLException {
        comandaDAO.creareComanda(comanda);
    }

    public Comanda getComandaById(int id) throws SQLException {
        return comandaDAO.citireComanda(id);
    }


    public void stergeComanda(Comanda comanda) throws SQLException {
        comandaDAO.stergereComanda(comanda);
    }

    public List<Comanda> getAllComenzi() throws SQLException {
        return comandaDAO.gasesteToateComenzile();
    }

}

