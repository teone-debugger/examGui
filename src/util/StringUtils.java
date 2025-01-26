package util;

import java.awt.*;


public class StringUtils {

    // Metodo per formattare una stringa con il nome del personaggio
    public static String formatCharacterName(String name) {
        return name.trim().toUpperCase();
    }

    public static Font getAlagardFont(float size){
        Font alagardFont;
        try {
            alagardFont = Font.createFont(Font.TRUETYPE_FONT, new java.io.File("resources/font/alagard.ttf")).deriveFont(size);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(alagardFont); // Registra il font

        }
        catch (Exception e) {
            e.printStackTrace();
            alagardFont = new Font("Serif", Font.PLAIN, 24); // Font di fallback
        }

        return alagardFont;
    }

    public static Font getAlagardDefaultFont(){
        return getAlagardFont(24f);
    }

    public static Font getBodyFont(float size){
        //Creazione font base
        Font bodyFont;
        try {
            bodyFont = Font.createFont(Font.TRUETYPE_FONT, new java.io.File("resources/font/Perfect DOS VGA 437.ttf")).deriveFont(size);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(bodyFont);
        }
        catch (Exception e) {
            e.printStackTrace();
            bodyFont = new Font("Serif", Font.PLAIN, 25);
        }

        return bodyFont;
    }

    public static Font getBodyDefaultFont(){
        return getBodyFont(17f);
    }

    public static Font getGameOverFont(float size){
        //Creazione font game over/win
        Font gameOverFont;
        try {
            gameOverFont = Font.createFont(Font.TRUETYPE_FONT, new java.io.File("resources/font/alagard.ttf")).deriveFont(size);
            GraphicsEnvironment ye = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ye.registerFont(gameOverFont);
        }
        catch (Exception e) {
            e.printStackTrace();
            gameOverFont = new Font("Serif", Font.PLAIN, 15);
        }

        return gameOverFont;
    } 

    public static Font getGameOverDefaultFont(){
        return getGameOverFont(25f);
    }

}