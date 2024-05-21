package services;

import java.util.List;
import java.util.Scanner;

import models.Comanda;
import models.Client;
import models.Produs;
import daoservices.ComandaRepositoryService;

public class ComandaService {
    private final ComandaRepositoryService comandaRepositoryService = new ComandaRepositoryService();

    public void adaugaComanda(Scanner scanner) {
        System.out.println("Introduceți detaliile comenzii:");
        Comanda comanda = new Comanda();
        comandaRepositoryService.adaugaComanda(comanda);
    }

    public Comanda getComandaById(Scanner scanner) {
        System.out.print("Introduceți ID-ul comenzii: ");
        int id = Integer.parseInt(scanner.nextLine());
        Comanda comanda = comandaRepositoryService.getComandaById(id);
        if (comanda != null) {
            System.out.println("Detalii comandă: " + comanda);
        } else {
            System.out.println("Comanda cu ID-ul " + id + " nu a fost găsită.");
        }
        return comanda;
    }

    public void actualizeazaComanda(Scanner scanner) {
        System.out.print("Introduceți ID-ul comenzii de actualizat: ");
        int id = Integer.parseInt(scanner.nextLine());
        Comanda comanda = comandaRepositoryService.getComandaById(id);
        if (comanda != null) {
            comandaRepositoryService.actualizeazaComanda(id, comanda);
            System.out.println("Comanda actualizată cu succes.");
        } else {
            System.out.println("Comanda cu ID-ul " + id + " nu a fost găsită.");
        }
    }

    public void stergeComanda(Scanner scanner) {
        System.out.print("Introduceți ID-ul comenzii de șters: ");
        int id = Integer.parseInt(scanner.nextLine());
        Comanda comanda = comandaRepositoryService.getComandaById(id);
        if (comanda != null) {
            comandaRepositoryService.stergeComanda(comanda);
            System.out.println("Comanda ștearsă cu succes.");
        } else {
            System.out.println("Comanda cu ID-ul " + id + " nu a fost găsită.");
        }
    }

    public void afiseazaToateComenzile() {
        List<Comanda> comenzi = comandaRepositoryService.getAllComenzi();
        if (comenzi.isEmpty()) {
            System.out.println("Nu există comenzi înregistrate.");
        } else {
            comenzi.forEach(System.out::println);
        }
    }
}