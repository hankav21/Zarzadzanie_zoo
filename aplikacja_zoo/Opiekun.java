import java.util.ArrayList;
import java.util.Objects;

public class Opiekun extends Osoba{

    public Opiekun(String imie, String nazwisko, String id) {
        super(imie, nazwisko, id);
    }


    void zatwierdz_pobranie_jedzenia(){
    }

    void zatwierdz_nakarmienie(){

    }

    public boolean pobierz_jedzenie_z_Magazynu(){
        Dieta lacznaDietaDlaWszytkichWybiegow = new Dieta();
        ArrayList<Wybieg> wybiegiOpiekuna = zwrocSwojeWybiegi();
        for (Wybieg w: wybiegiOpiekuna) {
            lacznaDietaDlaWszytkichWybiegow.zsumujDiety(lacznaDietaDlaWszytkichWybiegow.zsumujDietyWszystkichZwierzat(w.getZwierzeta()));
        }

        if(!BazaDanych.magazyn.czy_jest_wystarczajaca_ilosc_jedzenia_w_magazynie(lacznaDietaDlaWszytkichWybiegow)) return false;
        BazaDanych.magazyn.pobierz_z_magazynu(lacznaDietaDlaWszytkichWybiegow);


        return true;
    }

    public ArrayList<Wybieg> zwrocSwojeWybiegi(){
        ArrayList<Wybieg> wybiegiOpiekuna = new ArrayList<>();
        for (Wybieg w: BazaDanych.getWybiegi_bd()) {
            if(w.czy_jest_ten_opiekun(id) == true) wybiegiOpiekuna.add(w);
        }
        return wybiegiOpiekuna;
    }


    @Override
    public String toString() {
        return "Opiekun{" +
                "imie='" + imie + '\'' +
                ", nazwisko='" + nazwisko + '\'' +
                ", id='" + id + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Osoba osoba = (Osoba) o;
//        return Objects.equals(imie, osoba.imie) && Objects.equals(nazwisko, osoba.nazwisko);
        return super.equals(o);
    }


}
