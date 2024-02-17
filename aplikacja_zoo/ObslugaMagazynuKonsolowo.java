import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ObslugaMagazynuKonsolowo implements ObslugaMagazynu{

    public void wyswietlWszystko(Magazyn magazyn) {
        System.out.println("Stan magazynu: ");
        for (Map.Entry<Jedzenie, Integer> entry : magazyn.pasza_ilosc.entrySet()) {
            Jedzenie jedzenie = entry.getKey();
            Integer gramy = entry.getValue();
            System.out.println("Jedzenie: " + jedzenie.getNazwa() + ", Ilość: " + gramy);
        }
    }

    public void wyswietl_ilosc_w_magazynie(Magazyn magazyn, Jedzenie jedzenie){
        System.out.println("Jedzenie: " + jedzenie.getNazwa() + ", Ilość: " + magazyn.pasza_ilosc.get(jedzenie));
    }

    public void wyswietl_ilosc_w_magazynie(Magazyn magazyn, String jedzenie){
        System.out.println("Jedzenie: " + jedzenie + ", Ilość: " + magazyn.zwroc_ilosc_paszy(jedzenie));
        //to do ogarniecia bo wyrzuca null
    }

    public void wyswietl_ilosc_w_magazynie_na_diete(Magazyn magazyn, Dieta dieta){
        System.out.println("Stan magazynu dla wybranej diety: ");
        for (Map.Entry<Jedzenie, Integer> entry: dieta.getProdukt_gramy().entrySet()) {
            Jedzenie jedzenie = entry.getKey();
            Integer wymagana_ilosc = entry.getValue();
            Integer ilosc_w_magazynie = magazyn.zwroc_ilosc_paszy(jedzenie);

            if (ilosc_w_magazynie == null) System.out.println("!Jedzenie: " + jedzenie.getNazwa() + " brak w magazynie");
            else if(wymagana_ilosc > ilosc_w_magazynie)
                    System.out.println("!Jedzenie: " + jedzenie.getNazwa() + ", Ilość w magazynie: " + ilosc_w_magazynie
                    + ", Wymagana ilość: " + wymagana_ilosc + ", Brakuje: " + (wymagana_ilosc - ilosc_w_magazynie));
                else System.out.println("Jedzenie: " + jedzenie.getNazwa() + ", Ilość w magazynie: " + ilosc_w_magazynie
                        + ", Wymagana ilość: " + wymagana_ilosc + ", Zostanie: " + (ilosc_w_magazynie - wymagana_ilosc));
        }

    }

    public void wyswietl_na_ile_dni_starczy_kazdego_jedzenia(Magazyn magazyn){

    }

    public void dodaj_do_magazynu(){
        Scanner scanner = new Scanner(System.in);





    }

    public void wyswietl_dostepne_rodzaje_jedzenia(Magazyn magazyn){
        List<Jedzenie> listaRodzajowJedzenia = BazaDanych.getLista_dostepnego_jedzenia();
        System.out.println("Lista dostepnych rodzajów jedzenia:");
        for (Jedzenie jedzenie : listaRodzajowJedzenia) {
            System.out.println("Id: " + jedzenie.getId() + " nazwa: " +jedzenie.getNazwa());
        }
    }

}
