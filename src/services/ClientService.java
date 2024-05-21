package services;

import java.util.List;
import java.util.Scanner;
import models.Comanda;
import models.Client;
import daoservices.ClientRepositoryService;

public class ClientService {
    private final ClientRepositoryService clientRepositoryService = new ClientRepositoryService();

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


}
