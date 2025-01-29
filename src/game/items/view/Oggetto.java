package game.items.view;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import game.Posizione;
import game.items.model.ModelOggetto;

public class Oggetto extends Posizione {

    private static final long serialVersionUID = 5L;


    private int peso;
    private String descrizione;
    private static int count = 1;

    private Integer index = 0;

    private ModelOggetto controllerOggetto = ModelOggetto.getInstance();

    public Oggetto() {
        super(0, 0, "*");
        this.descrizione = ModelOggetto.generateObjects(count);
        setPeso(controllerOggetto.getPeso(descrizione));

        this.index = count;
        count++;
    }

    public Oggetto(int righe, int colonne) {
        super(righe, colonne, "*");

        this.descrizione = ModelOggetto.generateObjects(count);
        setPeso(controllerOggetto.getPeso(descrizione));

        this.index = count;
        count++;

    }
    public Oggetto(int righe, int colonne, String descrizione) {
        super(righe, colonne, "%");

        /*this.index = count;
        count++;*/

        this.descrizione = descrizione;

    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public Integer getIndex(){
        return index;
    }

    public static void serialize(Oggetto posizione, String filePath) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(posizione);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Oggetto deserialize(String filePath) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            return (Oggetto) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
