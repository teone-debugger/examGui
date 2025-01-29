package test.java.game;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.*;

public class PosizioneTest {
    int righe;
    int colonne;
    String tipo;
    boolean libera;

    public PosizioneTest() {
        this.righe = 0;
        this.colonne = 0;
        this.tipo = " ";
        this.libera = true;
    }

    public PosizioneTest(int righe, int colonne, String tipo) {
        this.righe = righe;
        this.colonne = colonne;
        this.tipo = tipo;
        this.libera = true;
    }

    @Test
    public void testDefaultConstructor() {
        PosizioneTest posizione = new PosizioneTest();
        assertNotNull(posizione);
        assertEquals(0, posizione.righe);
        assertEquals(0, posizione.colonne);
        assertEquals(" ", posizione.tipo);
        assertTrue(posizione.libera);
    }

    @Test
    public void testParameterizedConstructor() {
        PosizioneTest posizione = new PosizioneTest(5, 10, "Test");
        assertNotNull(posizione);
        assertEquals(5, posizione.righe);
        assertEquals(10, posizione.colonne);
        assertEquals("Test", posizione.tipo);
        assertTrue(posizione.libera);
    }

    @Test
    public void testSerialization() throws IOException, ClassNotFoundException {
        PosizioneTest posizione = new PosizioneTest(5, 10, "Test");
        String filename = "posizioneTest.ser";

        // Serialize
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(posizione);
        }

        // Deserialize
        PosizioneTest deserializedPosizione;
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            deserializedPosizione = (PosizioneTest) in.readObject();
        }

        assertNotNull(deserializedPosizione);
        assertEquals(posizione.righe, deserializedPosizione.righe);
        assertEquals(posizione.colonne, deserializedPosizione.colonne);
        assertEquals(posizione.tipo, deserializedPosizione.tipo);
        assertEquals(posizione.libera, deserializedPosizione.libera);
    }
}