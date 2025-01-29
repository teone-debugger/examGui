package game;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)

public class Posizione implements Serializable{

@JsonProperty

    private static final long serialVersionUID = 2L;

    int righe;
    int colonne;
    String tipo;
    boolean libera;

    //Metodo costruttore di default
    public Posizione(int righe, int colonne, String tipo) {
        this.righe = righe;
        this.colonne = colonne;
        this.tipo = tipo;
        libera = false;
        if(tipo==" ") {
            libera = true;
        }
    }

    //Metodo costruttore 
    public Posizione(int righe, int colonne) {
        this.righe = righe;
        this.colonne = colonne;
        this.tipo = " ";
        this.libera = true;
    }

    public static void serialize(Posizione posizione, String filePath) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(posizione);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Posizione deserialize(String filePath) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            return (Posizione) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**--- METODI GETTER ---**/

    //Metodo per ottenere il numero di righe
    public int getRighe(){return righe;}

    //Metodo per ottenere il numero di colonne
    public int getColonne(){return colonne;}

    //Metodo per ottenere il tipo
    public String getTipo(){return tipo;} /***--- toString() ---***/

    //Metodo per ottenere la posizione
    public boolean isLibera(){return libera;}

    /**--- METODI SETTER ---**/

    //Metodo per settare il numero di righe
    public void setRighe(int righe) {this.righe = righe;}

    //Metodo per settare il numero di colonne
    public void setColonne(int colonne) {this.colonne = colonne;}

    //Metodo per settare il tipo
    public void setTipo(String tipo) {this.tipo = tipo;}
}
