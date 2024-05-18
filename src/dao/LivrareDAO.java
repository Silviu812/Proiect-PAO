package dao;

import models.Livrare;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LivrareDAO {
    private static int nextId = 1;
    private static List<Livrare> livrari = new ArrayList<>();

    public void creareLivrare(Livrare livrare) {
        livrare.setId(nextId++);
        livrari.add(livrare);
    }

    public Livrare citireLivrare(int id) {
        for (Livrare livrare : livrari) {
            if (livrare.getId() == id) {
                return livrare;
            }
        }
        return null;
    }

    public void actualizareLivrare(int id, Livrare livrareActualizata) {
        Optional<Livrare> livrareOptional = livrari.stream()
                .filter(livrare -> livrare.getId() == id)
                .findFirst();

        livrareOptional.ifPresent(livrare -> {
            livrari.remove(livrare);
            livrareActualizata.setId(id);
            livrari.add(livrareActualizata);
        });
    }

    public void stergereLivrare(Livrare livrare) {
        livrari.remove(livrare);
    }

    public List<Livrare> gasesteToateLivrariile() {
        return new ArrayList<>(livrari);
    }
}
