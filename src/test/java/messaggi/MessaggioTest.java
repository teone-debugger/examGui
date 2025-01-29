package test.java.messaggi;

import org.junit.jupiter.api.Test;

import messaggi.Messaggio;

import static org.junit.jupiter.api.Assertions.*;

public class MessaggioTest {

    private static Messaggio instance;
    private static String messaggio = "";

    @Test
    public void testGetInstance() {
        Messaggio instance1 = MessaggioTest.getIstance();
        assertNotNull(instance1);

        Messaggio instance2 = MessaggioTest.getIstance();
        assertSame(instance1, instance2);
    }

    @Test
    public void testGetAndSetMessaggio() {
        MessaggioTest.setMessaggio("Hello");
        assertEquals("Hello", MessaggioTest.getMessaggio());

        MessaggioTest.setMessaggio("World");
        assertEquals("World", MessaggioTest.getMessaggio());
    }

    @Test
    public void testAddMessaggio() {
        MessaggioTest.clearMesaggio();
        MessaggioTest.addMessaggio("Hello");
        assertEquals("Hello\n", MessaggioTest.getMessaggio());

        MessaggioTest.addMessaggio("World");
        assertEquals("Hello\nWorld\n", MessaggioTest.getMessaggio());
    }

    @Test
    public void testClearMessaggio() {
        MessaggioTest.setMessaggio("Hello");
        MessaggioTest.clearMesaggio();
        assertEquals("", MessaggioTest.getMessaggio());
    }

    public void Messaggio(){
    }

    public static Messaggio getIstance(){
        if(instance == null){
            instance = new Messaggio();
        }
        
        return instance;
    }

    public static String getMessaggio() {
        return messaggio;
    }
    public static void setMessaggio(String m) {
        messaggio = m;
    }
    public static void addMessaggio(String m) {
        messaggio += m + "\n";
    }
    public static void clearMesaggio(){
        messaggio = "";
    }
}
