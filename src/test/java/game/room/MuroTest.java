package test.java.game.room;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MuroTest {

    private int righe;
    private int colonne;
    private String tipo;

    public MuroTest(int righe, int colonne, String tipo) {
        this.righe = righe;
        this.colonne = colonne;
        this.tipo = tipo;
    }

    public int getRighe() {
        return righe;
    }

    public int getColonne() {
        return colonne;
    }

    public String getTipo() {
        return tipo;
    }

    @Test
    public void testConstructor() {
        MuroTest muro = new MuroTest(5, 10, "Wall");
        assertNotNull(muro);
        assertEquals(5, muro.getRighe());
        assertEquals(10, muro.getColonne());
        assertEquals("Wall", muro.getTipo());
    }
}