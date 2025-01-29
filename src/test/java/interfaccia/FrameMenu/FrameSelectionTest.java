package test.java.interfaccia.FrameMenu;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import javax.swing.*;

public class FrameSelectionTest extends JFrame {

    @Test
    public void testFrameSelectionInitialization() {
        FrameSelectionTest frame = new FrameSelectionTest();
        assertNotNull(frame);
    }
}