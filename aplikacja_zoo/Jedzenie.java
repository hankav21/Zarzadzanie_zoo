import java.util.ArrayList;
import java.util.Objects;

public class Jedzenie {
    private String id;
    private String nazwa;

    public Jedzenie(String id, String nazwa) {
        this.id = id;
        this.nazwa = nazwa;
        //BazaDanych.dodaj_nowy_rodaj_jedzenia(this);
    }




    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Jedzenie jedzenie = (Jedzenie) o;
        return Objects.equals(id, jedzenie.id) && Objects.equals(nazwa, jedzenie.nazwa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nazwa);
    }

    @Override
    public String toString() {
        return "Id: " + id + " nazwa: " + nazwa;
    }
}


