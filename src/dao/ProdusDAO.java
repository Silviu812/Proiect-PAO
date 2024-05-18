package dao;

import models.Produs;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProdusDAO {
    private static int nextId = 1;
    private static List<Produs> produse = new ArrayList<>();

    public void creareProdus(Produs produs) {
        produs.setId(nextId++);
        produse.add(produs);
    }

    public Produs citireProdus(int id) {
        for (Produs produs : produse) {
            if (produs.getId() == id) {
                return produs;
            }
        }
        return null;
    }

    public void actualizareProdus(int id, Produs produsActualizat) {
        Optional<Produs> produsOptional = produse.stream()
                .filter(produs -> produs.getId() == id)
                .findFirst();

        produsOptional.ifPresent(produs -> {
            produse.remove(produs);
            produsActualizat.setId(id);
            produse.add(produsActualizat);
        });
    }

    public void stergereProdus(Produs produs) {
        produse.remove(produs);
    }

    public List<Produs> gasesteToateProdusele() {
        return new ArrayList<>(produse);
    }
}
