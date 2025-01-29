package test.java.interfaccia.framesGame;

import game.character.player.Giocatore;
import interfaccia.framesGame.FrameGame;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import javax.swing.JFrame;

public class FrameGameTest {

    @Test
    public void testFrameGameInitialization() {
        Giocatore giocatore = new Giocatore();
        FrameGame frame = new FrameGame(giocatore);
        assertNotNull(frame);
        assertEquals("D&D", frame.getTitle());
    }

    @Test
    public void testFrameSettings() {
        Giocatore giocatore = new Giocatore();
        FrameGame frame = new FrameGame(giocatore);
        frame.settings();
        
        assertNotNull(frame.getContentPane());
        assertFalse(frame.isResizable());
        assertEquals(JFrame.EXIT_ON_CLOSE, frame.getDefaultCloseOperation());
    }
}