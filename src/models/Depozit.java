package models;

import java.util.List;

public class Depozit {
    private int id;
    private String adresa;
    private List<Produs> produseStocate;

    public int getId() {
        return id;
    }

    public String getAdresa() {
        return adresa;
    }

    public List<Produs> getProduseStocate() {
        return produseStocate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public void setProduseStocate(List<Produs> produseStocate) {
        this.produseStocate = produseStocate;
    }

    @Override
    public String toString() {
        return "Depozit{" +
                "ID =" + id +
                ", Adresa ='" + adresa + '\'' +
                ", produseStocate =" + produseStocate +
                '}';
    }
}
