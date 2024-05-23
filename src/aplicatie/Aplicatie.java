package aplicatie;

import services.*;

import java.sql.SQLException;
import java.util.Scanner;

public class Aplicatie {
    private static Aplicatie instance = null;

    private Aplicatie() throws SQLException {}

    public static Aplicatie getInstance() throws SQLException {
        if (instance == null) {
            instance = new Aplicatie();
        }
        return instance;
    }

    public Scanner scanner = new Scanner(System.in);
    public AngajatService angajatService = new AngajatService();
    public ClientService clientService = new ClientService();
    public ProdusService produsService = new ProdusService();
    public ComandaService comandaService = new ComandaService();
    public FurnizorService furnizorService = new FurnizorService();

    public void run() {

        int alegere = 0;
        while (alegere != 6) {
            afiseazaMeniu();
            alegere = scanner.nextInt();
            scanner.nextLine();

            try {
                switch (alegere) {
                    case 1:
                        angajatServiceMeniu();
                        break;
                    case 2:
                        clientServiceMeniu();
                        break;
                    case 3:
                        produsServiceMeniu();
                        break;
                    case 4:
                        comandaServiceMeniu();
                        break;
                    case 5:
                        furnizorServiceMeniu();
                        break;
                    case 6:
                        System.out.println("Ieșire...");
                        break;
                    default:
                        System.out.println("Alegere invalida, încearcă din nou.");
                }
            } catch (SQLException e) {
                System.out.println("Eroare SQL: " + e.getMessage());
            }
        }
    }

    private void afiseazaMeniu() {
        System.out.println("Meniu:");
        System.out.println("1. Modifica Angajati");
        System.out.println("2. Modifica Clienti");
        System.out.println("3. Modifica Produse");
        System.out.println("4. Modifica Comenzi");
        System.out.println("5. Modifica Furnizor");
        System.out.println("6. Ieșire");
        System.out.print("Introduceți alegerea dvs: ");
    }

    private void angajatServiceMeniu() throws SQLException {
        System.out.println("Meniu Angajati : ");
        angajatService.meniuangajati();
    }

    private void clientServiceMeniu() throws SQLException {
        System.out.println("Meniu Clienti : ");
        clientService.meniuclienti();

    }

    private void produsServiceMeniu() throws SQLException {
        System.out.println("Meniu Produse : ");
        produsService.meniuproduse();

    }

    private void comandaServiceMeniu() throws SQLException {
        System.out.println("Meniu Comenzi : ");
        comandaService.meniucomenzi();
    }

    private void furnizorServiceMeniu() throws SQLException {
        System.out.println("Meniu Furnizori : ");
        furnizorService.meniufurnizor();

    }


    public static void main(String[] args) throws SQLException {
        Aplicatie aplicatie = Aplicatie.getInstance();
        aplicatie.run();
    }
}
