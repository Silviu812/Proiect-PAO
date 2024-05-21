package models;
import aplicatie.Aplicatie;
public class Furnizor {
    private int id;
    private String nume;
    private String adresa;

    public int getId() {
        return id;
    }

    public String getNume() {
        return nume;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    @Override
    public String toString() {
        return "Furnizor{" +
                "ID =" + id +
                ", Nume ='" + nume + '\'' +
                ", Adresa ='" + adresa + '\'' +
                '}';
    }
}
