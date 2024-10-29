package gioco;

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

        dialogo = "TU CHE OSI SVEGLIARMI DOPO IL MIO RIPOSO DI 1000 ANNI, HAI ANCHE IL CORAGGIO DI COMBATTERE CON ME?";
    }

    public static String getDialogo() {
        return dialogo;
    }

    public static int getDannoMorso() {
        return dannoMorso;
    }

    public static int getDannoFiammata() {
        return dannoFiammata;
    }

    public static int getDannoZampa() {
        return dannoZampa;
    }
}
