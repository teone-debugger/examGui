package gioco;

public class Porta extends Posizione {

    private boolean bloccata;

    public Porta(int riga, int colonna, String tipo) {
        super(riga,colonna, tipo);

        bloccata = false;
    }

    public boolean isBloccata() {
        return this.bloccata;
    }
}
