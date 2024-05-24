package services;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import models.Comanda;
import models.Client;
import daoservices.ClientRepositoryService;

public class ClientService {
    private final ClientRepositoryService clientRepositoryService = new ClientRepositoryService();
    private final Scanner scanner = new Scanner(System.in);

    public ClientService() throws SQLException {
    }

    public void adaugaClient(Scanner scanner) {
        System.out.println("Introduceți detaliile clientului:");
        System.out.print("Nume: ");
        String nume = scanner.nextLine();
        System.out.print("Prenume: ");
        String prenume = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Parolă: ");
        String parola = scanner.nextLine();
        System.out.print("Adresă de livrare: ");
        String adresaLivare = scanner.nextLine();

        Client client = new Client(0, nume, prenume, email, parola, adresaLivare);
        clientRepositoryService.adaugaClient(client);
        System.out.println("Client adăugat cu succes.");
    }

    public Client getClientById(Scanner scanner) {
        System.out.print("Introduceți ID-ul clientului: ");
        int id = Integer.parseInt(scanner.nextLine());
        Client client = clientRepositoryService.getClientById(id);
        if (client != null) {
            System.out.println("Detalii client: " + client);
        } else {
            System.out.println("Clientul cu ID-ul " + id + " nu a fost găsit.");
        }
        return client;
    }

    public void actualizeazaClient(Scanner scanner) {
        System.out.print("Introduceți ID-ul clientului de actualizat: ");
        int id = Integer.parseInt(scanner.nextLine());
        Client client = clientRepositoryService.getClientById(id);
        if (client != null) {
            System.out.print("Introduceți noua adresă de livrare: ");
            String adresaLivare = scanner.nextLine();

            client.setAdresaLivare(adresaLivare);
            clientRepositoryService.actualizeazaClient(id, client);
            System.out.println("Client actualizat cu succes.");
        } else {
            System.out.println("Clientul cu ID-ul " + id + " nu a fost găsit.");
        }
    }

    public void stergeClient(Scanner scanner) {
        System.out.print("Introduceți ID-ul clientului de șters: ");
        int id = Integer.parseInt(scanner.nextLine());
        Client client = clientRepositoryService.getClientById(id);
        if (client != null) {
            clientRepositoryService.stergeClient(client);
            System.out.println("Client șters cu succes.");
        } else {
            System.out.println("Clientul cu ID-ul " + id + " nu a fost găsit.");
        }
    }

    public void afiseazaTotiClientii() {
        List<Client> clienti = clientRepositoryService.getAllClienti();
        if (clienti.isEmpty()) {
            System.out.println("Nu există clienți înregistrați.");
        } else {
            clienti.forEach(System.out::println);
        }
    }

    public void afiseazaComenzileClientului(Scanner scanner) {
        System.out.print("Introduceți ID-ul clientului: ");
        int id = Integer.parseInt(scanner.nextLine());
        Client client = clientRepositoryService.getClientById(id);
        if (client != null) {
            List<Comanda> comenzi = clientRepositoryService.getComenziByClientId(id);
            if (comenzi.isEmpty()) {
                System.out.println("Clientul nu are comenzi.");
            } else {
                System.out.println("Comenzile clientului " + client.getNume() + ":");
                comenzi.forEach(System.out::println);
            }
        } else {
            System.out.println("Clientul cu ID-ul " + id + " nu a fost găsit.");
        }
    }

    public void meniuclienti() {
        boolean continuare = true;
        while (continuare) {
            System.out.println("[----- Meniu Clienti -----]");
            System.out.println("1. Adauga client");
            System.out.println("2. Afiseaza toti clientii");
            System.out.println("3. Afiseaza client dupa ID");
            System.out.println("4. Actualizeaza client");
            System.out.println("5. Sterge client");
            System.out.println("6. Afiseaza comenzile unui client (in lucru)");
            System.out.println("7. Inapoi");
            System.out.print("Introduceti optiunea: ");

            int optiune = scanner.nextInt();
            scanner.nextLine();

            switch (optiune) {
                case 1:
                    adaugaClient(scanner);
                    break;
                case 2:
                    afiseazaTotiClientii();
                    break;
                case 3:
                    getClientById(scanner);
                    break;
                case 4:
                    actualizeazaClient(scanner);
                    break;
                case 5:
                    stergeClient(scanner);
                    break;
                case 6:
                    afiseazaComenzileClientului(scanner);
                    break;
                case 7:
                    continuare = false;
                    break;
                default:
                    System.out.println("Optiune invalida. Va rugam sa incercati din nou.");
            }
        }
    }
}
