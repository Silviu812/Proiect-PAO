package services;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import models.Furnizor;
import daoservices.FurnizorRepositoryService;

public class FurnizorService {
    private final FurnizorRepositoryService furnizorRepositoryService = new FurnizorRepositoryService();
    private final Scanner scanner = new Scanner(System.in);

    public FurnizorService() throws SQLException {
    }

    public void adaugaFurnizor(Scanner scanner) {
        System.out.println("Introduceți detaliile furnizorului:");
        System.out.print("Nume: ");
        String nume = scanner.nextLine();
        System.out.print("Adresa: ");
        String adresa = scanner.nextLine();

        Furnizor furnizor = new Furnizor(0, nume, adresa);
        furnizor.setNume(nume);
        furnizor.setAdresa(adresa);
        furnizorRepositoryService.adaugaFurnizor(furnizor);
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

    public void meniufurnizor () {
        boolean continuare = true;
        while (continuare) {
            System.out.println("[----- Meniu Furnizor -----]");
            System.out.println("1. Adaugă furnizor");
            System.out.println("2. Afișează furnizor dupa ID");
            System.out.println("3. Actualizează furnizor");
            System.out.println("4. Șterge furnizor");
            System.out.println("5. Afișează toți furnizorii");
            System.out.println("6. Înapoi");
            System.out.print("Introduceți alegerea dvs: ");

            int optiune = scanner.nextInt();
            scanner.nextLine();

            switch (optiune) {
                case 1:
                    adaugaFurnizor(scanner);
                    break;
                case 2:
                    getFurnizorById(scanner);
                    break;
                case 3:
                    actualizeazaFurnizor(scanner);
                    break;
                case 4:
                    stergeFurnizor(scanner);
                    break;
                case 5:
                    afiseazaTotiFurnizorii();
                    break;
                case 6:
                    continuare = false;
                    break;
                default:
                    System.out.println("Alegere invalida, încercați din nou.");
            }
        }
    }
}