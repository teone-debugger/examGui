package test.java.interfaccia.frameBlocks;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import interfaccia.frameBlocks.PannelloText;
import javax.swing.*;
import java.awt.*;

public class PannelloTextTest {
    private JTextArea jtextArea = new JTextArea();

    @Test
    public void testPannelloTextInitialization() {
        PannelloText panel = new PannelloText();
        assertNotNull(panel);
    }

    @Test
    public void testDimension() {
        PannelloText panel = new PannelloText();
        Dimension dimension = new Dimension(200, 100);
        panel.dimension(dimension);
        assertEquals(dimension, panel.getPreferredSize());
    }

    @Test
    public void testSetText() {
        JTextArea panel = new JTextArea();
        String text = "Hello, World!";
        panel.setText(text);
        assertEquals(text, panel.getText());
    }

    @Test
    public void testClearText() {
        JTextArea panel = new JTextArea();
        panel.setText("Hello, World!");
        clearText();
        assertEquals("Hello, World!", panel.getText());
    }

    public void clearText(){
        jtextArea.setText("");

        addTextArea();
    }

    public JTextArea getJtextArea() {
        return jtextArea;
    }

    
    public void setText(String testo){
        jtextArea.setText(testo);

        addTextArea();
    }

    private void addTextArea(){

        jtextArea.setEditable(false);
        jtextArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 20));
        jtextArea.setPreferredSize(new Dimension(200, 100));

        // Assuming PannelloText has an add method
        PannelloText panel = new PannelloText();
        panel.add(jtextArea);
    }
}