package services;

import daoservices.AngajatRepositoryService;
import models.Angajat;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class AngajatService {
    private final AngajatRepositoryService angajatRepositoryService = new AngajatRepositoryService();

    public void adaugaAngajat(Scanner scanner) {
        System.out.println("Introduceti detaliile angajatului:");
        System.out.print("Nume: ");
        String nume = scanner.nextLine();
        System.out.print("Prenume: ");
        String prenume = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Parola: ");
        String parola = scanner.nextLine();
        System.out.print("Salariu: ");
        double salariu = Double.parseDouble(scanner.nextLine());
        System.out.print("Rol: ");
        String rol = scanner.nextLine();

        Angajat angajat = new Angajat(0, nume, prenume, email, parola, salariu, rol);
        angajatRepositoryService.adaugaAngajat(angajat);
        System.out.println("Angajat adaugat cu succes.");
    }

    public Angajat getAngajatById(Scanner scanner) {
        System.out.print("Introduceti ID-ul angajatului: ");
        int id = Integer.parseInt(scanner.nextLine());
        Angajat angajat = angajatRepositoryService.getAngajatById(id);
        if (angajat != null) {
            System.out.println("Detalii angajat: " + angajat);
        } else {
            System.out.println("Angajatul cu ID-ul " + id + " nu a fost gasit.");
        }
        return angajat;
    }

    public void actualizeazaAngajat(Scanner scanner) {
        System.out.print("Introduceti ID-ul angajatului de actualizat: ");
        int id = Integer.parseInt(scanner.nextLine());
        Angajat angajat = angajatRepositoryService.getAngajatById(id);
        if (angajat != null) {
            System.out.print("Introduceti noul salariu: ");
            double salariu = Double.parseDouble(scanner.nextLine());
            System.out.print("Introduceti noul rol: ");
            String rol = scanner.nextLine();

            angajat.setSalariu(salariu);
            angajat.setRol(rol);
            angajatRepositoryService.actualizeazaAngajat(id, angajat);
            System.out.println("Angajat actualizat cu succes.");
        } else {
            System.out.println("Angajatul cu ID-ul " + id + " nu a fost gasit.");
        }
    }

    public void stergeAngajat(Scanner scanner) {
        System.out.print("Introduceti ID-ul angajatului de sters: ");
        int id = Integer.parseInt(scanner.nextLine());
        Angajat angajat = angajatRepositoryService.getAngajatById(id);
        if (angajat != null) {
            angajatRepositoryService.stergeAngajat(angajat);
            System.out.println("Angajat sters cu succes.");
        } else {
            System.out.println("Angajatul cu ID-ul " + id + " nu a fost gasit.");
        }
    }

    public void afiseazaTotiAngajatii() {
        List<Angajat> angajati = angajatRepositoryService.getAllAngajati();
        if (angajati.isEmpty()) {
            System.out.println("Nu exista angajati inregistrati.");
        } else {
            angajati.forEach(System.out::println);
        }
    }

    public List<Angajat> getAngajatiByRol(String rol) {
        List<Angajat> angajati = angajatRepositoryService.getAllAngajati();
        return angajati.stream()
                .filter(angajat -> angajat.getRol().equalsIgnoreCase(rol))
                .collect(Collectors.toList());
    }

    public List<Angajat> getAngajatiBySalariuMinim(Scanner scanner) {
        System.out.print("Introduceti salariul minim: ");
        double salariuMinim = Double.parseDouble(scanner.nextLine());
        List<Angajat> angajati = angajatRepositoryService.getAllAngajati();
        return angajati.stream()
                .filter(angajat -> angajat.getSalariu() >= salariuMinim)
                .collect(Collectors.toList());
    }

    public double calculareSalariuTotal() {
        List<Angajat> angajati = angajatRepositoryService.getAllAngajati();
        return angajati.stream()
                .mapToDouble(Angajat::getSalariu)
                .sum();
    }

    public void meniuangajati() {
        boolean continuare = true;
        while (continuare) {
            System.out.println("[----- Meniu Angajati -----]");
            System.out.println("1. Adaugare angajat");
            System.out.println("2. Afisare angajat dupa ID");
            System.out.println("3. Actualizare angajat");
            System.out.println("4. Stergere angajat");
            System.out.println("5. Afisare toti angajatii");
            System.out.println("6. Afisare angajati dupa rol");
            System.out.println("7. Afisare angajati cu salariu minim");
            System.out.println("8. Calculare salariu total al angajatilor");
            System.out.println("0. Iesire din meniu");
            System.out.print("Alegeti o optiune: ");

            Scanner scanner = new Scanner(System.in);

            int optiune = Integer.parseInt(scanner.nextLine());

            switch (optiune) {
                case 1:
                    adaugaAngajat(scanner);
                    break;
                case 2:
                    getAngajatById(scanner);
                    break;
                case 3:
                    actualizeazaAngajat(scanner);
                    break;
                case 4:
                    stergeAngajat(scanner);
                    break;
                case 5:
                    afiseazaTotiAngajatii();
                    break;
                case 6:
                    getAngajatiByRol(String.valueOf(scanner));
                    break;
                case 7:
                    getAngajatiBySalariuMinim(scanner);
                    break;
                case 8:
                    System.out.println("Salariu total al angajatilor: " + calculareSalariuTotal());
                    break;
                case 0:
                    continuare = false;
                    break;
                default:
                    System.out.println("Optiune invalida. Va rugam sa incercati din nou.");
            }
        }
    }
}
