package models;

public class Produs {
    private int id;
    private String denumire;
    private double pret;
    private String categorie;

    public Produs(int id, String nume, double pret, String categorie) {
        this.id = id;
        this.denumire = nume;
        this.pret = pret;
        this.categorie = categorie;
    }

    public int getId() {
        return id;
    }

    public String getDenumire() {
        return denumire;
    }

    public double getPret() {
        return pret;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public void setPret(double pret) {
        this.pret = pret;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    @Override
    public String toString() {
        return "Produs{" +
                "ID =" + id +
                ", Denumire ='" + denumire + '\'' +
                ", Pret =" + pret +
                ", Categorie ='" + categorie + '\'' +
                '}';
    }
}
