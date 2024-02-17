import java.util.ArrayList;

public class Wybieg {
    private String numer;
    private Integer wielkosc;   //w metrach kwadratowych
    private ArrayList<Zwierze> zwierzeta;
    private ArrayList<Opiekun> opiekunowie;

    public Wybieg(String numer, Integer wielkosc) {
        this.numer = numer;
        this.wielkosc = wielkosc;
        this.zwierzeta = new ArrayList<>();
        this.opiekunowie = new ArrayList<>();
        BazaDanych.dodaj_nowy_wybieg(this);
    }

    public void dodaj_opiekuna(Opiekun opiekun){
        this.opiekunowie.add(opiekun);
    }

    public Integer ile_zajetego_miejsca(){
        Integer zajete_miejsce = 0;
        for (Zwierze z: zwierzeta) {
            zajete_miejsce += z.gatunek.getMetrow_na_osobnika();
        }
        return zajete_miejsce;
    }

    public boolean czy_jest_ten_opiekun(String id){
        for (Opiekun o: opiekunowie) {
            if(o.id.equals(id)) return true;
        }
        return false;

    }

    public Dieta dieta_dla_wybiegu(){
        Dieta lacznaDieta = new Dieta();
        return lacznaDieta.zsumujDietyWszystkichZwierzat(zwierzeta);
    }

    public Integer ile_wolnego_miejsca(){
        return wielkosc - ile_zajetego_miejsca();
    }

    public void dodaj_zwierze(Zwierze zwierze){
        this.zwierzeta.add(zwierze);
    }

    //.....getter i settery
    public String getNumer() {
        return numer;
    }

    public void setNumer(String numer) {
        this.numer = numer;
    }

    public Integer getWielkosc() {
        return wielkosc;
    }

    public void setWielkosc(Integer wielkosc) {
        this.wielkosc = wielkosc;
    }

    public ArrayList<Zwierze> getZwierzeta() {
        return zwierzeta;
    }

    public void setZwierzeta(ArrayList<Zwierze> zwierzeta) {
        this.zwierzeta = zwierzeta;
    }

    public ArrayList<Opiekun> getOpiekunowie() {
        return opiekunowie;
    }

    public void setOpiekunowie(ArrayList<Opiekun> opiekunowie) {
        this.opiekunowie = opiekunowie;
    }

    @Override
    public String toString() {
        return "Wybieg{" +
                "numer='" + numer + '\'' +
                ", wielkosc=" + wielkosc +
                ", zwierzeta=" + zwierzeta +
                ", opiekunowie=" + opiekunowie +
                '}';
    }
}
