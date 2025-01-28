package game.character.enemies;

import game.character.Personaggio;


public class Png extends Personaggio {
    
    private String dialogo;
    private boolean ostile;
    private ControllerPng controllerPng = ControllerPng.getInstance();

    private static int count = 1;

    //Metodo costruttore png ostili
    public Png(int righe, int colonne, int puntiArmatura, int monete) {
        super(righe, colonne, "§", ControllerPng.generateNome(), ControllerPng.setPuntiVitaPng(), puntiArmatura, monete);

        setImmagine(controllerPng.setImmaginePng(getPuntiVita()));

        this.ostile = true;
        count++;

        this.dialogo = controllerPng.generateDialoghiOstili(count);
    }

    //Metodo costruttore png non ostili
    public Png(int righe, int colonne) {
        super(righe, colonne, "?", ControllerPng.generateNome(), 1, 0, 100);

        this.ostile = false;
        count++;

        this.dialogo = controllerPng.generateDialoghiNonOstili(count);
    }


    /**--- METODI GETTER ---**/

    //Metodo per prendere il dialogo
    public String getDialogo() {return dialogo;}

    //Metodo per dire se è ostile
    public boolean isOstile() {return ostile;}
}
