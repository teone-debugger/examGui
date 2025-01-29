package test.java.interfaccia;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import javax.swing.*;

import java.awt.GridBagConstraints;

public class FrameTest extends JFrame {
    GridBagConstraints gbc = new GridBagConstraints();

    public FrameTest(String title) {
        super(title);
    }

    @Test
    public void testFrameInitialization() {
        FrameTest frame = new FrameTest("Test Frame");
        assertNotNull(frame);
        assertEquals("Test Frame", frame.getTitle());
    }

    @Test
    public void testFrameSettings() {
        FrameTest frame = new FrameTest("Test Frame");
        frame.settings();
        assertNotNull(frame.getContentPane());
        assertFalse(frame.isResizable());
        assertEquals(JFrame.EXIT_ON_CLOSE, frame.getDefaultCloseOperation());
    }

    @Test
    public void testGridBagConstraints() {
        FrameTest frame = new FrameTest("Test Frame");
        assertNotNull(frame.gbc);
    }

    public void settings(){
        /**-- DIMENSIONI FRAME AUTOMATICHE --**/
        pack();

        /**--- METTO IN MEZZO E NON RIDIMENSIONABILE ---**/
        setLocationRelativeTo(null);
        setResizable(false);
        /**--- PROGRAMMA FINISCE SU FINESTRA CHIUSA ---**/
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /**--- FINESTRA VISIBILE ---**/
        setVisible(true);
    }
}