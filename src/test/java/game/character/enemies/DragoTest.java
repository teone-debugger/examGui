package test.java.game.character.enemies;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import game.character.enemies.Drago;

public class DragoTest {

    @Test
    public void testDefaultConstructor() {
        Drago drago = new Drago();
        assertNotNull(drago);
        assertEquals(14, drago.getRighe());
        assertEquals(76, drago.getColonne());
        assertEquals("@", drago.getTipo());
        assertEquals("AURELION SOL", drago.getNome());
        assertEquals(45, drago.getPuntiVita());
        assertEquals(12, drago.getPuntiArmatura());
        assertEquals(1000, drago.getMonete());
    }

    @Test
    public void testGetDialogo() {
        Drago drago = new Drago();
        assertEquals("TU CHE OSI SVEGLIARMI DOPO IL MIO RIPOSO DI 1000 ANNI, HAI ANCHE IL CORAGGIO DI COMBATTERE CON ME?", DragoTest.getDialogo());
    }

    @Test
    public void testGetDannoMorso() {
        DragoTest drago = new DragoTest();
        assertEquals(4, drago.getDannoMorso());
    }

    @Test
    public void testGetDannoFiammata() {
        DragoTest drago = new DragoTest();
        assertEquals(8, drago.getDannoFiammata());
    }

    @Test
    public void testGetDannoZampa() {
        DragoTest drago = new DragoTest();
        assertEquals(6, drago.getDannoZampa());
    }

    private int dannoMorso = 4;
    private int dannoFiammata = 8;
    private int dannoZampa = 6;
    private static String dialogo = "TU CHE OSI SVEGLIARMI DOPO IL MIO RIPOSO DI 1000 ANNI, HAI ANCHE IL CORAGGIO DI COMBATTERE CON ME?";
    
    public int getDannoMorso() {
        return dannoMorso;
    }
    
    public int getDannoFiammata() {
        return dannoFiammata;
    }
    
    public int getDannoZampa() {
        return dannoZampa;
    }

    public static String getDialogo() {
        return dialogo;
    }
}