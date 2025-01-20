package gioco;

import java.awt.Image;
import java.util.HashMap;

import interfaccia.ImagePanel;

public class Png extends Personaggio {
    private String dialogo;
    private boolean ostile;

    private static HashMap<Integer, String> dialoghiOstili;
    private static HashMap<Integer, String> dialoghiNonOstili;

    private static int count = 0;

    /**--- COSTRUTTORE PNG OSTILI ---**/
    public Png(int righe, int colonne, int puntiVita, int puntiArmatura, int monete, ImagePanel immagine) {
        super(righe, colonne, "§", generateNome(), puntiVita, puntiArmatura, monete);

        setImmagine(immagine);

        this.ostile = true;

        count++;

        this.dialogo = selectDialoghiOstili();
    }


    /**--- COSTRUTTORE NON PNG OSTILI ---**/
    public Png(int righe, int colonne) {
        super(righe, colonne, "?", generateNome(), 1, 0, 100);

        this.ostile = false;

        count++;

        this.dialogo = selectDialoghiNonOstili();
    }


    /**--- STATIC: PERCHE' RICHIAMATO NEL COSTRUTTORE DELLA SUPERCLASSE ---**/ /**--- PASSA IL METODO AL COSTRUTTORE DI PERSONAGGIO ---**/
    private static String generateNome(){
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

    public static void generateDialoghiOstili(){
        dialoghiOstili = new HashMap<>();

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
    }
    private String selectDialoghiOstili() {
        return dialoghiOstili.get(count);
    }

    public static void generateDialoghiNonOstili(){
        dialoghiNonOstili = new HashMap<>();

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

    }
    private String selectDialoghiNonOstili() {
        return dialoghiNonOstili.get(count);

    }

    public String getDialogo() {return dialogo;}

    public boolean isOstile() {return ostile;}}
