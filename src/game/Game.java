package game;

import game.character.player.Giocatore;

import java.io.IOException;

import com.google.api.client.json.Json;
import com.google.firebase.FirebaseApp;

import game.Dungeon;
import interfaccia.FrameMenu.FrameMenu;
import interfaccia.framesGame.FrameGame;
import game.enums.*;
import saveManager.*;

import messaggi.Scn;

import game.character.enemies.Drago;

public class Game {

    private static boolean terminal;

    private static int selectPuntiVita(Razza razza) {
        switch(razza){
            case ELFO:
                return 20;
            case UMANO:
                return 25;
            case NANO:
                return 30;
        }
        return 0;
    }
    private static int selectPuntiArmatura(Classe classe) {
        switch(classe){
            case BARBARO:
                return 12;
            case LADRO:
                return 10;
            case MAGO:
                return 8;
        }
        return 0;
    }
    private static Razza selectRazza(String str) {
        Razza r = null;

            switch (str.toLowerCase()) {
                case "umano":
                case "u":
                    r = Razza.UMANO;
                    break;
                case "elfo":
                case "e":
                    r = Razza.ELFO;
                    break;
                case "nano":
                case "n":
                    r = Razza.NANO;
                    break;
            }

        //System.out.println(r);
        return r;
    }
    private static Classe selectClasse(String str) {
        Classe c = null;

            switch (str.toLowerCase()) {
                case "mago":
                case "m":
                    c = Classe.MAGO;
                    break;
                case "ladro":
                case "l":
                    c = Classe.LADRO;
                    break;
                case "barbaro":
                case "b":
                    c = Classe.BARBARO;
                    break;
            }

        //System.out.println(c);
        return c;
    }

    public Giocatore createGiocatoreTerminal(){
        System.out.print("INSERISCI NOME GIOCATORE: ");
        String nome = Scn.getInstance().next().toUpperCase();

        String s = " ";

        while(!s.equals("umano") && !s.equals("u") && !s.equals("elfo") && !s.equals("e") && !s.equals("nano") && !s.equals("n")) {
            System.out.println("SCEGLI LA RAZZA GIOCATORE: (UMANO/U - ELFO/E - NANO/N)");
            s = Scn.getInstance().next().toLowerCase();
        }
        Razza razza = selectRazza(s);

        while(!s.equals("mago") && !s.equals("m") && !s.equals("ladro") && !s.equals("l") && !s.equals("barbaro") && !s.equals("b")) {
            System.out.println("SCEGLI LA CLASSE GIOCATORE: (MAGO/M - LADRO/L - BARBARO/B)");
            s = Scn.getInstance().next().toLowerCase();
        }
        Classe classe = selectClasse(s);

        return new Giocatore(nome, selectPuntiVita(razza), selectPuntiArmatura(classe), 300, classe, razza, 1, 1);

    }
    public static Giocatore createGiocatoreGui(String razza, String classe, String nome){

        Razza razzaObj = selectRazza(razza);
        Classe classeObj = selectClasse(classe);

        return new Giocatore(nome, selectPuntiVita(razzaObj), selectPuntiArmatura(classeObj), 300, classeObj, razzaObj, 1, 1);

    }

    public void play(){
        System.out.println("DO YOU PREFER TO PLAY WITH GUI/G OR TERMINAL/T: ");
        switch (Scn.getInstance().nextLine().toLowerCase()) {
            case "g":
            case "gui":
                terminal = false;
                playGui();
                break;
            case "t":
            case "terminal":
                terminal = true;
                playTerminal();
                break;
            default:
                play();
                break;
        }

    }

    private void playGui() {

        new FrameMenu();
        //new FrameSelection();

    }

    public void playTerminal() {


        /**--- RIGHE:(NUMERO DIVISIBILE PER 4) +1 ---**//**--- COLONNE:(NUMERO DIVISIBILE PER 8) +1 ---**/
        /**--- PASSO I DIVISORI GRANDEZZE STANZE ---**/
        /**--- DIVISORI OBBLIGO PARI PER LE PORTE ---**/

        //gioco.Giocatore g = new gioco.Giocatore("HAL", 25, 10, 300, gioco.Classe.LADRO, gioco.Razza.ELFO, 1,1);
        System.out.println("DO YOU PREFER TO START A 'NEW' GAME OR 'LOAD' A PREVIOUS ONE: ");
        switch (Scn.getInstance().nextLine().toLowerCase()) {
            case "new":
                Dungeon.getInstance(17, 81, 4, 8, createGiocatoreTerminal(), new Drago());
                break;
            case "load":
                try {
                    Dungeon.getInstance(17, 81, 4, 8, JsonSaving.loadFromFile("resources/firebase/savesLogs/save.json"), new Drago());
                    Dungeon.showDungeon();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                break;
            default:
                playTerminal();
                break;
        }
            

        //Dungeon.showDungeon();

        String str;

        while (!isWin()) {

            System.out.print("\nselect direction (up/u - down/d - left/l - right/r) to move \nor press 'i' for the inventory or press 's' for stats or press 'b' to return on the previous room \n'save' to save or 't' to throw object: ");
            str = Scn.getInstance().nextLine();
            switch (str.toLowerCase()) {
                case "i":

                    Giocatore.getInventory().showInventory();
                    break;
                case "s":

                    Dungeon.getGiocatore().showStats();
                    break;
                case "b":
                    if(Dungeon.getGiocatore().getBackRoom()==null){
                        System.out.println("Non puoi tornare indietro");
                    }else{
                        
                        Dungeon.getGiocatore().setRighe(Dungeon.getGiocatore().getBackRoom().getRighe());
                        Dungeon.getGiocatore().setColonne(Dungeon.getGiocatore().getBackRoom().getColonne());
            
                        Dungeon.setPosizioneMappa(Dungeon.getGiocatore());
                    }

                    Dungeon.showDungeon();
                    break;
                case "save":
                    Game.saveGame();
                    System.out.println("HAI SALVATO LA PARTITA");
                    Runtime.getRuntime().exit(404);
                    break;
                case "t":
                    Dungeon.getGiocatore().throwObject();
                    break;

                default:
                    Dungeon.getGiocatore().move(str);
                    try {
                        Dungeon.getGiocatore().around();
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                    break;
            }
            /*if (str.equalsIgnoreCase("i")) {

                Giocatore.getInventory().showInventory();

            } else if (str.equalsIgnoreCase("s")) {

                Dungeon.getGiocatore().showStats();

            } else if (str.equalsIgnoreCase("b")) {
                
                if(Dungeon.getGiocatore().getBackRoom()==null){
                    System.out.println("Non puoi tornare indietro");
                }else{
                    
                    Dungeon.getGiocatore().setRighe(Dungeon.getGiocatore().getBackRoom().getRighe());
                    Dungeon.getGiocatore().setColonne(Dungeon.getGiocatore().getBackRoom().getColonne());
        
                    Dungeon.setPosizioneMappa(Dungeon.getGiocatore());
                }

                Dungeon.showDungeon();
                
            } else if (str.equalsIgnoreCase("save")) {
                
                Game.saveGame();
                System.out.println("HAI SALVATO LA PARTITA");
                Runtime.getRuntime().exit(404);
                
            }
            else if (str.equalsIgnoreCase("l")) {
                
                try {
                    Giocatore g = JsonSaving.loadFromFile("resources/firebase/savesLogs/save.json");
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Dungeon.get

                Dungeon.showDungeon();
                
            }else {
                Dungeon.getGiocatore().move(str);
                try {
                    Dungeon.getGiocatore().around();
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
                //Dungeon.showDungeon();
            }*/


        }
    }

    public static void saveGame(){

        JsonSaving.saveToFileClasse(Giocatore.getClasse(), "resources/firebase/savesLogs/classe.json");
        JsonSaving.saveToFileRazza(Giocatore.getRazza(), "resources/firebase/savesLogs/razza.json");
        JsonSaving.saveToFileInventario(Giocatore.getInventory(), "resources/firebase/savesLogs/inventory.json");
        JsonSaving.saveToFile(Dungeon.getGiocatore(), "resources/firebase/savesLogs/save.json");

        /*FirebaseInitializer fb = new FirebaseInitializer();
        fb.saveToCloud("resources/firebase/savesLogs/classe.json", "classe.json");
        fb.saveToCloud("resources/firebase/savesLogs/razza.json", "razza.json");
        fb.saveToCloud("resources/firebase/savesLogs/inventory.json", "inventory.json");
        fb.saveToCloud("resources/firebase/savesLogs/save.json", "save.json");*/

        /*fb.saveToCloud("classe.json");
        fb.saveToCloud( "razza.json");
        fb.saveToCloud( "inventory.json");
        fb.saveToCloud( "save.json");*/
    }

    public static boolean isWin(){
        return Dungeon.getDrago().getPuntiVita() <= 0 || Dungeon.getGiocatore().getMonete() >= 1800;
    }
    
    public static boolean isTerminal() {
        return terminal;
    }
}
