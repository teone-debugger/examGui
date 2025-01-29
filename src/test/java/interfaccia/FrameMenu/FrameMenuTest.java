package test.java.interfaccia.FrameMenu;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import javax.swing.*;

public class FrameMenuTest {

    @Test
    public void testFrameMenuInitialization() {
        JFrame frame = new JFrame();
        assertNotNull(frame);
    }
}