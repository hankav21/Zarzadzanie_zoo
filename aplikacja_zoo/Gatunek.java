import java.util.Objects;

public class Gatunek {
    private String id;
    private String nazwa;
    private Dieta dieta_na_kg_osobnika;
    private Integer metrow_na_osobnika;

    public Gatunek(String id, String nazwa, Dieta dieta_na_kg_osobnika, Integer metrow_na_osobnika) {
        this.id = id;
        this.nazwa = nazwa;
        this.dieta_na_kg_osobnika = dieta_na_kg_osobnika;
        this.metrow_na_osobnika = metrow_na_osobnika;
        BazaDanych.dodaj_nowy_gatunek(this);
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

    public Dieta getDieta_na_kg_osobnika() {
        return dieta_na_kg_osobnika;
    }

    public void setDieta_na_kg_osobnika(Dieta dieta_na_kg_osobnika) {
        this.dieta_na_kg_osobnika = dieta_na_kg_osobnika;
    }

    public Integer getMetrow_na_osobnika() {
        return metrow_na_osobnika;
    }

    public void setMetrow_na_osobnika(Integer metrow_na_osobnika) {
        this.metrow_na_osobnika = metrow_na_osobnika;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Gatunek gatunek = (Gatunek) o;
        return Objects.equals(id, gatunek.id) && Objects.equals(nazwa, gatunek.nazwa) && Objects.equals(dieta_na_kg_osobnika, gatunek.dieta_na_kg_osobnika) && Objects.equals(metrow_na_osobnika, gatunek.metrow_na_osobnika);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nazwa, dieta_na_kg_osobnika, metrow_na_osobnika);
    }

    @Override
    public String toString() {
        return "Gatunek{" +
                "id='" + id + '\'' +
                ", nazwa='" + nazwa + '\'' +
                ", dieta_na_kg_osobnika=" + dieta_na_kg_osobnika +
                ", metrow_na_osobnika=" + metrow_na_osobnika +
                '}';
    }
}
