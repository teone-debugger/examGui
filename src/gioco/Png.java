package gioco;

import java.util.HashMap;

public class Png extends Personaggio {
    private String dialogo;
    private boolean ostile;

    private static int count = 1;

    /**--- COSTRUTTORE PNG OSTILI ---**/
    public Png(int righe, int colonne, int puntiVita, int puntiArmatura, int monete) {
        super(righe, colonne, "§", generateNome(), puntiVita, puntiArmatura, monete);

        this.ostile = true;

        count++;

        this.dialogo = generateDialoghiOstili();
    }

    /**--- COSTRUTTORE NON PNG OSTILI ---**/
    public Png(int righe, int colonne) {
        super(righe, colonne, "?", generateNome(), 1, 0, 100);

        this.ostile = false;

        count++;

        this.dialogo = generateDialoghiNonOstili();
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

    private String generateDialoghiOstili(){
        HashMap<Integer, String> dialoghi = new HashMap<>();
        dialoghi.put(1,"non mi piacciono quelli della tua razza");
        dialoghi.put(2,"quelli come te li mangio a colazione");
        dialoghi.put(3,"ti ammazzo");
        dialoghi.put(4,"vedi di sparire");
        dialoghi.put(5,"ora le prendi");
        dialoghi.put(6,"provare a parlare con me è stata la peggiore delle tue idee");
        dialoghi.put(7, "TODO");
        dialoghi.put(8,"TODO");
        dialoghi.put(9,"TODO");
        dialoghi.put(10,"TODO");

        return dialoghi.get(count);
    }

    private String generateDialoghiNonOstili(){
        HashMap<Integer, String> dialoghi = new HashMap<>();
        dialoghi.put(11,"TODO");
        dialoghi.put(12,"TODO");
        dialoghi.put(13,"TODO");
        dialoghi.put(14,"TODO");
        dialoghi.put(15,"TODO");
        dialoghi.put(16,"TODO");
        dialoghi.put(17, "TODO");
        dialoghi.put(18,"TODO");
        dialoghi.put(19,"TODO");
        dialoghi.put(20,"TODO");

        return dialoghi.get(count);
    }

    public String getDialogo() {return dialogo;}

    public boolean isOstile() {return ostile;}}
