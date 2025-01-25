package game.items.view;

import game.Posizione;
import game.items.model.ModelOggetto;

public class Oggetto extends Posizione {

    private int peso;
    private String descrizione;
    private static int count = 1;

    private Integer index = 0;

    private ModelOggetto controllerOggetto = ModelOggetto.getInstance();

    public Oggetto(int righe, int colonne) {
        super(righe, colonne, "*");

        this.descrizione = ModelOggetto.generateObjects(count);
        setPeso(controllerOggetto.getPeso(descrizione));

        this.index = count;
        count++;

    }
    public Oggetto(int righe, int colonne, String descrizione) {
        super(righe, colonne, "%");

        /*this.index = count;
        count++;*/

        this.descrizione = descrizione;

    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public Integer getIndex(){
        return index;
    }
}
