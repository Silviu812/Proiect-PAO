package services;

import java.util.List;
import java.util.Scanner;

import models.Comanda;
import models.Client;
import models.Produs;
import daoservices.*;

public class ComandaService {
    private final ComandaRepositoryService comandaRepositoryService = new ComandaRepositoryService();
    private final ClientRepositoryService clientRepositoryService = new ClientRepositoryService();
    private final Scanner scanner = new Scanner(System.in);

    public void adaugaComanda(Scanner scanner) {
        System.out.print("Introduceți ID-ul clientului: ");
        int clientId = Integer.parseInt(scanner.nextLine());

        Client client = clientRepositoryService.getClientById(clientId);
        if (client == null) {
            System.out.println("Clientul cu ID-ul " + clientId + " nu a fost găsit.");
            return;
        }
        Comanda comanda = new Comanda();
        comanda.setClient(client);
        comandaRepositoryService.adaugaComanda(comanda);
        System.out.println("Comanda adaugata cu succes.");
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
    public void meniucomenzi() {
        boolean continuare = true;
        while (continuare) {
            System.out.println("[----- Meniu Comenzi -----]");
            System.out.println("1. Adauga Comanda");
            System.out.println("2. Afiseaza toate comenzile");
            System.out.println("3. Afiseaza comanda dupa ID");
            System.out.println("4. Actualizeaza comanda");
            System.out.println("5. Sterge comanda");
            System.out.println("6. Inapoi");
            System.out.print("Introduceti optiunea: ");

            int optiune = scanner.nextInt();
            scanner.nextLine();

            switch (optiune) {
                case 1:
                    adaugaComanda(scanner);
                    break;
                case 2:
                    afiseazaToateComenzile();
                    break;
                case 3:
                    getComandaById(scanner);
                    break;
                case 4:
                    actualizeazaComanda(scanner);
                    break;
                case 5:
                    stergeComanda(scanner);
                    break;
                case 6:
                    continuare = false;
                    break;
                default:
                    System.out.println("Optiune invalida. Va rugam sa incercati din nou.");
            }
        }
    }
}