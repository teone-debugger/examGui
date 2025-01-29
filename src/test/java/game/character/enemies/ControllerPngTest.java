package test.java.game.character.enemies;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ControllerPngTest {

    private static ControllerPngTest instance;

    public static ControllerPngTest getInstance() {
        if (instance == null) {
            instance = new ControllerPngTest();
        }
        return instance;
    }

    @Test
    public void testSingletonInstance() {
        ControllerPngTest instance1 = ControllerPngTest.getInstance();
        assertNotNull(instance1);

        ControllerPngTest instance2 = ControllerPngTest.getInstance();
        assertSame(instance1, instance2);
    }

    public static String generateNome() {
        // Example implementation
        return "AEIOU";
    }

    @Test
    public void testGenerateNome() {
        String nome = ControllerPngTest.generateNome();
        assertNotNull(nome);
        assertTrue(nome.length() > 0);
        assertFalse(nome.matches("[A-Z][AEIOU]"));
    }
}