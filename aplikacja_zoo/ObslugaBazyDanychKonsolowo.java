import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class ObslugaBazyDanychKonsolowo implements ObslugaBazyDanych{


    public void wyswietl_liste_gatunkow(){
        ArrayList<Gatunek> listaGatunkow = BazaDanych.getGatunki_bd();
        System.out.println("Lista gatunków:" + listaGatunkow);
        for (Gatunek g: listaGatunkow) {
            System.out.println(g.toString());
        }
    }
    @Override
    public void dodaj_nowy_gatunek(){
        Scanner scanner = new Scanner(System.in);

        String id = NoweId.przyznajId();

        System.out.println("Podaj nazwe gatunku");
        String nazwa = scanner.nextLine();

        System.out.println("Ustal diete dla gatunku");
        String kontrola = "N";
        Dieta dieta = new Dieta();
        do {
            System.out.println("Wybierz jedzenie z listy wpsiujac jego nazwe lub id");
            wyswietl_liste_rodzajow_jedzenia();
            String rodzajJedzenia = scanner.nextLine();

            System.out.println("Ustal ile jednostek jedzenia (domyslnie gram) potrzeba na kilogram zwierzecia");
            Integer jednostekJedzenia = scanner.nextInt();
            scanner.nextLine();

            Jedzenie jedzenieDoDodania = BazaDanych.znajdz_jedzenie(rodzajJedzenia);

            dieta.dodaj_pozycje(jedzenieDoDodania, jednostekJedzenia);

            System.out.println("Jesli zakonczyles tworzyc diete dla gatunku wpisz Y");
            kontrola = scanner.nextLine();

        }while (!kontrola.equals("Y"));

        System.out.println("Wpisz ile osobnik potrzebuje m2");
        Integer metry2 = scanner.nextInt();
        //scanner.nextLine();

        Gatunek nowyGatunek = new Gatunek(id, nazwa, dieta , metry2);
        BazaDanych.zarzadca.dodaj_nowy_gatunek(nowyGatunek);
        //scanner.close();
    }

    @Override
    public void dodaj_nowy_rodaj_jedzenia() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Podaj nazwe jedzenia");
        String nazwa = scanner.nextLine();

        BazaDanych.dodaj_nowy_rodaj_jedzenia(nazwa);

        scanner.close();
    }

    public void wyswietl_liste_rodzajow_jedzenia(){
        ArrayList<Jedzenie> listaJedzenia =  BazaDanych.getLista_dostepnego_jedzenia();
        System.out.println("Lista rodzajow jedzenia:");
        for (Jedzenie j: listaJedzenia) {
            System.out.println(j.toString());
        }
    }

    public void wyswietl_liste_wybiegow(){
        ArrayList<Wybieg> listaWybiegow = BazaDanych.getWybiegi_bd();
        System.out.println("Lista wybiegow: ");
        for (Wybieg w: listaWybiegow){
            System.out.println(w.getNumer());
        }
    }

    @Override
    public void dodaj_nowy_wybieg(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Wpisz numer wybiegu");
        wyswietl_liste_wybiegow();
        String numer = scanner.nextLine();

        System.out.println("Wpisz wielkośc wybiegu w m2");
        Integer wielkosc = scanner.nextInt();

        BazaDanych.dodaj_nowy_wybieg(new Wybieg(numer,wielkosc));
    }

    @Override
    public void wyswietl_zwierzeta_z_wybiegu() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Wpisz numer wybiegu");
        wyswietl_liste_wybiegow();
        String numer = scanner.nextLine();

        for (Zwierze z: BazaDanych.zwroc_dany_wybieg(numer).getZwierzeta()) {
            System.out.println(z);
        }
    }

    @Override
    public void sprawdz_pojemnosc_wybiegu() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Wpisz numer wybiegu. Dostepne wybiegi: ");
        wyswietl_liste_wybiegow();
        String numer = scanner.nextLine();


        Wybieg wybieg = BazaDanych.zwroc_dany_wybieg(numer);
        System.out.println("Wolne miejsce: " + wybieg.ile_wolnego_miejsca());
        System.out.println("Zajete miejsce: " + wybieg.ile_zajetego_miejsca());
    }

    @Override
    public void sprawdz_pojemnosc_wybiegow() {
        for (Wybieg w: BazaDanych.getWybiegi_bd()
             ) {
            System.out.println("Wybieg nm " + w.getNumer());
            System.out.println("    Wolne miejsce: " + w.ile_wolnego_miejsca());
            System.out.println("    Zajete miejsce: " + w.ile_zajetego_miejsca());
        }
    }

    @Override
    public void wyswietl_opiekunow_wybiegu() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Wpisz numer wybiegu");
        wyswietl_liste_wybiegow();
        String numer = scanner.nextLine();
        for (Wybieg w: BazaDanych.getWybiegi_bd()
             ) {
            System.out.println(w.getNumer());
        }

        Wybieg wybieg = BazaDanych.zwroc_dany_wybieg(numer);
        System.out.println("Opiekunowie wybiegu " + numer);
        for (Opiekun o: wybieg.getOpiekunowie()) {
            System.out.println(o.toString());
        }
    }


    public void przyznaj_opiekuna(){

        Scanner scanner = new Scanner(System.in);

        System.out.println("Wybierz pracownika z dostepnych wpisuja jego id");
        wyswietl_wszytkich_opiekunow();
        String id = scanner.nextLine();

        System.out.println("Wybierz wybieg wpisujac numer z dostepnych");
        wyswietl_liste_wybiegow();
        String numer_wybiegu = scanner.nextLine();

        BazaDanych.zarzadca.przyznaj_opiekuna(BazaDanych.zwroc_opiekuna_z_id(id), numer_wybiegu);

    }

    public void wyswietl_wszytkich_opiekunow(){
        for (Opiekun o: BazaDanych.getOpiekunowie()
             ) {
            System.out.println(o.toString());
        }
    }

    public void wyswietl_wszystkich_opiekunow_wybiegu(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Wybierz wybieg z dostepnych przez jego ID");
        wyswietl_liste_wybiegow();

        String nmWybiegu = scanner.nextLine();

        for (Opiekun o: BazaDanych.zwroc_dany_wybieg(nmWybiegu).getOpiekunowie()
        ) {
            System.out.println(o.toString());
        }
    }

    public void dodaj_opiekuna(){//_do_systemu(){
        Scanner scanner = new Scanner(System.in);

        String id = NoweId.przyznajId();

        System.out.println("Podaj imie opiekuna");
        String imie = scanner.nextLine();

        System.out.println("Podaj nazwisko opiekuna");
        String nazwisko = scanner.nextLine();

        Opiekun opiekun = new Opiekun(imie, nazwisko, id);

        BazaDanych.zarzadca.dodaj_opiekuna(opiekun);
    }

    public void dodaj_zwierze_do_systemu(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Wybierz gatunek zwierzecia");
        wyswietl_liste_gatunkow();
        String nazwaGatunku = scanner.nextLine();
        Gatunek gatunek = BazaDanych.zwroc_podany_gatunek(nazwaGatunku);

        String id = NoweId.przyznajId();

        System.out.println("Podaj imie zwierzecia");
        String imie = scanner.nextLine();

        Dieta obecna_dieta = gatunek.getDieta_na_kg_osobnika();
        System.out.println("Podaj wage zwierzecia");
        Integer waga = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Podaj numer wybiegu dla zwierzecia");
        String numer_wybiegu = scanner.nextLine();
        scanner.nextLine();



        Zwierze noweZwierze = new Zwierze(gatunek, id, imie, waga, numer_wybiegu);
        BazaDanych.zarzadca.wprowadz_zwierze_do_systemu(noweZwierze, numer_wybiegu);


    }


    public void wyswietl_laczne_zzycie_paszy_na_x_dni(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj liczbe dni na ile wysiwetlic zuzycie jedzenia");
        Integer liczbaDni = scanner.nextInt();

        System.out.println("Tyle potrzeba jedzenia zeby starczyło na podany okres czasu dla calego zoo");
        Dieta dietaNaXDni = BazaDanych.pobierzInstancje().zuzycie_jedzenia_dla_x_dni_na_zoo(liczbaDni);

        System.out.println(dietaNaXDni.toString());
    }

}
