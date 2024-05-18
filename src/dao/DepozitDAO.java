package dao;

import models.Depozit;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DepozitDAO {
    private static int nextId = 1;
    private static List<Depozit> depozite = new ArrayList<>();

    public void creareDepozit(Depozit depozit) {
        depozit.setId(nextId++);
        depozite.add(depozit);
    }

    public Depozit citireDepozit(int id) {
        for (Depozit depozit : depozite) {
            if (depozit.getId() == id) {
                return depozit;
            }
        }
        return null;
    }

    public void actualizareDepozit(int id, Depozit depozitActualizat) {
        Optional<Depozit> depozitOptional = depozite.stream()
                .filter(depozit -> depozit.getId() == id)
                .findFirst();

        depozitOptional.ifPresent(depozit -> {
            depozite.remove(depozit);
            depozitActualizat.setId(id);
            depozite.add(depozitActualizat);
        });
    }

    public void stergereDepozit(Depozit depozit) {
        depozite.remove(depozit);
    }

    public List<Depozit> gasesteToateDepozitele() {
        return new ArrayList<>(depozite);
    }
}
