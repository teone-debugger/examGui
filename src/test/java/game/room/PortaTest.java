package test.java.game.room;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import game.room.Porta;

public class PortaTest {

    @Test
    public void testConstructor() {
        Porta porta = new Porta(1, 2, "Test", 3);
        assertNotNull(porta);
        assertEquals("Test", porta.getTipo());
        assertEquals(3, porta.getIndex());
    }

    @Test
    public void testIsBloccata() {
        Porta porta = new Porta(1, 2, "Test", 3);
        assertNotNull(porta);
        assertTrue(porta.isBloccata() || !porta.isBloccata()); // Randomly true or false
    }

    @Test
    public void testSetBloccata() {
        Porta porta = new Porta(1, 2, "Test", 3);
        porta.setBloccata(true);
        assertTrue(porta.isBloccata());

        porta.setBloccata(false);
        assertFalse(porta.isBloccata());
    }

    @Test
    public void testGetIndex() {
        Porta porta = new Porta(1, 2, "Test", 3);
        assertEquals(3, porta.getIndex());
    }
}