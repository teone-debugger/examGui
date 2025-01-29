package test.java.game.character.player;

import game.enums.Classe;
import game.enums.Razza;
import game.items.view.Arma;
import game.character.Personaggio;
import game.character.player.Giocatore;
import game.character.player.Inventario;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class GiocatoreTest {

    private static Inventario inventario;

    private static Classe classe=Classe.BARBARO;
    private static Razza razza=Razza.ELFO;
    private int puntiEsperienza;
    private static Arma arma;
    private Personaggio nemico;

    @Test
    public void testDefaultConstructor() {
        Giocatore giocatore = new Giocatore();
        assertNotNull(giocatore);
        assertNull(null);
    }

    @Test
    public void testSetAndGetPuntiEsperienza() {
        Giocatore giocatore = new Giocatore();
    }

    @Test
    public void testSetAndGetClasse() {
        GiocatoreTest giocatore = new GiocatoreTest();
        giocatore.setClasse(Classe.BARBARO);
        assertEquals(Classe.BARBARO, GiocatoreTest.getClasse());
    }

    @Test
    public void testSetAndGetRazza() {
        GiocatoreTest giocatore = new GiocatoreTest();
        giocatore.setRazza(Razza.ELFO);
        assertEquals(Razza.ELFO, GiocatoreTest.getRazza());
    }

    @Test
    public void testSerialization() throws IOException, ClassNotFoundException {
        GiocatoreTest giocatore = new GiocatoreTest();
        String filename = "giocatoreTest.ser";

        // Serialize
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(giocatore);
        }

        // Deserialize
        Giocatore deserializedGiocatore;
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            deserializedGiocatore = (Giocatore) in.readObject();
        }

        assertNotNull(deserializedGiocatore);
        assertEquals(Giocatore.getClasse(), Giocatore.getClasse());
        assertEquals(Giocatore.getRazza(), deserializedGiocatore.getRazza());
    }

        //Metodo per prendere i punti esperienza del giocatore
    public int getPuntiEsperienza() {return puntiEsperienza;}
    
    //Metodo per prendere la classe del giocatore
    public static Classe getClasse() {return classe;}

    //Metodo per prendere la razza del giocatore
    public static Razza getRazza() {return razza;}

    //Metodo per prendere l'arma con cui attacca il giocatore
    public static Arma getArma(){return arma;} /**--- STATIC: USATO IN UNA SOPRACLASSE --**/

    //Metodo per prendere l'inventario del giocatore
    public static Inventario getInventory() {return inventario;}

    //Metodo per prendere il nemmico del personaggio
    public Personaggio getNemico() {return nemico;}

    public void setRazza(Razza razza){
        razza = Razza.ELFO;
    }

    public void setClasse(Classe classe){
        classe = Classe.BARBARO;
    }

    public void setPuntiEsperienza(int puntiEsperienza){
        puntiEsperienza = 100;
    }
}