package models;

import java.util.Date;

public class Promotie {
    private int id;
    private String descriere;
    private Date dataInceput;
    private Date dataSfarsit;
    private double reducere;

    // Metodele get
    public int getId() {
        return id;
    }

    public String getDescriere() {
        return descriere;
    }

    public Date getDataInceput() {
        return dataInceput;
    }

    public Date getDataSfarsit() {
        return dataSfarsit;
    }

    public double getReducere() {
        return reducere;
    }

    // Metodele set
    public void setId(int id) {
        this.id = id;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }

    public void setDataInceput(Date dataInceput) {
        this.dataInceput = dataInceput;
    }

    public void setDataSfarsit(Date dataSfarsit) {
        this.dataSfarsit = dataSfarsit;
    }

    public void setReducere(double reducere) {
        this.reducere = reducere;
    }

    @Override
    public String toString() {
        return "Promotie{" +
                "id=" + id +
                ", descriere='" + descriere + '\'' +
                ", dataInceput=" + dataInceput +
                ", dataSfarsit=" + dataSfarsit +
                ", reducere=" + reducere +
                '}';
    }
}
