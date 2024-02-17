import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

interface DostepBazaDanych {
    void dodaj_nowy_gatunek(Gatunek gatunek);
    boolean czy_gatunek_o_tej_nazwie_na_liscie(String szukanyGatunek, ArrayList<Gatunek> listaGatunkow);
    Gatunek zwroc_podany_gatunek(String identyfikacja);
    //lista dostepnego jedzenia.............................................
    void dodaj_nowy_rodaj_jedzenia(String nazwa);
    void dodaj_nowy_rodaj_jedzenia(Jedzenie jedzenie);
    Jedzenie znajdz_jedzenie(String identyfikacja);
    boolean czy_istnieje_jedzenie_o_tej_nazwie_na_liscie(String szukaneJedzenie, ArrayList<Jedzenie> listaJedzenia);
    //wybiegi........................................................
    void dodaj_nowy_wybieg(Wybieg wybieg);
    boolean czy_istnieje_taki_wybieg_na_liscie(String nmWybiegu, ArrayList<Wybieg> listaWybiegow);
    void przyznaj_opiekuna(Opiekun opiekun, String nmWybiegu);
    void dodaj_zwierze_do_wybiegu(Zwierze zwierze, String nmWybiegu);
    //opiekunowie............................................................................................
    void dodaj_opiekuna(Opiekun opiekun);
}

interface ObslugaBazyDanych {
    void dodaj_nowy_gatunek();//Gatunek gatunek);
    //boolean czy_gatunek_o_tej_nazwie_na_liscie();//(String szukanyGatunek, ArrayList<Gatunek> listaGatunkow);
    //Gatunek zwroc_podany_gatunek();//(String identyfikacja);
    //lista dostepnego jedzenia.............................................
    void dodaj_nowy_rodaj_jedzenia();//(String nazwa);
    //-||- (Jedzenie jedzenie);
    //Jedzenie znajdz_jedzenie();//String identyfikacja);
    //boolean czy_istnieje_jedzenie_o_tej_nazwie_na_liscie(); //(String szukaneJedzenie, ArrayList<Jedzenie> listaJedzenia)
    //wybiegi........................................................
    void dodaj_nowy_wybieg();//(Wybieg wybieg)
    void wyswietl_zwierzeta_z_wybiegu();
    void sprawdz_pojemnosc_wybiegu();
    void sprawdz_pojemnosc_wybiegow();
    void wyswietl_opiekunow_wybiegu();

    void przyznaj_opiekuna();//(Opiekun opiekun, String nmWybiegu){
    //void dodaj_zwierze_do_wybiegu();//(Zwierze zwierze, String nmWybiegu){
    //opiekunowie............................................................................................
    void dodaj_opiekuna();//(Opiekun opiekun);




}

public class BazaDanych{

    //robimy rozwiazanie single tone
    private static BazaDanych instancja = null;

    private static ArrayList<Gatunek> gatunki_bd;
    private static ArrayList<Jedzenie> lista_dostepnego_jedzenia;
    private static ArrayList<Wybieg> wybiegi_bd;
    private static ArrayList<Opiekun> opiekunowie;
    private static ArrayList<Zwierze> zwierzeta;
    public static Zarzadca zarzadca;
    public static Magazyn magazyn;

    private BazaDanych() {

//        this.gatunki_bd = new ArrayList<>();
//        this.lista_dostepnego_jedzenia = new ArrayList<>();
//        this.wybiegi_bd = new ArrayList<>();
//        this.opiekunowie = new ArrayList<>();
//        this.zwierzeta = new ArrayList<>();
//        zarzadca = new Zarzadca("x","y","z");
        //this.magazyn = new Magazyn();

        //na potrzeby zal
        this.gatunki_bd = new ArrayList<>();
        Jedzenie j1 = new Jedzenie("0001", "Marchew");
        Jedzenie j2 = new Jedzenie("0002", "Siano");
        Jedzenie j3 = new Jedzenie("0003", "Kapusta");
        Dieta dieta1 = new Dieta();
        dieta1.dodaj_pozycje(j1,10);
        dieta1.dodaj_pozycje(j2,5);
        Dieta dieta2 = new Dieta();
        dieta2.dodaj_pozycje(j2,1);
        dieta2.dodaj_pozycje(j3,2);
        new Gatunek("001", "Lama", dieta1,20);
        new Gatunek("002", "Myszojelen", dieta2,10);
        this.lista_dostepnego_jedzenia = new ArrayList<>();
        lista_dostepnego_jedzenia.add(j1);
        lista_dostepnego_jedzenia.add(j2);
        lista_dostepnego_jedzenia.add(j3);
        this.wybiegi_bd = new ArrayList<>();
        Wybieg w1 = new Wybieg("A1",30);
        Wybieg w2 = new Wybieg("B1",50);

        this.opiekunowie = new ArrayList<>();
        Opiekun o1 = new Opiekun("o1i","o1n","o1");
        Opiekun o2 = new Opiekun("o2i","o2n","o2");
        dodaj_opiekuna(o1);
        dodaj_opiekuna(o2);
        przyznaj_opiekuna(o2, "B1");
        this.zwierzeta = new ArrayList<>();
        Zwierze z1 = new Zwierze(BazaDanych.zwroc_podany_gatunek("001"),"L01","Antosia",10,"B1");
        zwierzeta.add(z1);
        BazaDanych.dodaj_zwierze_do_wybiegu(z1,"B1");
        zarzadca = new Zarzadca("x","y","z");

        this.magazyn = new Magazyn();
        magazyn.dodaj_do_magazynu(j1, 200);
        magazyn.dodaj_do_magazynu(j2, 300);
        magazyn.dodaj_do_magazynu(j3, 40);
    }

    public static BazaDanych pobierzInstancje(){
        if(instancja == null) {
            instancja = new BazaDanych();
        }
        return instancja;
    }


    //gatunki...............................................................
    public static void dodaj_nowy_gatunek(Gatunek gatunek){
        if (!czy_gatunek_o_tej_nazwie_na_liscie(gatunek.getNazwa(), gatunki_bd))
        gatunki_bd.add(gatunek);
    }

    public static boolean czy_gatunek_o_tej_nazwie_na_liscie(String szukanyGatunek, ArrayList<Gatunek> listaGatunkow){
        for (Gatunek g: listaGatunkow) {
            if (g.getNazwa().equals(szukanyGatunek) || g.getId().equals(szukanyGatunek))
                return true;
        }
        return false;
    }

    public static Gatunek zwroc_podany_gatunek(String identyfikacja){
        for (Gatunek g: gatunki_bd) {
            if (g.getNazwa().equals(identyfikacja) || g.getId().equals(identyfikacja))
                return g;
        }
        return null;
    }

    //lista dostepnego jedzenia.............................................
    public static void dodaj_nowy_rodaj_jedzenia(String nazwa){
        Jedzenie noweJedzenie = new Jedzenie(NoweId.przyznajId(),nazwa);
        dodaj_nowy_rodaj_jedzenia(noweJedzenie);
    }
    public static void dodaj_nowy_rodaj_jedzenia(Jedzenie jedzenie){
        if (!czy_istnieje_jedzenie_o_tej_nazwie_na_liscie(jedzenie.getNazwa(), lista_dostepnego_jedzenia))
            lista_dostepnego_jedzenia.add(jedzenie);

    }

    public static Jedzenie znajdz_jedzenie(String identyfikacja){

        for (Jedzenie j: lista_dostepnego_jedzenia) {
            if (j.getNazwa().equals(identyfikacja))
                return j;
            else if (j.getId().equals(identyfikacja))
                return j;
        }
        return null;
    }


    public static boolean czy_istnieje_jedzenie_o_tej_nazwie_na_liscie(String szukaneJedzenie, ArrayList<Jedzenie> listaJedzenia){
        if (listaJedzenia == null) return false;
        for (Jedzenie j: listaJedzenia) {
            if (j.getNazwa().equals(szukaneJedzenie))
                return true;
        }
        return false;
    }

    //wybiegi........................................................

    public static void dodaj_nowy_wybieg(Wybieg wybieg){
        if (!czy_istnieje_taki_wybieg_na_liscie(wybieg.getNumer(), wybiegi_bd))
            wybiegi_bd.add(wybieg);
    }

    public static boolean czy_istnieje_taki_wybieg_na_liscie(String nmWybiegu, ArrayList<Wybieg> listaWybiegow){
        for (Wybieg w: listaWybiegow) {
            if (w.getNumer().equals(nmWybiegu))
                return true;
        }
        return false;
    }

    public static Wybieg zwroc_dany_wybieg(String nmWybiegu){
        for (Wybieg w: wybiegi_bd) {
            if (w.getNumer().equals(nmWybiegu))
                return w;
        }
        return null;
    }

    public static void przyznaj_opiekuna(Opiekun opiekun, String nmWybiegu){
        if (czy_istnieje_taki_wybieg_na_liscie(nmWybiegu, wybiegi_bd))
            for (Wybieg w: wybiegi_bd) {
                if (w.getNumer().equals(nmWybiegu))
                    w.dodaj_opiekuna(opiekun);
            }

    }

    public static void dodaj_zwierze_do_wybiegu(Zwierze zwierze, String nmWybiegu){
        if (czy_istnieje_taki_wybieg_na_liscie(nmWybiegu, wybiegi_bd))
            for (Wybieg w: wybiegi_bd) {
                if (w.getNumer().equals(nmWybiegu))
                    w.dodaj_zwierze(zwierze);
            }
    }

    //opiekunowie............................................................................................
    public static void dodaj_opiekuna(Opiekun opiekun){
        boolean jest_taki_opiekun = false;
        for (Opiekun o: opiekunowie) {
            if ((o.imie.equals(opiekun.imie) && o.nazwisko.equals(opiekun.nazwisko))) {
                jest_taki_opiekun = true;
                break;
            }
        }
        if(!jest_taki_opiekun)
            opiekunowie.add(opiekun);
    }

    public static Opiekun zwroc_opiekuna_z_id(String id){
        for (Opiekun o: opiekunowie) {
            if ((o.id.equals(id)))
                return o;
        }
        return null;
    }

    //.....zuzycie jedzenia
    public Dieta zuzycie_jedzenia_na_dzien_dla_zoo(){
        Dieta laczna_dieta = new Dieta();

        laczna_dieta = laczna_dieta.zsumujDietyWszystkichZwierzat(zwierzeta);

        return laczna_dieta;
    }

    public Dieta zuzycie_jedzenia_dla_x_dni_na_zoo(Integer x){

        Dieta laczna_dieta = new Dieta();

        laczna_dieta = laczna_dieta.zsumujDietyWszystkichZwierzat(zwierzeta);
        laczna_dieta = laczna_dieta.zwroc_diete_zwiekszona_o_liczbe_dni(laczna_dieta, x);

        return laczna_dieta;
    }


    //......gettery i settery

    public static ArrayList<Gatunek> getGatunki_bd() {
        return gatunki_bd;
    }

    public static void setGatunki_bd(ArrayList<Gatunek> gatunki_bd) {
        BazaDanych.gatunki_bd = gatunki_bd;
    }

    public static ArrayList<Jedzenie> getLista_dostepnego_jedzenia() {
        return lista_dostepnego_jedzenia;
    }

    public static void setLista_dostepnego_jedzenia(ArrayList<Jedzenie> lista_dostepnego_jedzenia) {
        BazaDanych.lista_dostepnego_jedzenia = lista_dostepnego_jedzenia;
    }

    public static ArrayList<Wybieg> getWybiegi_bd() {
        return wybiegi_bd;
    }

    public static void setWybiegi_bd(ArrayList<Wybieg> wybiegi_bd) {
        BazaDanych.wybiegi_bd = wybiegi_bd;
    }

    public static ArrayList<Opiekun> getOpiekunowie() {
        return opiekunowie;
    }

    public static void setOpiekunowie(ArrayList<Opiekun> opiekunowie) {
        BazaDanych.opiekunowie = opiekunowie;
    }
}
