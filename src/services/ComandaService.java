package services;

import java.sql.SQLException;
import java.util.*;
import java.util.Scanner;

import models.Comanda;
import models.Client;
import models.Produs;
import daoservices.*;

public class ComandaService {
    private final ComandaRepositoryService comandaRepositoryService = new ComandaRepositoryService();
    private final ClientRepositoryService clientRepositoryService = new ClientRepositoryService();
    private final ProdusRepositoryService produsRepositoryService = new ProdusRepositoryService();
    private final Scanner scanner = new Scanner(System.in);

    public ComandaService() throws SQLException {
    }

    public void adaugaComanda(Scanner scanner) throws SQLException {
        System.out.print("Introduceți ID-ul clientului: ");
        int clientId = Integer.parseInt(scanner.nextLine());

        Client client = clientRepositoryService.getClientById(clientId);
        if (client == null) {
            System.out.println("Clientul cu ID-ul " + clientId + " nu a fost găsit.");
            return;
        }

        Comanda comanda = new Comanda();
        comanda.setClient(client);

        List<Produs> produse = new ArrayList<>();
        while (true) {
            System.out.print("Introduceți ID-ul produsului (sau '0' pentru a termina): ");
            int produsId = Integer.parseInt(scanner.nextLine());
            if (produsId == 0) {
                break;
            }

            Produs produs = produsRepositoryService.getProdusById(produsId);
            if (produs == null) {
                System.out.println("Produsul cu ID-ul " + produsId + " nu a fost găsit.");
            } else {
                produse.add(produs);
            }
        }

        comanda.setProduse(produse);

        comandaRepositoryService.adaugaComanda(comanda);
        System.out.println("Comanda adaugata cu succes.");
    }


    public Comanda getComandaById(Scanner scanner) throws SQLException {
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


    public void stergeComanda(Scanner scanner) throws SQLException {
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

    public void afiseazaToateComenzile() throws SQLException {
        List<Comanda> comenzi = comandaRepositoryService.getAllComenzi();
        if (comenzi.isEmpty()) {
            System.out.println("Nu există comenzi înregistrate.");
        } else {
            comenzi.forEach(System.out::println);
        }
    }
    public void meniucomenzi() throws SQLException {
        boolean continuare = true;
        while (continuare) {
            System.out.println("[----- Meniu Comenzi -----]");
            System.out.println("1. Adauga Comanda");
            System.out.println("2. Afiseaza toate comenzile");
            System.out.println("3. Afiseaza comanda dupa ID");
            System.out.println("4. Actualizeaza comanda (not implemented)");
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