package gioco;

public class Drago extends Personaggio {

    private String dialogo;

    public Drago() {
        super(14,76, "£", "AURELION SOL", 45, 12, 1000);

        this.dialogo = "TU CHE OSI SVEGLIARMI DOPO IL MIO RIPOSO DI 1000 ANNI, HAI ANCHE IL CORAGGIO DI COMBATTERE CON ME?";
    }

    public String getDialogo() {
        return dialogo;
    }
}
