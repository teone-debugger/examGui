package game.character.enemies;

import game.character.Personaggio;
import interfaccia.multimedia.ImagePanel;

public class Drago extends Personaggio {

    private static String dialogo;

    private static int dannoMorso;
    private static int dannoFiammata;
    private static int dannoZampa;

    public Drago() {
        super(14,76, "£", "AURELION SOL", 45, 12, 1000);

        dannoMorso = 4;
        dannoFiammata = 8;
        dannoZampa = 6;
        setImmagine(new ImagePanel("resources/images/nemici/image (20).png", 300, 300));
        dialogo = "TU CHE OSI SVEGLIARMI DOPO IL MIO RIPOSO DI 1000 ANNI, HAI ANCHE IL CORAGGIO DI COMBATTERE CON ME?";
    }

    public static String getDialogo() {
        return dialogo;
    }

    public int getDannoMorso() {
        return dannoMorso;
    }

    public int getDannoFiammata() {
        return dannoFiammata;
    }

    public int getDannoZampa() {
        return dannoZampa;
    }

    public boolean isVivo() {
        return getPuntiVita() > 0;
    }
}
