package game.items.view;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import game.enums.Classe;
import game.items.model.ModelArma;

public class Arma extends Oggetto {

    private static final long serialVersionUID = 4L;


    private int dado;
    private Classe classe;

    private static int count = 1;
    
    private ModelArma controllerArma = ModelArma.getInstance();


    public Arma() {
        super(0, 0);
    }

    //Metodo costruttore
    public Arma(int righe, int colonne) {

        super(righe,colonne,ModelArma.generateDescrizione(count));
        
        count++;

        dado = controllerArma.getDado(getDescrizione());
        classe = controllerArma.getClasse(getDescrizione());
        setPeso(controllerArma.getPeso(getDescrizione()));

    }

    /**--- METODI GETTER ---**/

    //Metodo per ottenere il dado dell'arma
    public int getDado() {
        return dado;
    }


    //Metodo per ottenere la classe dell'arma
    public Classe getClasse() {
        return classe;
    }

    public static void serialize(Arma posizione, String filePath) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(posizione);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Arma deserialize(String filePath) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            return (Arma) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }


}
