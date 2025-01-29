package game.character.enemies;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import game.character.Personaggio;


public class Png extends Personaggio {
    
    private String dialogo;
    private boolean ostile;
    private ControllerPng controllerPng = ControllerPng.getInstance();

    private static int count = 1;

    //Metodo costruttore png ostili
    public Png(int righe, int colonne, int puntiArmatura, int monete) {
        super(righe, colonne, "!", ControllerPng.generateNome(), ControllerPng.setPuntiVitaPng(), puntiArmatura, monete);

        //setImmagine(controllerPng.setImmaginePng(getPuntiVita()));

        this.ostile = true;
        
        this.dialogo = controllerPng.generateDialoghiOstili(count);
        count++;
    }

    //Metodo costruttore png non ostili
    public Png(int righe, int colonne) {
        super(righe, colonne, "?", ControllerPng.generateNome(), 1, 0, 100);

        this.ostile = false;
        
        this.dialogo = controllerPng.generateDialoghiNonOstili(count);
        count++;
    }

    public static void serialize(Png posizione, String filePath) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(posizione);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Png deserialize(String filePath) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            return (Png) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }


    /**--- METODI GETTER ---**/

    //Metodo per prendere il dialogo
    public String getDialogo() {return dialogo;}

    //Metodo per dire se è ostile
    public boolean isOstile() {return ostile;}
}
