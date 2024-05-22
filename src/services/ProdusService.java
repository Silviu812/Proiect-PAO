package services;

import java.util.List;
import java.util.Scanner;
import models.Produs;
import daoservices.ProdusRepositoryService;

public class ProdusService {
    private final ProdusRepositoryService produsRepositoryService = new ProdusRepositoryService();
    private final Scanner scanner = new Scanner(System.in);

    public void adaugaProdus(Scanner scanner) {
        System.out.println("Introduceți detaliile produsului:");
        System.out.print("Denumire: ");
        String denumire = scanner.nextLine();
        System.out.print("Preț: ");
        double pret = Double.parseDouble(scanner.nextLine());
        System.out.print("Categorie: ");
        String categorie = scanner.nextLine();

        Produs produs = new Produs(0, denumire, pret, categorie);
        produsRepositoryService.adaugaProdus(produs);
        System.out.println("Produs adăugat cu succes.");
    }

    public Produs getProdusById(Scanner scanner) {
        System.out.print("Introduceți ID-ul produsului: ");
        int id = Integer.parseInt(scanner.nextLine());
        Produs produs = produsRepositoryService.getProdusById(id);
        if (produs != null) {
            System.out.println("Detalii produs: " + produs);
        } else {
            System.out.println("Produsul cu ID-ul " + id + " nu a fost găsit.");
        }
        return produs;
    }

    public void actualizeazaProdus(Scanner scanner) {
        System.out.print("Introduceți ID-ul produsului de actualizat: ");
        int id = Integer.parseInt(scanner.nextLine());
        Produs produs = produsRepositoryService.getProdusById(id);
        if (produs != null) {
            System.out.print("Introduceți noua denumire: ");
            String denumire = scanner.nextLine();
            System.out.print("Introduceți noul preț: ");
            double pret = Double.parseDouble(scanner.nextLine());
            System.out.print("Introduceți noua categorie: ");
            String categorie = scanner.nextLine();

            produs.setDenumire(denumire);
            produs.setPret(pret);
            produs.setCategorie(categorie);
            produsRepositoryService.actualizeazaProdus(id, produs);
            System.out.println("Produs actualizat cu succes.");
        } else {
            System.out.println("Produsul cu ID-ul " + id + " nu a fost găsit.");
        }
    }

    public void stergeProdus(Scanner scanner) {
        System.out.print("Introduceți ID-ul produsului de șters: ");
        int id = Integer.parseInt(scanner.nextLine());
        Produs produs = produsRepositoryService.getProdusById(id);
        if (produs != null) {
            produsRepositoryService.stergeProdus(produs);
            System.out.println("Produs șters cu succes.");
        } else {
            System.out.println("Produsul cu ID-ul " + id + " nu a fost găsit.");
        }
    }

    public void afiseazaToateProdusele() {
        List<Produs> produse = produsRepositoryService.getAllProduse();
        if (produse.isEmpty()) {
            System.out.println("Nu există produse înregistrate.");
        } else {
            produse.forEach(System.out::println);
        }
    }
    public void meniuproduse() {
        boolean continuare = true;
        while (continuare) {
            System.out.println("[----- Meniu Produse -----]");
            System.out.println("1. Adaugă produs");
            System.out.println("2. Afișează un produs dupa ID");
            System.out.println("3. Actualizează produs");
            System.out.println("4. Șterge produs");
            System.out.println("5. Afișează toate produsele");
            System.out.println("6. Ieșire");
            System.out.print("Introduceți alegerea dvs: ");

            int optiune = scanner.nextInt();
            scanner.nextLine();

            switch (optiune) {
                case 1:
                    adaugaProdus(scanner);
                    break;
                case 2:
                    getProdusById(scanner);
                    break;
                case 3:
                    actualizeazaProdus(scanner);
                    break;
                case 4:
                    stergeProdus(scanner);
                    break;
                case 5:
                    afiseazaToateProdusele();
                    break;
                case 6:
                    continuare = false;
                    break;
                default:
                    System.out.println("Alegere invalidă, încercați din nou.");
            }
        }
    }
}