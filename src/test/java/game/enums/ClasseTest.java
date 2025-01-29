package test.java.game.enums;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.*;

import game.enums.Classe;

public class ClasseTest {

    @Test
    public void testSerialization() {
        Classe classe = Classe.BARBARO;
        String filePath = "classeTest.ser";

        // Serialize
        assertDoesNotThrow(() -> ClasseTest.serialize(classe, filePath));

        // Deserialize
        Classe deserializedClasse = ClasseTest.deserialize(filePath);
        assertNotNull(deserializedClasse);
        assertEquals(classe, deserializedClasse);
    }

    @Test
    public void testDeserializationWithInvalidFile() {
        String invalidFilePath = "invalidFile.ser";
        Classe deserializedClasse = ClasseTest.deserialize(invalidFilePath);
        assertNull(deserializedClasse);
    }

    public static void serialize(Classe posizione, String filePath) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(posizione);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Classe deserialize(String filePath) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            return (Classe) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}