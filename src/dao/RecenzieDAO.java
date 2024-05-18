package dao;

import models.Recenzie;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RecenzieDAO {
    private static int nextId = 1;
    private static List<Recenzie> recenzii = new ArrayList<>();

    public void creareRecenzie(Recenzie recenzie) {
        recenzie.setId(nextId++);
        recenzii.add(recenzie);
    }

    public Recenzie citireRecenzie(int id) {
        for (Recenzie recenzie : recenzii) {
            if (recenzie.getId() == id) {
                return recenzie;
            }
        }
        return null;
    }

    public void actualizareRecenzie(int id, Recenzie recenzieActualizata) {
        Optional<Recenzie> recenzieOptional = recenzii.stream()
                .filter(recenzie -> recenzie.getId() == id)
                .findFirst();

        recenzieOptional.ifPresent(recenzie -> {
            recenzii.remove(recenzie);
            recenzieActualizata.setId(id);
            recenzii.add(recenzieActualizata);
        });
    }

    public void stergereRecenzie(Recenzie recenzie) {
        recenzii.remove(recenzie);
    }

    public List<Recenzie> gasesteToateRecenziile() {
        return new ArrayList<>(recenzii);
    }
}
