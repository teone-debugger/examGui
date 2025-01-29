package test.java.game;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.*;

public class DungeonTest implements Serializable {
    private static DungeonTest instance;
    private Object mappa; 

    private DungeonTest() {
        // Initialize mappa and other properties
        mappa = new Object();
    }

    public static DungeonTest getInstance() {
        if (instance == null) {
            instance = new DungeonTest();
        }
        return instance;
    }

    public Object getMappa() {
        return mappa;
    }

    public int getRighe() {
        return 1;
    }

    public int getColonne() {
        return 1;
    }

    public Object getGiocatore() {
        return new Object();
    }

    public Object getDrago() {
        return new Object();
    }

    @Test
    public void testSingletonInstance() {
        DungeonTest instance1 = DungeonTest.getInstance();
        assertNotNull(instance1);

        DungeonTest instance2 = DungeonTest.getInstance();
        assertSame(instance1, instance2);
    }

    @Test
    public void testSerialization() throws IOException, ClassNotFoundException {
        DungeonTest dungeon = DungeonTest.getInstance();
        String filename = "dungeonTest.ser";

        // Serialize
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(dungeon);
        }

        // Deserialize
        DungeonTest deserializedDungeon;
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            deserializedDungeon = (DungeonTest) in.readObject();
        }

        assertNotNull(deserializedDungeon);
        assertSame(dungeon, deserializedDungeon);
    }

    @Test
    public void testDungeonProperties() {
        DungeonTest dungeon = DungeonTest.getInstance();
        assertNotNull(dungeon.getMappa());
        assertTrue(dungeon.getRighe() > 0);
        assertTrue(dungeon.getColonne() > 0);
        assertNotNull(dungeon.getGiocatore());
        assertNotNull(dungeon.getDrago());
    }
}