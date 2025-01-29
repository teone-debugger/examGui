package test.java.game.items.view;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.*;

public class OggettoTest {

    private int index;
    private int righe;
    private int colonne;

    private String descrizione;
    private int peso;

    @Test
    public void testDefaultConstructor() {
        OggettoTest oggetto = new OggettoTest();
        assertNotNull(oggetto);
        assertEquals(0, oggetto.getRighe());
        assertEquals(0, oggetto.getColonne());
        assertEquals("*", oggetto.getTipo());
    }

    @Test
    public void testParameterizedConstructor() {
        OggettoTest oggetto = new OggettoTest(5, 10);
        assertNotNull(oggetto);
        assertEquals(5, oggetto.getRighe());
        assertEquals(10, oggetto.getColonne());
        assertEquals("*", oggetto.getTipo());
        assertNotNull(oggetto.getDescrizione());
        assertTrue(oggetto.getPeso() > 0);
    }

    @Test
    public void testSetAndGetPeso() {
        OggettoTest oggetto = new OggettoTest();
        oggetto.setPeso(100);
        assertEquals(100, oggetto.getPeso());
    }

    @Test
    public void testSetAndGetDescrizione() {
        OggettoTest oggetto = new OggettoTest();
        oggetto.setDescrizione("Test Description");
        assertEquals("Test Description", oggetto.getDescrizione());
    }

    @Test
    public void testSerialization() throws IOException, ClassNotFoundException {
        OggettoTest oggetto = new OggettoTest();
        String filename = "oggettoTest.ser";

        // Serialize
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(oggetto);
        }

        // Deserialize
        OggettoTest deserializedOggetto;
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            deserializedOggetto = (OggettoTest) in.readObject();
        }

        assertNotNull(deserializedOggetto);
        assertEquals(oggetto.getRighe(), deserializedOggetto.getRighe());
        assertEquals(oggetto.getColonne(), deserializedOggetto.getColonne());
        assertEquals(oggetto.getTipo(), deserializedOggetto.getTipo());
        assertEquals(oggetto.getDescrizione(), deserializedOggetto.getDescrizione());
            assertEquals(oggetto.getPeso(), deserializedOggetto.getPeso());
        }
    public OggettoTest() {
        this.righe = 0;
        this.colonne = 0;
        this.descrizione = "";
        this.peso = 0;
    }

    public OggettoTest(int righe, int colonne) {
        this.righe = righe;
        this.colonne = colonne;
        this.descrizione = "";
        this.peso = 0;
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

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public int getRighe() {
        return 0;
    }

    public int getColonne() {
        return 0;
    }

    public String getTipo() {
        return "*";
    }

    public Integer getIndex(){
        return index;
    }
}
