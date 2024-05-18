package models;

import java.util.Date;

public class Livrare {
    private int id;
    private Comanda comanda;
    private String adresaDestinatie;
    private Date dataLivrare;

    public int getId() {
        return id;
    }

    public Comanda getComanda() {
        return comanda;
    }

    public String getAdresaDestinatie() {
        return adresaDestinatie;
    }

    public Date getDataLivrare() {
        return dataLivrare;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setComanda(Comanda comanda) {
        this.comanda = comanda;
    }

    public void setAdresaDestinatie(String adresaDestinatie) {
        this.adresaDestinatie = adresaDestinatie;
    }

    public void setDataLivrare(Date dataLivrare) {
        this.dataLivrare = dataLivrare;
    }

    @Override
    public String toString() {
        return "Livrare{" +
                "ID =" + id +
                ", Comanda =" + comanda +
                ", adresaDestinatie ='" + adresaDestinatie + '\'' +
                ", dataLivrare =" + dataLivrare +
                '}';
    }
}
