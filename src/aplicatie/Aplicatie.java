package aplicatie;

import services.*;

import java.sql.SQLException;
import java.util.Scanner;

public class Aplicatie {
    private static Aplicatie instance = null;

    private Aplicatie() {}

    public static Aplicatie getInstance() {
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
    public FurnizorService depozitService = new FurnizorService();

    public void run() {
    }


}
