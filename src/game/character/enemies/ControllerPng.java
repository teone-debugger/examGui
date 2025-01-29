package game.character.enemies;

import java.util.HashMap;

public class ControllerPng {
    
    private static ControllerPng instance = null;

    //Metodo get instance
    public static ControllerPng getInstance(){
        if (instance == null){
            instance = new ControllerPng();
        }

        return instance;
    }

    //Metodo per generare il nome
    /**--- STATIC: PERCHE' RICHIAMATO NEL COSTRUTTORE DELLA SUPERCLASSE ---**/ /**--- PASSA IL METODO AL COSTRUTTORE DI PERSONAGGIO ---**/
    public static String generateNome(){
        String str = "";
        char c = (char) (Math.random() * (90 - 65 + 1) + 65);
        str += c;
        int x = (int) (Math.random() * 6 + 1);
        switch (x){
            case 1:
                str += "A";
                break;
            case 2:
                str += "E";
                break;
            case 3:
                str += "I";
                break;
            case 4:
                str += "O";
                break;
            case 5:
                str += "U";
                break;
            case 6:
                str += "Y";
                break;
        }
        c = (char) (Math.random() * (90 - 65 + 1) + 65);
        return str + c;

    }

    //Metodo per generare i dialoghi ostili
    public String generateDialoghiOstili(int count){
        HashMap<Integer, String> dialoghiOstili = new HashMap<>();;

        dialoghiOstili.put(1,"NON MI PIACCIONO QUELLI DELLA TUA RAZZA");
        dialoghiOstili.put(2,"QUELLI COME TE LI MANGIO A COLAZIONE");
        dialoghiOstili.put(3,"TI AMMAZZO");
        dialoghiOstili.put(4,"VEDI DI SPARIRE");
        dialoghiOstili.put(5,"OR ALE PRENDI");
        dialoghiOstili.put(6,"PROVARE A PARLARE CON ME E' STATA LA PEGGIORE DELLE TUE IDEE");
        dialoghiOstili.put(7, "TI FACCIO A FETTE");
        dialoghiOstili.put(8,"TI FARO' A PEZZI");
        dialoghiOstili.put(9,"VIVA GIGI FINIZZI");
        dialoghiOstili.put(10,"VUOI VEDERE COSA SUCCEDE?");
        
        return dialoghiOstili.get(count);
    }

    //Metodo per generare i dialoghi non ostili
    public String generateDialoghiNonOstili(int count){

        HashMap<Integer, String> dialoghiNonOstili = new HashMap<>();

        dialoghiNonOstili.put(11,"VIVA GIGI FINIZZI");
        dialoghiNonOstili.put(12,"BELLA GIORNATA OGGI");
        dialoghiNonOstili.put(13,"HAI VISTO IL MIO GATTO?");
        dialoghiNonOstili.put(14,"CIAO");
        dialoghiNonOstili.put(15,"HAI PREGIUDUIZI SUI MIEI SIMILI?");
        dialoghiNonOstili.put(16,"IL DRAGO E' NELL'ULTIMA STANZA");
        dialoghiNonOstili.put(17, "... ... ...");
        dialoghiNonOstili.put(18,"CORPO DI MILLE BALENE");
        dialoghiNonOstili.put(19,"OCCHIO AI BRUTTI CEFFI NEI DINTORNI");
        dialoghiNonOstili.put(20,"VAMOS A LA PLAYA");

        return dialoghiNonOstili.get(count);

    }

    //Metodo per settale punti vita
    public static int setPuntiVitaPng(){
        int selettore =  (int) (Math.random() *  100 + 1);

        if(selettore <= 30){
            return 8;

        }else if( selettore <= 45){
            return 12;

        }else if( selettore <= 55){
            return 15;

        }else if( selettore <= 68){
            return 18;

        }else if( selettore <= 75){
            return 21;

        }else if( selettore <= 88){
            return 24;

        }else if( selettore <= 98){
            return 27;

        }else{
            return 33;
        }
    }


}
