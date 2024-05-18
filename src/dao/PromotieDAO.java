package dao;

import models.Promotie;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PromotieDAO {
    private static int nextId = 1;
    private static List<Promotie> promotii = new ArrayList<>();

    public void crearePromotie(Promotie promotie) {
        promotie.setId(nextId++);
        promotii.add(promotie);
    }

    public Promotie citirePromotie(int id) {
        for (Promotie promotie : promotii) {
            if (promotie.getId() == id) {
                return promotie;
            }
        }
        return null;
    }

    public void actualizarePromotie(int id, Promotie promotieActualizata) {
        Optional<Promotie> promotieOptional = promotii.stream()
                .filter(promotie -> promotie.getId() == id)
                .findFirst();

        promotieOptional.ifPresent(promotie -> {
            promotii.remove(promotie);
            promotieActualizata.setId(id);
            promotii.add(promotieActualizata);
        });
    }

    public void stergerePromotie(Promotie promotie) {
        promotii.remove(promotie);
    }

    public List<Promotie> gasesteToatePromotiile() {
        return new ArrayList<>(promotii);
    }
}
