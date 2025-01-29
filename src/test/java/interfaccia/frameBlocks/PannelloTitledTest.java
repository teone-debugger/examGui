package test.java.interfaccia.frameBlocks;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import javax.swing.border.Border;
import interfaccia.frameBlocks.PannelloTitled;

public class PannelloTitledTest {

    @Test
    public void testPanelBorder() {
        PannelloTitled panel = new PannelloTitled("Test Title");
        Border border = panel.getBorder();
        assertNotNull(border);
        assertTrue(border instanceof javax.swing.border.CompoundBorder);
    }

    @Test
    public void testTitleInBorder() {
        PannelloTitled panel = new PannelloTitled("Test Title");
        Border border = panel.getBorder();
        assertNotNull(border);
        assertTrue(border instanceof javax.swing.border.CompoundBorder);

        javax.swing.border.CompoundBorder compoundBorder = (javax.swing.border.CompoundBorder) border;
        javax.swing.border.TitledBorder titledBorder = (javax.swing.border.TitledBorder) compoundBorder.getInsideBorder();
        assertEquals("Test Title", titledBorder.getTitle());
    }
}