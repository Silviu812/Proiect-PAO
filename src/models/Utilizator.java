package models;

import java.util.Objects;

public abstract class Utilizator {
    private int id;
    private String nume;
    private String prenume;
    private String email;
    private String parola;

    public Utilizator() {}

    public Utilizator(int id, String nume, String prenume, String email, String parola) {
        this.id = id;
        this.nume = nume;
        this.prenume = prenume;
        this.email = email;
        this.parola = parola;
    }

    public int getId() {
        return id;
    }

    public String getNume() {
        return nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public String getEmail() {
        return email;
    }

    public String getParola() {
        return parola;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setParola(String parola) {
        this.parola = parola;
    }

    @Override
    public String toString() {
        return "Utilizator:" + '\n' +
                "ID: " + getId() + '\n' +
                "Nume: " + getNume() + " " + getPrenume() + '\n' +
                "Email: " + getEmail() + '\n';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Utilizator that = (Utilizator) o;
        return id == that.id && Objects.equals(nume, that.nume) && Objects.equals(prenume, that.prenume) && Objects.equals(email, that.email) && Objects.equals(parola, that.parola);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nume, prenume, email, parola);
    }
}
