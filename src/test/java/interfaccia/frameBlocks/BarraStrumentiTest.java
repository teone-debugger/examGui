package test.java.interfaccia.frameBlocks;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import javax.swing.*;
import java.awt.event.ActionEvent;

public class BarraStrumentiTest {

    private boolean clicked;
    private JTextField inputField = new JTextField();
    private JButton affermativo = new JButton("Affermativo");
    private JButton negativo = new JButton("Negativo");

    public boolean isClicked() {
        return clicked;
    }

    public void setClicked(boolean clicked) {
        this.clicked = clicked;
    }

    @Test
    public void testBarraStrumentiInitialization() {
        BarraStrumentiTest barraStrumenti = new BarraStrumentiTest();
        assertNotNull(barraStrumenti);
    }

    @Test
    public void testInputFieldInitialization() {
        BarraStrumentiTest barraStrumenti = new BarraStrumentiTest();
        assertNotNull(barraStrumenti.getInputField());
    }

    @Test
    public void testAffermativoButtonAction() {
        BarraStrumentiTest barraStrumenti = new BarraStrumentiTest();
        JButton affermativo = barraStrumenti.getAffermativo();
        ActionEvent event = new ActionEvent(affermativo, ActionEvent.ACTION_PERFORMED, "command");
        affermativo.getActionListeners()[0].actionPerformed(event);
        assertTrue(barraStrumenti.isClicked());
    }

    @Test
    public void testNegativoButtonAction() {
        BarraStrumentiTest barraStrumenti = new BarraStrumentiTest();
        JButton negativo = barraStrumenti.getNegativo();
        ActionEvent event = new ActionEvent(negativo, ActionEvent.ACTION_PERFORMED, "command");
        negativo.getActionListeners()[0].actionPerformed(event);
        assertTrue(barraStrumenti.isClicked());
    }

    
    public String getInputField() {
        return inputField.getText();
    }

    public JButton getAffermativo() {
        return affermativo;
    }

    public JButton getNegativo() {
        return negativo;
    }

}