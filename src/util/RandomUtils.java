package util;


import java.util.Random;

public class RandomUtils {

    // Metodo per generare un numero casuale tra min e max (inclusi) 
    public static int getRandomNumber(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }
    
    // Metodo per lanciare un dado a 4 facce
    public static int rollD4() {
        return getRandomNumber(1, 4);
    }

    // Metodo per lanciare un dado a 6 facce
    public static int rollD6() {
        return getRandomNumber(1, 6);
    }

    // Metodo per lanciare un dado a 20 facce
    public static int rollD20() {
        return getRandomNumber(1, 20);
    }

    // Metodo per formattare una stringa con il nome del personaggio
    public static String formatCharacterName(String name) {
        return name.trim().toUpperCase();
    }

    // Metodo per calcolare il danno inflitto
    public static int calculateDamage(int baseDamage, int modifier) {
        return baseDamage + modifier;
    }
}