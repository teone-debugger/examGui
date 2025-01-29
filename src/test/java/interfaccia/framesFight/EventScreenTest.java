package test.java.interfaccia.framesFight;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import javax.swing.*;
import java.awt.*;

public class EventScreenTest {

    private JLabel messageLabel;
    private JLabel messaggioBase;
    private JLabel hintLabel;

    public EventScreenTest(String message) {
        this.messageLabel = new JLabel(message);
        this.messageLabel.setForeground(Color.WHITE);
        this.messaggioBase = new JLabel("UNA VOCE LONTANA RIECHEGGIA NEL DUNGEON:");
        this.messaggioBase.setForeground(Color.RED);
        this.hintLabel = new JLabel("PREMI 'ENTER' PER USCIRE");
    }

    public JLabel getMessageLabel() {
        return messageLabel;
    }

    public JLabel getMessaggioBase() {
        return messaggioBase;
    }

    public JLabel getHintLabel() {
        return hintLabel;
    }

    @Test
    public void testEventScreenInitialization() {
        EventScreenTest eventScreen = new EventScreenTest("Test Message");
        assertNotNull(eventScreen);
        assertEquals("Test Message", eventScreen.getMessageLabel().getText());
    }

    @Test
    public void testMessageLabel() {
        EventScreenTest eventScreen = new EventScreenTest("Test Message");
        JLabel messageLabel = eventScreen.getMessageLabel();
        assertNotNull(messageLabel);
        assertEquals("Test Message", messageLabel.getText());
        assertEquals(Color.WHITE, messageLabel.getForeground());
    }

    @Test
    public void testMessaggioBaseLabel() {
        EventScreenTest eventScreen = new EventScreenTest("Test Message");
        JLabel messaggioBase = eventScreen.getMessaggioBase();
        assertNotNull(messaggioBase);
        assertEquals("UNA VOCE LONTANA RIECHEGGIA NEL DUNGEON:", messaggioBase.getText());
        assertEquals(Color.RED, messaggioBase.getForeground());
    }

    @Test
    public void testHintLabel() {
        EventScreenTest eventScreen = new EventScreenTest("Test Message");
        JLabel hintLabel = eventScreen.getHintLabel();
        assertNotNull(hintLabel);
        assertEquals("PREMI 'ENTER' PER USCIRE", hintLabel.getText());
    }
}