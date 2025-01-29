package test.java.game.items.view;

import game.enums.Classe;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.*;

public class ArmaTest {

    private int righe;
    private int colonne;
    private String descrizione;
    private int dado;
    private Classe classe;

    public ArmaTest() {
        this.descrizione = "";
    }

    public ArmaTest(int righe, int colonne) {
        this.righe = righe;
        this.colonne = colonne;
        this.descrizione = "";
        this.dado = 1; // Default value for dado
    }

    public int getRighe() {
        return righe;
    }

    public int getColonne() {
        return colonne;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public int getDado() {
        return dado;
    }

    public void setDado(int dado) {
        this.dado = dado;
    }

    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }

    @Test
    public void testDefaultConstructor() {
        ArmaTest arma = new ArmaTest();
        assertNotNull(arma);
        assertEquals(0, arma.getRighe());
        assertEquals(0, arma.getColonne());
        assertNotNull(arma.getDescrizione());
    }

    @Test
    public void testParameterizedConstructor() {
        ArmaTest arma = new ArmaTest(5, 10);
        assertNotNull(arma);
        assertEquals(5, arma.getRighe());
        assertEquals(10, arma.getColonne());
        assertNotNull(arma.getDescrizione());
        assertTrue(arma.getDado() > 0);
    }

    @Test
    public void testSetAndGetDado() {
        ArmaTest arma = new ArmaTest();
        arma.setDado(6);
        assertEquals(6, arma.getDado());
    }

    @Test
    public void testSetAndGetClasse() {
        ArmaTest arma = new ArmaTest();
        arma.setClasse(Classe.BARBARO);
        assertEquals(Classe.BARBARO, arma.getClasse());
    }

    @Test
    public void testSerialization() throws IOException, ClassNotFoundException {
        ArmaTest arma = new ArmaTest();
        String filename = "armaTest.ser";

        // Serialize
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(arma);
        }

        // Deserialize
        ArmaTest deserializedArma;
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            deserializedArma = (ArmaTest) in.readObject();
        }

        assertNotNull(deserializedArma);
        assertEquals(arma.getRighe(), deserializedArma.getRighe());
        assertEquals(arma.getColonne(), deserializedArma.getColonne());
        assertEquals(arma.getDescrizione(), deserializedArma.getDescrizione());
        assertEquals(arma.getDado(), deserializedArma.getDado());
        assertEquals(arma.getClasse(), deserializedArma.getClasse());
    }
}