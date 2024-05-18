package models;

public class Client extends Utilizator {
    private String adresaLivare;

    public Client() {}

    public Client(int id, String nume, String prenume, String email, String parola, String adresaLivare) {
        super(id, nume, prenume, email, parola);
        this.adresaLivare = adresaLivare;
    }

    public String getAdresaLivare() {
        return adresaLivare;
    }

    public void setAdresaLivare(String adresaLivare) {
        this.adresaLivare = adresaLivare;
    }

    @Override
    public String toString() {
        return super.toString() + "Adresa de livrare: " + adresaLivare + '\n';
    }
}
