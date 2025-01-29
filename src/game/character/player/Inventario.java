package game.character.player;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import game.Dungeon;
import game.items.view.Oggetto;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Inventario implements Serializable{

@JsonProperty

    private static Inventario instance = null;

    private final int pesoMax; /**--- PESO MASSIMO CHE PUO' PORTARE ---**/
    private HashMap <Integer,Oggetto> inventario; /**--- BASATO SUL PESO ---**/

    //Metodo costruttore
    private Inventario() {
        this.pesoMax = 500;
        inventario = new HashMap<>();
    }

    //Metodo per prendere l'istanza dell'inventario
    public static Inventario getInstance() {
        if (instance == null) {
            instance = new Inventario();
        }
        return instance;
    }

    //Metodo per cercare un oggetto nell'inventario
    public boolean searchInInventory(String s) {
    
        for (Oggetto oggetto : inventario.values()) {
            if (oggetto.getDescrizione().equals(s)) {
                return true;
            }
        }

        return false;
    }

    //Metodo per rimuovere un oggetto dall'inventario
    public Class<?> removeFromInventory(String s) {
        for (Oggetto oggetto : inventario.values()) {
            if (oggetto.getDescrizione().equals(s)) {
                Oggetto g = inventario.remove(oggetto.getIndex());
                return g.getClass();
            }
        }

        return null;
    }

    //metodo per convertire l'inventario in stringa
    public String inventoryToString() {
    
        String str = "{";
        int count = 0;
        for(Oggetto oggetto : inventario.values()) {
            if (count == 0){
                if(oggetto.getClass().getSimpleName().equals("Arma")) {

                    str += "Weapon: " + oggetto.getIndex() + " - ";
                }else{

                    str += oggetto.getIndex() + " - ";
                }
            }else{
                if(oggetto.getClass().getSimpleName().equals("Arma")) {

                    str += "Weapon: " + oggetto.getIndex() + " - " ;
                }else {

                    str +=  oggetto.getIndex() + " - ";
                }
            }
            str +=oggetto.getDescrizione() + " ; ";
            count++;
            if(count == 2){
                count = 0;
                str += "\n";
            }
        }
        return str += "}";
    }

    //Metodo per mostrare a terminale l'inventario
    public void showInventory() {
    
        System.out.print("{");
        int count = 0;
        for(Oggetto oggetto : inventario.values()) {
            if (count == 0){
                count++;
                if(oggetto.getClass().getSimpleName().equals("Arma")) {

                    System.out.print("Weapon: " + oggetto.getIndex() + " - ");
                }else{

                    System.out.print(oggetto.getIndex() + " - ");
                }
            }else{
                if(oggetto.getClass().getSimpleName().equals("Arma")) {

                    System.out.print(" ; " + "Weapon: " + oggetto.getIndex() + " - ");
                }else {

                    System.out.print(" ; " + oggetto.getIndex() + " - ");
                }
            }
            System.out.print(oggetto.getDescrizione());
        }
        System.out.println("}");
    }

    //Metodo per aggiungere un oggetto all'inventario
    public void addToInventory(Oggetto oggetto) {
        inventario.put(oggetto.getIndex(), oggetto);
    }

    //Metodo per calcolare il peso attuale dell'inventario
    public int getPesoInventory() {
        int somma = 0;

        for(Oggetto o : inventario.values()) {
            somma += o.getPeso();
        }

        return somma;
    }

    //Metodo per prendere peso dell'inventario
    public int getPesoMax() {
        return instance.pesoMax;
    }

    //Metodo per prendere l'inventario
    public HashMap<Integer, Oggetto> getInventario() {
        return inventario;
    }

    public static void serialize(Inventario inventario, String filePath) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(inventario);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Inventario deserialize(String filePath) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            return (Inventario) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

}
