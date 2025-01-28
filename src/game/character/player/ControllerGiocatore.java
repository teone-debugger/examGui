package game.character.player;

import game.Dungeon;
import game.Posizione;
import game.character.enemies.Png;
import game.enums.*;
import interfaccia.multimedia.ImagePanel;
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

    //Metodo per generare l'immagine del giocatore a differenza della classe e razza
    public ImagePanel getImmagine(Razza razza, Classe classe){
            ImagePanel immagine;

            switch(razza.toString().toUpperCase().toString().toUpperCase()) {
                case "UMANO":
                   
                    switch (classe.toString().toUpperCase()) {
                        case "LADRO":
                            immagine = new ImagePanel("resources/images/pg principali/UMANO/UMANOLADRO.png", 200, 200);
                            break;
                    
                        case "MAGO":
                            immagine = new ImagePanel("resources/images/pg principali/UMANO/UMANOMAGO.png", 200, 200);
                            break;

                        case "BARBARO":
                            immagine = new ImagePanel("resources/images/pg principali/UMANO/UMANOBARBARO.png", 200, 200);
                            break;

                        default:
                            immagine = new ImagePanel(classe.toString().toUpperCase(), 200, 200);
                            break;
                    }
                    break;

                case "NANO":

                    switch (classe.toString().toUpperCase()) {
                        case "LADRO":
                            immagine = new ImagePanel("resources/images/pg principali/NANO/NANOLADRO.png", 200, 200);
                            break;
                    
                        case "MAGO":
                            immagine = new ImagePanel("resources/images/pg principali/NANO/NANOMAGO.png", 200, 200);
                            break;

                        case "BARBARO":
                            immagine = new ImagePanel("resources/images/pg principali/NANO/NANOBARBARO.png", 200, 200);
                            break;

                        default:
                            immagine = new ImagePanel(classe.toString().toUpperCase(), 200, 200);
                            break;
                    }
                    break;

                case "ELFO":
                    switch (classe.toString().toUpperCase()) {
                        case "LADRO":
                            immagine = new ImagePanel("resources/images/pg principali/ELFO/ELFOLADRO.png", 200, 200);
                            break;
                    
                        case "MAGO":
                            immagine = new ImagePanel("resources/images/pg principali/ELFO/ELFOMAGO.png", 200, 200);
                            break;

                        case "BARBARO":
                            immagine = new ImagePanel("resources/images/pg principali/ELFO/ELFOBARBARO.png", 200, 200);
                            break;

                        default:
                            immagine = new ImagePanel(classe.toString().toUpperCase(), 200, 200);
                            break;
                    }
                    break;

                default:
                    immagine = new ImagePanel("resources/images/pg principali/ELFO/image (12).png", 200, 200);
                    break;

            }
            return immagine;

    }

}
