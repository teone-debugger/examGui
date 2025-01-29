package test.java.util;

import org.junit.jupiter.api.Test;
import util.StringUtils;
import java.awt.Font;
import static org.junit.jupiter.api.Assertions.*;

public class StringUtilsTest {

    @Test
    public void testFormatCharacterName() {
        assertEquals("JOHN DOE", StringUtils.formatCharacterName("  John Doe  "));
        assertEquals("JANE DOE", StringUtils.formatCharacterName("Jane Doe"));
        assertEquals("", StringUtils.formatCharacterName("   "));
    }

    @Test
    public void testGetAlagardFont() {
        Font font = StringUtils.getAlagardFont(24f);
        assertNotNull(font);
        assertEquals(24f, font.getSize2D());
    }

    @Test
    public void testGetAlagardDefaultFont() {
        Font font = StringUtils.getAlagardDefaultFont();
        assertNotNull(font);
        assertEquals(24f, font.getSize2D());
    }

    @Test
    public void testGetBodyFont() {
        Font font = StringUtils.getBodyFont(17f);
        assertNotNull(font);
        assertEquals(17f, font.getSize2D());
    }

    @Test
    public void testGetBodyDefaultFont() {
        Font font = StringUtils.getBodyDefaultFont();
        assertNotNull(font);
        assertEquals(17f, font.getSize2D());
    }

    @Test
    public void testGetGameOverFont() {
        Font font = StringUtils.getGameOverFont(25f);
        assertNotNull(font);
        assertEquals(25f, font.getSize2D());
    }

    @Test
    public void testGetGameOverDefaultFont() {
        Font font = StringUtils.getGameOverDefaultFont();
        assertNotNull(font);
        assertEquals(25f, font.getSize2D());
    }
}