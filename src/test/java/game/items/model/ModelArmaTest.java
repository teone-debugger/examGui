package test.java.game.items.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ModelArmaTest {

    public static String generateDescrizione(int index) {
        String[] descrizioni = {"BASTONE", "SPADA CORTA", "MARTELLO DA GUERRA", "ASCIA BIPENNE", "RUNE", "BASTONE DEI FULMINI", "BASTONE DI FUOCO"};
        if (index >= 0 && index < descrizioni.length) {
            return descrizioni[index];
        } else {
            return null;
        }
    }

    private static ModelArmaTest instance;

    public static ModelArmaTest getInstance() {
        if (instance == null) {
            instance = new ModelArmaTest();
        }
        return instance;
    }

    @Test
    public void testSingletonInstance() {
        ModelArmaTest instance1 = ModelArmaTest.getInstance();
        assertNotNull(instance1);

        ModelArmaTest instance2 = ModelArmaTest.getInstance();
        assertSame(instance1, instance2);
    }

    @Test
    public void testGenerateDescrizione() {
        String descrizione = ModelArmaTest.generateDescrizione(1);
        assertNotNull(descrizione);
        assertTrue(descrizione.equals("BASTONE") || descrizione.equals("SPADA CORTA") ||
                   descrizione.equals("MARTELLO DA GUERRA") || descrizione.equals("ASCIA BIPENNE") ||
                   descrizione.equals("RUNE") || descrizione.equals("BASTONE DEI FULMINI") ||
                   descrizione.equals("BASTONE DI FUOCO"));
    }

    @Test
    public void testGenerateDescrizioneWithInvalidIndex() {
        String descrizione = ModelArmaTest.generateDescrizione(100);
        assertNull(descrizione);
    }
}