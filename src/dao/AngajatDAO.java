package dao;

import models.Angajat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class AngajatDAO {
    private static int nextId = 1;
    private static List<Angajat> angajati = new ArrayList<>();

    public void creareAngajat(Angajat angajat) {
        angajat.setId(nextId++);
        angajati.add(angajat);
    }

    public Angajat citireAngajat(int id) {
        for (Angajat angajat : angajati) {
            if (angajat.getId() == id) {
                return angajat;
            }
        }
        return null;
    }

    public void actualizareAngajat(int id, Angajat angajatActualizat) {
        Optional<Angajat> angajatOptional = angajati.stream()
                .filter(angajat -> angajat.getId() == id)
                .findFirst();

        angajatOptional.ifPresent(angajat -> {
            angajati.remove(angajat);
            angajatActualizat.setId(id);
            angajati.add(angajatActualizat);
        });
    }

    public void stergereAngajat(Angajat angajat) {
        angajati.remove(angajat);
    }

    public List<Angajat> gasesteTotiAngajatii() {
        return new ArrayList<>(angajati);
    }
}
