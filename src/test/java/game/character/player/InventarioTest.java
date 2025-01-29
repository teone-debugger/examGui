package test.java.game.character.player;

import game.character.player.Inventario;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.*;

public class InventarioTest {


    @Test
    public void testDefaultConstructor() {
        Inventario inventario = new Inventario();
        assertNotNull(inventario);
        assertEquals(500, inventario.getPesoMax());
        assertNotNull(inventario.getInventario());
    }

    @Test
    public void testCopyConstructor() {
        Inventario original = new Inventario();
        Inventario copy = new Inventario(original);
        assertNotNull(copy);
        assertEquals(original.getPesoMax(), copy.getPesoMax());
        assertEquals(original.getInventario(), copy.getInventario());
    }

    @Test
    public void testSerialization() throws IOException, ClassNotFoundException {
        Inventario inventario = new Inventario();
        String filename = "inventarioTest.ser";

        // Serialize
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(inventario);
        }

        // Deserialize
        Inventario deserializedInventario;
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            deserializedInventario = (Inventario) in.readObject();
        }

        assertNotNull(deserializedInventario);
        assertEquals(inventario.getPesoMax(), deserializedInventario.getPesoMax());
        assertEquals(inventario.getInventario(), deserializedInventario.getInventario());
    }
}
