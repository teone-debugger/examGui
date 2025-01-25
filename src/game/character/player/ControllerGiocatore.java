package game.character.player;

import game.Posizione;
import game.character.enemies.Png;
import messaggi.Messaggio;

public class ControllerGiocatore {
    

    private static ControllerGiocatore instance = null;

    public static ControllerGiocatore getInstance() {
        if (instance == null) {
            instance = new ControllerGiocatore();
        }
        return instance;
    }

    //Metodo per creare un messaggio a differenza della posizione trovata
    public void messagge(Posizione p){
            switch (p.getClass().getSimpleName()) {
                case "Png":
    
                    Messaggio.addMessaggio("VUOI PARLARE CON '" + ((Png)p).getNome() + "' ?");
                    break;
                case "Drago":
    
                    Messaggio.addMessaggio("VUOI COMBATTERE IL DRAGO? ");
                    break;
    
                case "Arma":
                case "Oggetto":
    
                    Messaggio.addMessaggio("HAI TROVATO  UN OGGETTO. RACCOGLIERLO? \n");
                    break;
    
                case "Armatura":
    
                    Messaggio.addMessaggio("Armatura");
                    break;
                case "Porta":
    
                    Messaggio.addMessaggio("VUOI OLTREPASSARE LA PORTA? ");
                    break;
            }
        }

}
