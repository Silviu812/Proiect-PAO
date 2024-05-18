package models;

public class Recenzie {
    private int id;
    private String comentariu;
    private int rating;

    public int getId() {
        return id;
    }

    public String getComentariu() {
        return comentariu;
    }

    public int getRating() {
        return rating;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setComentariu(String comentariu) {
        this.comentariu = comentariu;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Recenzie{" +
                "ID =" + id +
                ", Comentariu ='" + comentariu + '\'' +
                ", Rating =" + rating +
                '}';
    }
}
