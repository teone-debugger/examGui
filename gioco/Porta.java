package gioco;

public class Porta extends Posizione {

    private boolean bloccata;
    private int index;

    public Porta(int riga, int colonna, String tipo, int index) {
        super(riga,colonna, tipo);

        this.index = index;

        if((int)(Math.random() * 6) == 0) {
            bloccata = true;
        } else {
            bloccata = false;
        }
    }

    public int getIndex() {
        return index;
    }

    public boolean isBloccata() {
        return this.bloccata;
    }

    public void setBloccata(boolean bloccata) {
        this.bloccata = bloccata;
        //setTipo("^");
    }
}
