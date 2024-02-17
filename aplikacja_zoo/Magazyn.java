import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

interface ObslugaMagazynu{
    public void wyswietlWszystko(Magazyn magazyn);
    public void wyswietl_ilosc_w_magazynie(Magazyn magazyn, Jedzenie jedzenie);
    public void wyswietl_ilosc_w_magazynie(Magazyn magazyn, String jedzenie);
    public void wyswietl_ilosc_w_magazynie_na_diete(Magazyn magazyn, Dieta dieta);
}

interface DostepMagazyn{
    public void dodaj_do_magazynu(Jedzenie jedzenie, Integer ilosc);
    public Integer zwroc_ilosc_paszy(String jedzenie);
    public Integer zwroc_ilosc_paszy(Jedzenie jedzenie);
}

public class Magazyn implements DostepMagazyn{
    HashMap<Jedzenie, Integer> pasza_ilosc;

    public Magazyn() {
        this.pasza_ilosc = new HashMap<>();
    }


    public void dodaj_do_magazynu(Jedzenie jedzenie, Integer ilosc){
        Integer obecna_ilosc = pasza_ilosc.get(jedzenie);
        if(obecna_ilosc == null) {
            pasza_ilosc.put(jedzenie, ilosc);

//            BazaDanych bd = BazaDanych.pobierzInstancje();
            BazaDanych.dodaj_nowy_rodaj_jedzenia(jedzenie);
        }
        else pasza_ilosc.put(jedzenie, obecna_ilosc + ilosc);
    }

    public Integer zwroc_ilosc_paszy(String jedzenie){
        for (Map.Entry<Jedzenie, Integer> entry : pasza_ilosc.entrySet()) {
            Jedzenie j = entry.getKey();
            if(j.getNazwa().equals(jedzenie)) return entry.getValue();
        }
        return -1;
    }

    public void pobierz_z_magazynu(Dieta dieta){
        if(czy_jest_wystarczajaca_ilosc_jedzenia_w_magazynie(dieta)) {
            for (Map.Entry<Jedzenie, Integer> entry : dieta.produkt_gramy.entrySet()) {
                Jedzenie jedzenie = entry.getKey();
                int ilosc = entry.getValue();

                if (this.pasza_ilosc.containsKey(jedzenie)) {
                    int aktualnaIlosc = this.pasza_ilosc.get(jedzenie);
                    this.pasza_ilosc.put(jedzenie, aktualnaIlosc - ilosc);
                } else {
                    this.pasza_ilosc.put(jedzenie, ilosc);
                }
            }
        }
    }

    public boolean czy_jest_wystarczajaca_ilosc_jedzenia_w_magazynie(Dieta dieta){
        for (Map.Entry<Jedzenie, Integer> entry : pasza_ilosc.entrySet()) {
            Jedzenie j = entry.getKey();
            Integer iloscJedzeniaWMagazynie = entry.getValue();
            if (dieta.czy_jest_to_jedzenie_w_diecie(j) == true) {
                Integer wymaganaIloscJedzenia = dieta.ile_jest_tego_jedzenia_w_diecie(j);
                if((iloscJedzeniaWMagazynie - wymaganaIloscJedzenia) <= 0) return false;
            }

        }
        return true;
    }

    public Integer zwroc_ilosc_paszy(Jedzenie jedzenie){
        return pasza_ilosc.get(jedzenie);
    }







}
