package game.room;

import game.Posizione;
import util.RandomUtils;

public class Porta extends Posizione {

    private boolean bloccata;
    private int index;


    //Metodo costruttore
    public Porta(int riga, int colonna, String tipo, int index) {
        super(riga,colonna, tipo);

        this.index = index;

        if(RandomUtils.getRandomNumber(0, 6) == 0) {
            bloccata = true;
        } else {
            bloccata = false;
        }
    }

    /**--- METODI GETTER ---**/

    //Metodo per ottenere l'indice
    public int getIndex() {return index;}

    //Metodo per ottenere se la porta è bloccata
    public boolean isBloccata() {return this.bloccata;}

    /**--- METODI SETTER ---**/

    //Metodo per bloccare la porta
    public void setBloccata(boolean bloccata) {this.bloccata = bloccata;}
    
}
