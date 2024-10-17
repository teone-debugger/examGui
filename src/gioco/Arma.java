package gioco;

import java.util.HashMap;

public class Arma extends Oggetto {

    private int dado;
    private Classe classe;

    private static int count = 1;



    public Arma(int righe, int colonne) {
        super(righe,colonne,Arma.generateDescrizione());

        count++;

        generateStats(getDescrizione());

    }

    private static String generateDescrizione() {
        HashMap<Integer, String> descrizione = new HashMap<>();
        descrizione.put(1,"BASTONE");

        descrizione.put(2,"SPADA CORTA");
        descrizione.put(3,"MARTELLO DA GUERRA");
        descrizione.put(4, "ASCIA BIPENNE");

        descrizione.put(5,"RUNE");
        descrizione.put(6,"BASTONE DEI FULMINI");
        descrizione.put(7,"BASTONE DI FUOCO");

        descrizione.put(8,"COLTELLI DA LANCIO");
        descrizione.put(9,"DAGHE");
        descrizione.put(10,"BALESTRA");

        return descrizione.get(count);
    }

    private void generateStats(String s){
        switch(s) {
            case "SPADA CORTA":
                this.dado = 6;
                setPeso(200);
                this.classe = Classe.BARBARO;

                break;
            case "MARTELLO DA GUERRA":
                this.dado = 8;
                setPeso(250);
                this.classe = Classe.BARBARO;

                break;
            case "ASCIA BIPENNE":
                this.dado = 10;
                setPeso(300);
                this.classe = Classe.BARBARO;

                break;
            case "RUNE":
                this.dado = 8;
                setPeso(200);
                this.classe = Classe.MAGO;

                break;
            case "BASTONE DEI FULMINI":
                this.dado = 10;
                setPeso(250);
                this.classe = Classe.MAGO;

                break;
            case "BASTONE DI FUOCO":
                this.dado = 12;
                setPeso(300);
                this.classe = Classe.MAGO;

                break;
            case "COLTELLI DA LANCIO":
                this.dado = 8;
                setPeso(200);
                this.classe = Classe.LADRO;

                break;
            case "DAGHE":
                this.dado = 10;
                setPeso(250);
                this.classe = Classe.LADRO;

                break;
            case "BALESTRA":
                this.dado = 12;
                setPeso(300);
                this.classe = Classe.LADRO;

                break;

            default:
                this.dado = 4;
                setPeso(100);
                this.classe = null;

                break;
        }


    }

    public int getDado() {
        return dado;
    }

    public Classe getClasse() {
        return classe;
    }
}
