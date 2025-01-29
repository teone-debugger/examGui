package game.items.model;

import java.util.HashMap;

public class ModelOggetto {
    
    private static ModelOggetto instance = null;
    

    //Metodo per ottenere l'istanza
    public static ModelOggetto getInstance(){
        if(instance == null){
            instance = new ModelOggetto();
        }
        return instance;
    }

    //Metodo per generare gli oggetti (30 oggetti)
    public static String generateObjects(int index){

        HashMap<Integer, String> descrizione = new HashMap<>();

        int indiceMappa = 1;
        //Inserisco 6 pozioni e 6 chiavi
        for (int i = 0; i < 9; i++) {
            descrizione.put(indiceMappa, "POZIONE");
            indiceMappa++;
            descrizione.put(indiceMappa, "CHIAVE");
            indiceMappa++;
        }

        //Inserisco 3 pietre focaie, 3 diari vuoti, 3 lapislazzuli e 3 tagliacarte
        for (int i = 0; i < 3; i++) {
            descrizione.put(indiceMappa, "PIETRA FOCAIA");
            indiceMappa++;
            descrizione.put(indiceMappa, "DIARIO VUOTO");
            indiceMappa++;
            descrizione.put(indiceMappa, "LAPISLAZZULI");
            indiceMappa++;
            descrizione.put(indiceMappa, "TAGLIACARTE");
            indiceMappa++;
        }

        return descrizione.get(index); 

    }

    //Metodo per ottenere il peso dell'oggetto
    public int getPeso(String s){
        if(s == null){return 0;}
        switch(s) {
            case "POZIONE":
                return 10;

            case "CHIAVE":
                return 7;

            case "PIETRA FOCAIA":
                return 5;

            case "DIARIO VUOTO":
                return 20;

            case "LAPISLAZZULI":
                return 7;

            case "TAGLIACARTE":
                return 5;

            default:
                return 0;
        }
    }
}
