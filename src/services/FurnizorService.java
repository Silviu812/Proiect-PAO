package services;

import java.util.List;
import java.util.Scanner;
import models.Furnizor;
import daoservices.FurnizorRepositoryService;

public class FurnizorService {
    private final FurnizorRepositoryService furnizorRepositoryService = new FurnizorRepositoryService();

    public void adaugaFurnizor(Scanner scanner) {
        System.out.println("Introduceți detaliile furnizorului:");
        System.out.print("Nume: ");
        String nume = scanner.nextLine();
        System.out.print("Adresa: ");
        String adresa = scanner.nextLine();

        Furnizor furnizor = new Furnizor();
        furnizor.setNume(nume);
        furnizor.setAdresa(adresa);
        furnizorRepositoryService.adaugaFurnizor(furnizor);
        System.out.println("Furnizor adăugat cu succes.");
    }

    public Furnizor getFurnizorById(Scanner scanner) {
        System.out.print("Introduceți ID-ul furnizorului: ");
        int id = Integer.parseInt(scanner.nextLine());
        Furnizor furnizor = furnizorRepositoryService.getFurnizorById(id);
        if (furnizor != null) {
            System.out.println("Detalii furnizor: " + furnizor);
        } else {
            System.out.println("Furnizorul cu ID-ul " + id + " nu a fost găsit.");
        }
        return furnizor;
    }

    public void actualizeazaFurnizor(Scanner scanner) {
        System.out.print("Introduceți ID-ul furnizorului de actualizat: ");
        int id = Integer.parseInt(scanner.nextLine());
        Furnizor furnizor = furnizorRepositoryService.getFurnizorById(id);
        if (furnizor != null) {
            System.out.print("Introduceți noul nume: ");
            String nume = scanner.nextLine();
            System.out.print("Introduceți noua adresă: ");
            String adresa = scanner.nextLine();

            furnizor.setNume(nume);
            furnizor.setAdresa(adresa);
            furnizorRepositoryService.actualizeazaFurnizor(id, furnizor);
            System.out.println("Furnizor actualizat cu succes.");
        } else {
            System.out.println("Furnizorul cu ID-ul " + id + " nu a fost găsit.");
        }
    }

    public void stergeFurnizor(Scanner scanner) {
        System.out.print("Introduceți ID-ul furnizorului de șters: ");
        int id = Integer.parseInt(scanner.nextLine());
        Furnizor furnizor = furnizorRepositoryService.getFurnizorById(id);
        if (furnizor != null) {
            furnizorRepositoryService.stergeFurnizor(furnizor);
            System.out.println("Furnizor sters cu succes.");
        } else {
            System.out.println("Furnizorul cu ID-ul " + id + " nu a fost gasit.");
        }
    }

    public void afiseazaTotiFurnizorii() {
        List<Furnizor> furnizori = furnizorRepositoryService.getAllFurnizori();
        if (furnizori.isEmpty()) {
            System.out.println("Nu exista furnizori inregistrati.");
        } else {
            furnizori.forEach(System.out::println);
        }
    }
}