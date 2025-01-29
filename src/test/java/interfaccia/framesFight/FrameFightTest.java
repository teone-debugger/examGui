package test.java.interfaccia.framesFight;

import game.character.player.Giocatore;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import javax.swing.*;

public class FrameFightTest extends JFrame {

    private Giocatore giocatore;

    public FrameFightTest(Giocatore giocatore) {
        super("Fight");
        this.giocatore = giocatore;
    }

    @Test
    public void testFrameFightInitialization() {
        Giocatore giocatore = new Giocatore();
        FrameFightTest frame = new FrameFightTest(giocatore);
        assertNotNull(frame);
        assertEquals("Fight", frame.getTitle());
    }

    @Test
    public void testFrameSettings() {
        Giocatore giocatore = new Giocatore();
        FrameFightTest frame = new FrameFightTest(giocatore);
        frame.settings();
        
        assertNotNull(frame.getContentPane());
        assertFalse(frame.isResizable());
        assertEquals(JFrame.EXIT_ON_CLOSE, frame.getDefaultCloseOperation());
    }

    public void settings() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
    }
}