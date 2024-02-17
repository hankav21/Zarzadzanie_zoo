import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Zarzadca extends Osoba implements DostepBazaDanych{

    public Zarzadca(String imie, String nazwisko, String id) {
        super(imie, nazwisko, id);
    }

    void zamow_jedzenie(Dieta zamowienie){

    }

    void przyjmij_zamowienie_do_magazynu(Dieta zamowienie, Magazyn magazyn){
        for (Map.Entry<Jedzenie, Integer> entry : zamowienie.getProdukt_gramy().entrySet()) {
            Jedzenie jedzenie = entry.getKey();
            Integer ilosc = entry.getValue();

            magazyn.dodaj_do_magazynu(jedzenie, ilosc);
        }

    }

    void sprawdz_stan_magazynu(Magazyn magazyn){
        ObslugaMagazynuKonsolowo omk = new ObslugaMagazynuKonsolowo();
        omk.wyswietlWszystko(magazyn);
    }

    //wyswietla w konsoli ile jest danego produktu na magazynie
    void sprawdz_stan_magazynu(Magazyn magazyn, Jedzenie jedzenie){
        ObslugaMagazynuKonsolowo omk = new ObslugaMagazynuKonsolowo();
        omk.wyswietl_ilosc_w_magazynie(magazyn, jedzenie);
    }

    //wyswietla w konsoli ile jest danego produktu na magazynie
    void sprawdz_stan_magazynu(Magazyn magazyn, String jedzenie){
        ObslugaMagazynuKonsolowo omk = new ObslugaMagazynuKonsolowo();
        omk.wyswietl_ilosc_w_magazynie(magazyn, jedzenie);
    }

    //sprawdza czy ejst wystarczajaca ilość w magazynie
    public void sprawdz_stan_magazynu(Magazyn magazyn, Dieta dieta){
        ObslugaMagazynuKonsolowo omk = new ObslugaMagazynuKonsolowo();
        omk.wyswietl_ilosc_w_magazynie_na_diete(magazyn, dieta);
    }

    public void przyznaj_opiekuna(Opiekun opiekun, String numer_wybiegu){
        BazaDanych.przyznaj_opiekuna(opiekun, numer_wybiegu);
    }

    @Override
    public void dodaj_zwierze_do_wybiegu(Zwierze zwierze, String nmWybiegu) {
        BazaDanych.dodaj_zwierze_do_wybiegu(zwierze, nmWybiegu);
    }

    @Override
    public void dodaj_opiekuna(Opiekun opiekun) {
        BazaDanych.dodaj_opiekuna(opiekun);
    }

    public void wprowadz_zwierze_do_systemu(Zwierze zwierze, String nmWybiegu){
        BazaDanych.dodaj_zwierze_do_wybiegu(zwierze, nmWybiegu);
    }

    public void dodaj_nowy_gatunek(Gatunek gatunek){
        BazaDanych.dodaj_nowy_gatunek(gatunek);
    }

    @Override
    public boolean czy_gatunek_o_tej_nazwie_na_liscie(String szukanyGatunek, ArrayList<Gatunek> listaGatunkow) {
        return BazaDanych.czy_gatunek_o_tej_nazwie_na_liscie(szukanyGatunek,listaGatunkow);
    }

    @Override
    public Gatunek zwroc_podany_gatunek(String identyfikacja) {
        return BazaDanych.zwroc_podany_gatunek(identyfikacja);
    }

    @Override
    public void dodaj_nowy_rodaj_jedzenia(String nazwa) {
        BazaDanych.dodaj_nowy_rodaj_jedzenia(nazwa);
    }

    @Override
    public void dodaj_nowy_rodaj_jedzenia(Jedzenie jedzenie) {
        BazaDanych.dodaj_nowy_rodaj_jedzenia(jedzenie);
    }

    @Override
    public Jedzenie znajdz_jedzenie(String identyfikacja) {
        return BazaDanych.znajdz_jedzenie(identyfikacja);
    }

    @Override
    public boolean czy_istnieje_jedzenie_o_tej_nazwie_na_liscie(String szukaneJedzenie, ArrayList<Jedzenie> listaJedzenia) {
        return BazaDanych.czy_istnieje_jedzenie_o_tej_nazwie_na_liscie(szukaneJedzenie, listaJedzenia);
    }

    @Override
    public void dodaj_nowy_wybieg(Wybieg wybieg) {
        BazaDanych.dodaj_nowy_wybieg(wybieg);
    }

    @Override
    public boolean czy_istnieje_taki_wybieg_na_liscie(String nmWybiegu, ArrayList<Wybieg> listaWybiegow) {
        return BazaDanych.czy_istnieje_taki_wybieg_na_liscie(nmWybiegu, listaWybiegow);
    }

    public void wyswietl_dostepne_rodzaje_jedzenia(Magazyn magazyn){
        ObslugaMagazynuKonsolowo omk = new ObslugaMagazynuKonsolowo();
        omk.wyswietl_dostepne_rodzaje_jedzenia(magazyn);
    }

    //..................Magazyn


}
