package models;

public class Angajat extends Utilizator {
    private double salariu;
    private String rol;

    public Angajat() {}

    public Angajat(int id, String nume, String prenume, String email, String parola, double salariu, String rol) {
        super(id, nume, prenume, email, parola);
        this.salariu = salariu;
        this.rol = rol;
    }

    public double getSalariu() {
        return salariu;
    }

    public void setSalariu(double salariu) {
        this.salariu = salariu;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return super.toString() + "Salariu: " + salariu + '\n' +
                "Rol: " + rol + '\n';
    }
}
