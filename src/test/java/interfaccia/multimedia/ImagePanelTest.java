package test.java.interfaccia.multimedia;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import javax.swing.*;
import java.awt.*;

public class ImagePanelTest {

    private Image image;
    private float opacity;
    private int width;
    private int height;

    public ImagePanelTest(String imagePath, int width, int height) {
        this.image = new ImageIcon(imagePath).getImage();
        this.width = width;
        this.height = height;
        this.opacity = 1.0f;
    }

    public ImagePanelTest() {
        this.opacity = 1.0f;
    }

    public void setOpacity(float opacity) {
        this.opacity = opacity;
    }

    public void setScaledSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Test
    public void testConstructorWithImagePath() {
        ImagePanelTest panel = new ImagePanelTest("path/to/image.jpg", 100, 100);
        assertNotNull(panel);
        assertNotNull(panel.image);
    }

    @Test
    public void testSetOpacity() {
        ImagePanelTest panel = new ImagePanelTest();
        panel.setOpacity(0.5f);
        assertEquals(0.5f, panel.opacity);
    }

    @Test
    public void testSetScaledSize() {
        ImagePanelTest panel = new ImagePanelTest("path/to/image.jpg", 100, 100);
        panel.setScaledSize(200, 200);
        assertNotNull(panel.image);
    }
}