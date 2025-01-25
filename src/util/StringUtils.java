package util;

import java.awt.*;


public class StringUtils {

    // Metodo per formattare una stringa con il nome del personaggio
    public static String formatCharacterName(String name) {
        return name.trim().toUpperCase();
    }

    public static Font getAlagardFont(){
        Font alagardFont;
        try {
            alagardFont = Font.createFont(Font.TRUETYPE_FONT, new java.io.File("resources/font/alagard.ttf")).deriveFont(24f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(alagardFont); // Registra il font

        }
        catch (Exception e) {
            e.printStackTrace();
            alagardFont = new Font("Serif", Font.PLAIN, 24); // Font di fallback
        }

        return alagardFont;
    }

}