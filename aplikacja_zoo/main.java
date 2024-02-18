import java.util.ArrayList;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        BazaDanych bd = BazaDanych.pobierzInstancje();
        ObslugaBazyDanychKonsolowo obdk = new ObslugaBazyDanychKonsolowo();
        ObslugaMagazynuKonsolowo omk = new ObslugaMagazynuKonsolowo();
        ArrayList<Dieta> zamowienia = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("Wciśnij Z jesli jestes zarzadca, wpisz O jesli opiekunem");
            String wybor_uzytkownika = scanner.nextLine();
            boolean zalogowany = false;

            switch (wybor_uzytkownika){
                case "Z":

                    zalogowany = true;
                    System.out.println("WItaj, jesteś w konsoli przystosowanej dla Zarzadcy");

                    do {
                        System.out.println("Ponizej masz liste dostepnych czynnosci:");

                        System.out.println("1 = zamow_jedzenie dla calego zoo na x dni");
                        System.out.println("2 = sprawdz_stan_calego magazynu");
                        System.out.println("3 = sprawdz_stan_magazynu - konkretne jedzenie");
                        System.out.println("4 = sprawdz zuzycie jedzenia na x dni dla calego zoo ");
                        System.out.println("5 = przydznaj_opiekuna do wybiegu");
                        System.out.println("6 = wprowadz_opiekuna_do_systemu()");
                        System.out.println("7 = wprowadz_zwierze_do_systemu()");
                        System.out.println("8 = wyswietl_dostepne_rodzaje_jedzenia");
                        System.out.println("9 = dodaj nowy rodzaj jedzenia");
                        System.out.println("10 = dodaj nowy gatunek");
                        System.out.println("11 = dodaj nowy wybieg");
                        System.out.println("12 = Sprawdz pojemnosc wybiegu");
                        System.out.println("13 = Sprawdz pojemnosc wybiegow");
                        System.out.println("14 = Wyswietl wszystkie zwierzeta na wybiegu");
                        System.out.println("15 = Wyswietl wszystkich opiekunow wybiegu");
                        System.out.println("16 = Wyswietl wszystkich opiekunow");
                        System.out.println("17 = Przyjmij zamowienie do magazynu");
                        System.out.println("18 = wyloguj sie");

                        wybor_uzytkownika = scanner.nextLine();
                        switch (wybor_uzytkownika){
                            case "1":
                                System.out.println("Podajna ile dni chcez zamowic jedzenie dla calego zoo");
                                Integer iloscDni = scanner.nextInt();
                                Dieta dietaNaXDni = BazaDanych.pobierzInstancje().zuzycie_jedzenia_dla_x_dni_na_zoo(iloscDni);
                                BazaDanych.zarzadca.zamow_jedzenie(dietaNaXDni);
                                zamowienia.add(dietaNaXDni);
                                break;
                            case "2":
                                BazaDanych.zarzadca.sprawdz_stan_magazynu(BazaDanych.magazyn);
                                break;
                            case "3":
                                System.out.println("Wpisz nazwe lub id interesujacego cie jedzenia z listy");
                                obdk.wyswietl_liste_rodzajow_jedzenia();
                                wybor_uzytkownika = scanner.nextLine();
                                BazaDanych.zarzadca.sprawdz_stan_magazynu(BazaDanych.magazyn,BazaDanych.znajdz_jedzenie(wybor_uzytkownika));
                                break;
                            case "4":
                                obdk.wyswietl_laczne_zzycie_paszy_na_x_dni();
                                break;
                            case "5":
                                obdk.przyznaj_opiekuna();
                                break;
                            case "6":
                                obdk.dodaj_opiekuna();//_do_systemu();
                                break;
                            case "7":
                                obdk.dodaj_zwierze_do_systemu();
                                break;
                            case "8":
                                obdk.wyswietl_liste_rodzajow_jedzenia();
                                break;
                            case "9":
                                obdk.dodaj_nowy_rodaj_jedzenia();
                                break;
                            case "10":
                                obdk.dodaj_nowy_gatunek();
                                break;
                            case "11":
                                obdk.dodaj_nowy_wybieg();
                                break;
                            case "12":
                                obdk.sprawdz_pojemnosc_wybiegu();
                                break;
                            case "13":
                                obdk.sprawdz_pojemnosc_wybiegow();
                                break;
                            case "14":
                                obdk.wyswietl_zwierzeta_z_wybiegu();
                                break;
                            case "15":
                                obdk.wyswietl_wszystkich_opiekunow_wybiegu();
                                break;
                            case "16":
                                obdk.wyswietl_wszytkich_opiekunow();
                                break;
                            case "17":
                                if(zamowienia.size() == 0) {
                                    System.out.println("Obecnie nie ma oczekujacych zamówien do odbioru");
                                    break;
                                }
                                Integer numerZamowienia = 0;
                                System.out.println("Wybiezr numer zamowienia do przyjecia do magazynu");
                                for (Dieta d: zamowienia) {
                                    System.out.println("Numer zamowienia: " +numerZamowienia + d.toString());
                                    numerZamowienia++;
                                }
                                numerZamowienia = scanner.nextInt();
                                Dieta zamowienie = zamowienia.get(numerZamowienia);

                                zamowienia.remove(zamowienie);
                                BazaDanych.zarzadca.przyjmij_zamowienie_do_magazynu(zamowienie,BazaDanych.magazyn);
                                break;
                            case "18":
                                zalogowany = false;
                                break;

                        }


                    }while(zalogowany);

                case "O":
                    System.out.println("Wpisz numer id opiekuna");
                    System.out.println(BazaDanych.getOpiekunowie());
                    String numerOpiekuna = scanner.nextLine();
                    Opiekun zalogowanyOpiekun = BazaDanych.zwroc_opiekuna_z_id(numerOpiekuna);
                    System.out.println("Wybrany opiekun = " + zalogowanyOpiekun);
                    zalogowany = true;

                    do {
                        System.out.println("Wybierz z listy:");
                        System.out.println("1 = Sprawdz wybiegi ktore musisz obsluzyc");
                        System.out.println("2 = Pobierz z magazynu wymagane");
                        System.out.println("3 = wyloguj sie");

                        wybor_uzytkownika = scanner.nextLine();

                        switch (wybor_uzytkownika) {
                            case "1":
                                System.out.println("Twoje wybiegi: " + zalogowanyOpiekun.zwrocSwojeWybiegi());
                                break;
                            case "2":
                                ArrayList<Wybieg> wybiegiOpiekuna =  zalogowanyOpiekun.zwrocSwojeWybiegi();
                                if(zalogowanyOpiekun.pobierz_jedzenie_z_Magazynu())
                                    System.out.println("Pobrałes jedzneie z magazynu");
                                else
                                    System.out.println("niewystarczajaca ilosc jedzenia w magazynie. skontaktuj sie z zarzadca");
                                break;
                            case "3":
                                zalogowany = false;
                                break;
                        }

                    }while (zalogowany);


                    //break;
            }
        }while (true);



    }
}

class x{
    public static void main(String[] args) {
        BazaDanych bd = BazaDanych.pobierzInstancje();
        BazaDanych.dodaj_nowy_gatunek(new Gatunek("x","nazwa",new Dieta(),2));
        System.out.println(BazaDanych.zwroc_podany_gatunek("x"));
    }


}