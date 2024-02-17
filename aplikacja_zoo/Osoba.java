import java.util.Objects;

public class Osoba {
    String imie;
    String nazwisko;
    String id;

    public Osoba(String imie, String nazwisko, String id) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.id = id;
    }

    @Override
    public String toString() {
        return "Osoba{" +
                "imie='" + imie + '\'' +
                ", nazwisko='" + nazwisko + '\'' +
                ", id='" + id + '\'' +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Osoba osoba = (Osoba) o;
        return Objects.equals(imie, osoba.imie) && Objects.equals(nazwisko, osoba.nazwisko);
    }

    @Override
    public int hashCode() {
        return Objects.hash(imie, nazwisko);
    }
}
