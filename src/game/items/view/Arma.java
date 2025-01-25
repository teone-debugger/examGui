package game.items.view;

import game.enums.Classe;
import game.items.model.ModelArma;

public class Arma extends Oggetto {

    private int dado;
    private Classe classe;

    private static int count = 1;
    
    private ModelArma controllerArma = ModelArma.getInstance();


    //Metodo costruttore
    public Arma(int righe, int colonne) {

        super(righe,colonne,ModelArma.generateDescrizione(count));
        
        count++;

        dado = controllerArma.getDado(getDescrizione());
        classe = controllerArma.getClasse(getDescrizione());
        setPeso(controllerArma.getPeso(getDescrizione()));

    }

    /**--- METODI GETTER ---**/

    //Metodo per ottenere il dado dell'arma
    public int getDado() {
        return dado;
    }


    //Metodo per ottenere la classe dell'arma
    public Classe getClasse() {
        return classe;
    }


}
