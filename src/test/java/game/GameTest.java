package test.java.game;

import game.enums.Razza;
import game.enums.Classe;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GameTest {

    public static int selectPuntiVita(Razza razza) {
        switch (razza) {
            case ELFO:
                return 20;
            case UMANO:
                return 25;
            case NANO:
                return 30;
            default:
                throw new IllegalArgumentException("Razza non valida");
        }
    }

    @Test
    public void testSelectPuntiVita() {
        assertEquals(20, GameTest.selectPuntiVita(Razza.ELFO));
        assertEquals(25, GameTest.selectPuntiVita(Razza.UMANO));
        assertEquals(30, GameTest.selectPuntiVita(Razza.NANO));
    }

    public static int selectPuntiArmatura(Classe classe) {
        switch (classe) {
            case BARBARO:
                return 12;
            case LADRO:
                return 10;
            case MAGO:
                return 8;
            default:
                throw new IllegalArgumentException("Classe non valida");
        }
    }

    @Test
    public void testSelectPuntiArmatura() {
        assertEquals(12, GameTest.selectPuntiArmatura(Classe.BARBARO));
        assertEquals(10, GameTest.selectPuntiArmatura(Classe.LADRO));
        assertEquals(8, GameTest.selectPuntiArmatura(Classe.MAGO));
    }
}