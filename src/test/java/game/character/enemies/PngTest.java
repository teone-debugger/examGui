package test.java.game.character.enemies;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import game.character.enemies.Png;

public class PngTest implements java.io.Serializable {

    @Test
    public void testOstileConstructor() {
        Png png = new Png(5, 10, 20, 50);
        assertNotNull(png);
        assertEquals(5, png.getRighe());
        assertEquals(10, png.getColonne());
        assertEquals("!", png.getTipo());
        assertNotNull(png.getNome());
        assertTrue(png.getPuntiVita() > 0);
        assertEquals(20, png.getPuntiArmatura());
        assertEquals(50, png.getMonete());
        assertTrue(png.isOstile());
        assertNotNull(png.getDialogo());
    }

    @Test
    public void testNonOstileConstructor() {
        Png png = new Png(5, 10);
        assertNotNull(png);
        assertEquals(5, png.getRighe());
        assertEquals(10, png.getColonne());
        assertEquals("?", png.getTipo());
        assertNotNull(png.getNome());
        assertEquals(1, png.getPuntiVita());
        assertEquals(0, png.getPuntiArmatura());
        assertEquals(100, png.getMonete());
        assertFalse(png.isOstile());
        assertNull(png.getDialogo());
    }

    @Test
    public void testSerialization() throws IOException, ClassNotFoundException {
        Png png = new Png(5, 10, 20, 50);
        String filename = "pngTest.ser";

        // Serialize
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(png);
        }

        // Deserialize
        Png deserializedPng;
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            deserializedPng = (Png) in.readObject();
        }

        assertNotNull(deserializedPng);
        assertEquals(png.getRighe(), deserializedPng.getRighe());
        assertEquals(png.getColonne(), deserializedPng.getColonne());
        assertEquals(png.getTipo(), deserializedPng.getTipo());
        assertEquals(png.getNome(), deserializedPng.getNome());
        assertEquals(png.getPuntiVita(), deserializedPng.getPuntiVita());
        assertEquals(png.getPuntiArmatura(), deserializedPng.getPuntiArmatura());
        assertEquals(png.getMonete(), deserializedPng.getMonete());
        assertEquals(png.isOstile(), deserializedPng.isOstile());
        assertEquals(png.getDialogo(), deserializedPng.getDialogo());
    }
}