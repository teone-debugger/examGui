package game.items.model;

import game.enums.Classe;

import java.util.HashMap;

public class ModelArma{

    private static ModelArma instance = null;

    //Metodo per ottenere l'istanza
    public static ModelArma getInstance() {
        if (instance == null) {
            instance = new ModelArma();
        }
        return instance;
    }

    //Metodo per generare le descrizioni delle armi
    public static String generateDescrizione(int index) {
        HashMap<Integer, String> descrizione = new HashMap<>();

        //Arma per tutte le classi 
        descrizione.put(1,"BASTONE");

        //armi da barbaro
        descrizione.put(2,"SPADA CORTA");
        descrizione.put(3,"MARTELLO DA GUERRA");
        descrizione.put(4, "ASCIA BIPENNE");

        //armi da mago
        descrizione.put(5,"RUNE");
        descrizione.put(6,"BASTONE DEI FULMINI");
        descrizione.put(7,"BASTONE DI FUOCO");

        //armi da ladro
        descrizione.put(8,"COLTELLI DA LANCIO");
        descrizione.put(9,"DAGHE");
        descrizione.put(10,"BALESTRA");

        return descrizione.get(index);

    }

    //Metodo per ottenere il danno dell'oggetto
    public int getDado(String s){
        switch(s) {
            case "SPADA CORTA":
                return 6;

            case "MARTELLO DA GUERRA":
                return 8;

            case "ASCIA BIPENNE":
                return 10;

            case "RUNE":
                return 8;

            case "BASTONE DEI FULMINI":
                return 10;

            case "BASTONE DI FUOCO":
                return 12;

            case "COLTELLI DA LANCIO":
                return 8;

            case "DAGHE":
                return 10;

            case "BALESTRA":
                return 12;


            default:
                return 4;

        }


    }

    //Metodo per ottenere il peso dell'oggetto
    public int getPeso(String s){
        switch(s) {
            case "SPADA CORTA":
                return 200;

            case "MARTELLO DA GUERRA":
                return 250;

            case "ASCIA BIPENNE":
                return 300;
 
            case "RUNE":
                return 200;

            case "BASTONE DEI FULMINI":
                return 250;

            case "BASTONE DI FUOCO":
                return 300;

            case "COLTELLI DA LANCIO":
                return 200;

            case "DAGHE":
                return 250;

            case "BALESTRA":
                return 300;

            default:
                return 100;

        }
    }

    //Metodo per ottenere la classe dell'oggetto
    public Classe getClasse(String s){
        switch(s) {
            case "SPADA CORTA":
                return Classe.BARBARO;

            case "MARTELLO DA GUERRA":

                return Classe.BARBARO;

            case "ASCIA BIPENNE":
                return Classe.BARBARO;

            case "RUNE":
                return Classe.MAGO;

            case "BASTONE DEI FULMINI":
                return Classe.MAGO;

            case "BASTONE DI FUOCO":
                return Classe.MAGO;

            case "COLTELLI DA LANCIO":
                return Classe.LADRO;

            case "DAGHE":
                return Classe.LADRO;

            case "BALESTRA":
                return Classe.LADRO;

            default:
                return null;

        }
    }


}

