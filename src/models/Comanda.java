package models;

import java.util.List;

public class Comanda {
    private int id;
    private Client client;
    private List<Produs> produse;

    public int getId() {
        return id;
    }

    public Client getClient() {
        return client;
    }

    public List<Produs> getProduse() {
        return produse;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setProduse(List<Produs> produse) {
        this.produse = produse;
    }

    @Override
    public String toString() {
        return "Comanda{" +
                "ID =" + id +
                ", Client =" + client +
                ", Produse =" + produse +
                '}';
    }
}
