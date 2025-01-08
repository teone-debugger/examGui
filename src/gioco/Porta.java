package gioco;

public class Porta extends Posizione {

    private boolean bloccata;

    public Porta(int riga, int colonna, String tipo) {
        super(riga,colonna, tipo);

        if((int)(Math.random() * 30) == 0) {
            bloccata = true;
        } else {
            bloccata = false;
        }
    }

    public Porta(int riga, int colonna, String tipo , boolean bloccata) {
        super(riga,colonna, tipo);

            this.bloccata = bloccata;
        
    }

    public boolean isBloccata() {
        return this.bloccata;
    }
}
