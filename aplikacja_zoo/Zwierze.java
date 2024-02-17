import java.util.Objects;

public class Zwierze {
    Gatunek gatunek;
    String id;
    String imie;
    Dieta obecna_dieta;
    Integer waga;
    String numer_wybiegu;

    public Zwierze(Gatunek gatunek, String id, String imie, Integer waga, String numer_wybiegu) {
        this.gatunek = gatunek;
        this.id = id;
        this.imie = imie;
        Dieta dietaDlaGatunku = gatunek.getDieta_na_kg_osobnika();
        this.obecna_dieta = dietaDlaGatunku.dostosuj_diete_do_wagi(dietaDlaGatunku,waga);
        this.waga = waga;
        this.numer_wybiegu = numer_wybiegu;
    }

    public Gatunek getGatunek() {
        return gatunek;
    }

    public void setGatunek(Gatunek gatunek) {
        this.gatunek = gatunek;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public Dieta getObecna_dieta() {
        return obecna_dieta;
    }

    public void setObecna_dieta(Dieta obecna_dieta) {
        this.obecna_dieta = obecna_dieta;
    }

    public Integer getWaga() {
        return waga;
    }

    public void setWaga(Integer waga) {
        this.waga = waga;
    }

    public String getNumer_wybiegu() {
        return numer_wybiegu;
    }

    public void setNumer_wybiegu(String numer_wybiegu) {
        this.numer_wybiegu = numer_wybiegu;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Zwierze zwierze = (Zwierze) o;
        return Objects.equals(gatunek, zwierze.gatunek) && Objects.equals(id, zwierze.id) && Objects.equals(imie, zwierze.imie) && Objects.equals(obecna_dieta, zwierze.obecna_dieta) && Objects.equals(waga, zwierze.waga) && Objects.equals(numer_wybiegu, zwierze.numer_wybiegu);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gatunek, id, imie, obecna_dieta, waga, numer_wybiegu);
    }

    @Override
    public String toString() {
        return "Zwierze{" +
                "gatunek=" + gatunek +
                ", id='" + id + '\'' +
                ", imie='" + imie + '\'' +
                ", obecna_dieta=" + obecna_dieta +
                ", waga=" + waga +
                ", numer_wybiegu='" + numer_wybiegu + '\'' +
                '}';
    }
}
