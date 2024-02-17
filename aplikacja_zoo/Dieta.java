import java.util.*;

public class Dieta {
    HashMap<Jedzenie, Integer> produkt_gramy;

    public Dieta() {
        this.produkt_gramy = new HashMap<>();
    }

    public Dieta(Dieta dieta){
        this.produkt_gramy = new HashMap<>(dieta.produkt_gramy);
    }


    public HashMap<Jedzenie, Integer> getProdukt_gramy() {
        return produkt_gramy;
    }

    public void setProdukt_gramy(HashMap<Jedzenie, Integer> produkt_gramy) {
        this.produkt_gramy = produkt_gramy;
    }

    public void dodaj_pozycje(Jedzenie jedzenie, Integer gramy){
        this.produkt_gramy.put(jedzenie, gramy);
    }

    public void usun_pozycje(Jedzenie jedzenie){
        this.produkt_gramy.remove(jedzenie);
    }

    public void zmien_liczbe_gramow(Jedzenie jedzenie, Integer gramy){
        this.produkt_gramy.put(jedzenie, gramy);
    }

    public void dodaj_liczbe_gramow(Jedzenie jedzenie, Integer gramy){
        this.produkt_gramy.put(jedzenie, (this.produkt_gramy.get(jedzenie) + gramy));
    }

    public Dieta dostosuj_diete_do_wagi(Dieta dieta, Integer kg_zwierzecia){
        Dieta nowa_dieta= new Dieta(dieta);
        for (Map.Entry<Jedzenie, Integer> entry : dieta.produkt_gramy.entrySet()) {
            Jedzenie jedzenie = entry.getKey();
            Integer gramy = entry.getValue();
            nowa_dieta.zmien_liczbe_gramow(jedzenie, gramy * kg_zwierzecia);
        }
        return nowa_dieta;
    }

    // Metoda do zsumowania diety dla wszystkich zwierząt
    public Dieta zsumujDietyWszystkichZwierzat(ArrayList<Zwierze> zwierzeta) {
        Dieta sumaDiety = new Dieta();

        for (Zwierze zwierze : zwierzeta) {
            Dieta dietaZwierzecia = zwierze.getObecna_dieta();
            sumaDiety.zsumujDiety(dietaZwierzecia);
        }

        return sumaDiety;
    }

    // Metoda do zsumowania dwóch diet
    public void zsumujDiety(Dieta dieta) {
        for (Map.Entry<Jedzenie, Integer> entry : dieta.produkt_gramy.entrySet()) {
            Jedzenie jedzenie = entry.getKey();
            int ilosc = entry.getValue();

            if (this.produkt_gramy.containsKey(jedzenie)) {
                int aktualnaIlosc = this.produkt_gramy.get(jedzenie);
                this.produkt_gramy.put(jedzenie, aktualnaIlosc + ilosc);
            } else {
                this.produkt_gramy.put(jedzenie, ilosc);
            }
        }
    }

    public Dieta zwroc_diete_zwiekszona_o_liczbe_dni(Dieta dieta, Integer liczba_dni){
        Dieta dieta_na_x_dni = new Dieta(dieta);
        dieta_na_x_dni = dieta.dostosuj_diete_do_wagi(dieta_na_x_dni, liczba_dni);
        return dieta_na_x_dni;
    }

    public boolean czy_jest_to_jedzenie_w_diecie(Jedzenie jedzenie){
        if(produkt_gramy.get(jedzenie) == null) return false;
        return true;
    }

    public Integer ile_jest_tego_jedzenia_w_diecie(Jedzenie jedzenie){
        if(!czy_jest_to_jedzenie_w_diecie(jedzenie)) return 0;
        return produkt_gramy.get(jedzenie);
    }




    @Override
    public String toString() {

        StringBuilder result = new StringBuilder("Dieta{\n");

        for (Map.Entry<Jedzenie, Integer> entry : produkt_gramy.entrySet()) {
            result.append("{")
                    .append(entry.getKey().toString())  // Załóżmy, że Jedzenie posiada swoją metodę toString()
                    .append(" = ")
                    .append(entry.getValue())
                    .append("},\n");
        }

        result.append("}");
        return result.toString();

        /*for (Map.Entry<Jedzenie, Integer> entry : produkt_gramy.entrySet()) {
            Jedzenie jedzenie = entry.getKey();
            Integer gramy = entry.getValue();
            System.out.println("Jedzenie: " + jedzenie.getNazwa() + ", Gramy: " + gramy);
        }*/
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dieta dieta = (Dieta) o;
        return Objects.equals(produkt_gramy, dieta.produkt_gramy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(produkt_gramy);
    }
}
