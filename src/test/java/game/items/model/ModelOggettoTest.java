package test.java.game.items.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ModelOggettoTest {

    private static ModelOggettoTest instance;

    private ModelOggettoTest() {
        // private constructor to prevent instantiation
    }

    public static ModelOggettoTest getInstance() {
        if (instance == null) {
            instance = new ModelOggettoTest();
        }
        return instance;
    }

    public static String generateObjects(int index) {
        switch (index) {
            case 1: return "POZIONE";
            case 2: return "CHIAVE";
            case 3: return "PIETRA FOCAIA";
            case 4: return "DIARIO VUOTO";
            case 5: return "LAPISLAZZULI";
            case 6: return "TAGLIACARTE";
            default: return null;
        }
    }

    @Test
    public void testSingletonInstance() {
        ModelOggettoTest instance1 = ModelOggettoTest.getInstance();
        assertNotNull(instance1);

        ModelOggettoTest instance2 = ModelOggettoTest.getInstance();
        assertSame(instance1, instance2);
    }

    @Test
    public void testGenerateObjects() {
        String objectDescription = ModelOggettoTest.generateObjects(1);
        assertNotNull(objectDescription);
        assertTrue(objectDescription.equals("POZIONE") || objectDescription.equals("CHIAVE") ||
                   objectDescription.equals("PIETRA FOCAIA") || objectDescription.equals("DIARIO VUOTO") ||
                   objectDescription.equals("LAPISLAZZULI") || objectDescription.equals("TAGLIACARTE"));
    }

    @Test
    public void testGenerateObjectsWithInvalidIndex() {
        String objectDescription = ModelOggettoTest.generateObjects(100);
        assertNull(objectDescription);
    }
}