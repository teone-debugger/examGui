package gioco;

import java.util.HashMap;

public class Png extends Personaggio {
    private String dialogo;
    private boolean ostile;

    private static HashMap<Integer, String> dialoghiOstili;
    private static HashMap<Integer, String> dialoghiNonOstili;

    private static int count = 0;

    /**--- COSTRUTTORE PNG OSTILI ---**/
    public Png(int righe, int colonne, int puntiVita, int puntiArmatura, int monete) {
        super(righe, colonne, "§", generateNome(), puntiVita, puntiArmatura, monete);

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
        dialoghiOstili.put(7, "GGGG");
        dialoghiOstili.put(8,"TODO");
        dialoghiOstili.put(9,"TODO");
        dialoghiOstili.put(10,"TODO");
    }
    private String selectDialoghiOstili() {
        return dialoghiOstili.get(count);
    }

    public static void generateDialoghiNonOstili(){
        dialoghiNonOstili = new HashMap<>();

        dialoghiNonOstili.put(11,"TODO");
        dialoghiNonOstili.put(12,"TODO");
        dialoghiNonOstili.put(13,"TODO");
        dialoghiNonOstili.put(14,"TODO");
        dialoghiNonOstili.put(15,"TODO");
        dialoghiNonOstili.put(16,"TODO");
        dialoghiNonOstili.put(17, "TODO");
        dialoghiNonOstili.put(18,"TODO");
        dialoghiNonOstili.put(19,"TODO");
        dialoghiNonOstili.put(20,"TODO");

    }
    private String selectDialoghiNonOstili() {
        return dialoghiNonOstili.get(count);

    }

    public String getDialogo() {return dialogo;}

    public boolean isOstile() {return ostile;}}
