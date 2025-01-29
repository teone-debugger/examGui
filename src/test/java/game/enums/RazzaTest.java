package test.java.game.enums;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.*;

public class RazzaTest {

    public enum Razza {
        ELFO, NANO , UMANO 
    }

    @Test
    public void testSerialization() {
        Razza razza = Razza.ELFO;
        String filePath = "razzaTest.ser";

        // Serialize
        assertDoesNotThrow(() -> RazzaTest.serialize(razza, filePath));

        // Deserialize
        Razza deserializedRazza = RazzaTest.deserialize(filePath);
        assertNotNull(deserializedRazza);
        assertEquals(razza, deserializedRazza);
    }

    @Test
    public void testDeserializationWithInvalidFile() {
        String invalidFilePath = "invalidFile";
        Razza deserializedRazza = RazzaTest.deserialize(invalidFilePath);
        assertNull(deserializedRazza);
    }

    public static void serialize(Razza posizione, String filePath) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(posizione);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Razza deserialize(String filePath) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            return (Razza) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
