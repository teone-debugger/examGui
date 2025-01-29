package game;

import java.io.*;
import java.lang.Class;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import game.character.player.Giocatore;
import game.items.view.*;
import game.character.enemies.*;
import game.room.*;

@JsonIgnoreProperties(ignoreUnknown = true)

public final class Dungeon implements Serializable{

@JsonProperty

    private static final long serialVersionUID = 1L;

    private static Dungeon instance = null;

    private static Posizione mappa[][];
    private static int righe;
    private static int colonne;
    private final int maxItems;
    private final int maxPng;

    private static int divisoreRighe;
    private static int divisoreColonne;

    private static Giocatore giocatore;
    private static Drago drago;


    private Dungeon(){
        this.maxItems = 40;
        this.maxPng = 20;
    }

    //Costruttore della classe
    private Dungeon(int righe, int colonne, int divisoreRighe, int divisoreColonne, Giocatore giocatore, Drago drago){
        Dungeon.righe = righe;
        Dungeon.colonne = colonne;
        this.maxItems = 40;
        this.maxPng = 20;
        this.giocatore = giocatore;
        this.drago = drago;

        this.divisoreColonne = divisoreColonne;
        this.divisoreRighe = divisoreRighe;
        mappa = new Posizione[righe][colonne];
        createdungeon();

        mappa[giocatore.getRighe()][giocatore.getColonne()] = this.giocatore;

        //mappa[giocatore.getRighe()+ 1][giocatore.getColonne()+1] = new Oggetto(giocatore.getRighe()+ 1,giocatore.getColonne()+1, "CHIAVE");
        mappa[drago.getRighe()][drago.getColonne()] = this.drago;
        //mappa[giocatore.getRighe() + 1][giocatore.getColonne()+ 1] = new gioco.Oggetto(giocatore.getRighe() + 1,giocatore.getColonne()+ 1 ,"POZIONE");
    }



    //Metodo per prendere l'istanza del dungeon
    public static Dungeon getInstance(int righe, int colonne, int divisoreRighe, int divisoreColonne, Giocatore giocatore, Drago drago){
        if(instance == null){
            instance = new Dungeon(righe, colonne, divisoreRighe, divisoreColonne, giocatore, drago);
        }
        return instance;
    }

    public static Dungeon getInstance(){
        return instance;
    }

    public static void setInstance(Dungeon dungeon){
        instance = dungeon;
    }

    //Metodo per creare il dungeon
    private void createdungeon(){

        int index = 1;
        for(int r = 0; r < Dungeon.getRighe(); r++){
            for(int c = 0;  c < Dungeon.getColonne(); c++){

                setWallsMappa(r,c);
                index = setDoors(r, c, index);
            }
        }
            
            setOggetti();
            setPng();
    }

    //Metodo per inserire gli oggetti nel dungeon
    private void setOggetti() {
        /**--- MATH.RANDOM() * (MAX - MIN + 1) + MIN ---**/
        /**--- MAX = RIGHE - 2 PERCHE ARRAY PARTE DA ZERO E COSI' EVITO UN POSSIBILE MURO ---**/
        /**--- MIN = 1 PERCHE ARRAY PARTE DA ZERO E COSI' EVITO UN POSSIBILE MURO ---**/

        int randRighe;
        int randColonne;


        for(int i = 0; i < this.maxItems; i++) {
            
            randRighe = (int) (Math.random() * (getRighe() - 2) + 1);
            randColonne = (int) (Math.random() * (getColonne() - 2) + 1);
           
            if (mappa[randRighe][randColonne].isLibera() && !aroundDoors(randRighe, randColonne)) {
                if(i < 10){
                    mappa[randRighe][randColonne] = new Arma(randRighe, randColonne);
                }else {
                    mappa[randRighe][randColonne] = new Oggetto(randRighe, randColonne);
                }

            } else {
                i--;    /**--- ASSICURO DI AVERE SEMPRE UN MASSIMO OGGETTI ---**/
            }
        }
    }
    
    //Metodo per settare i png nel dungeon
    private void setPng() {
        /**--- MATH.RANDOM() * (MAX - MIN + 1) + MIN ---**/
        /**--- MAX = RIGHE - 2 PERCHE ARRAY PARTE DA ZERO E COSI' EVITO UN POSSIBILE MURO ---**/
        /**--- MIN = 1 PERCHE ARRAY PARTE DA ZERO E COSI' EVITO UN POSSIBILE MURO ---**/

        int randRighe;
        int randColonne;


        for(int i = 0; i < this.maxPng; i++) {
            randRighe = (int) (Math.random() * (getRighe() - 2) + 1);
            randColonne = (int) (Math.random() * (getColonne() - 2) + 1);


            if (mappa[randRighe][randColonne].isLibera() && !aroundDoors(randRighe, randColonne)) {

                if(i < 10) {

                    /**--- OSTILI ---**/

                    //Png.generateDialoghiOstili();

                    mappa[randRighe][randColonne] = new Png(randRighe, randColonne,  7, 150);
                }else {

                    /**--- NON OSTILI ---**/
                    //Png.generateDialoghiNonOstili();
                    mappa[randRighe][randColonne] = new Png(randRighe, randColonne);

                }
            } else {
                i--;    /**--- ASSICURO DI AVERE SEMPRE UN MASSIMO PNG ---**/
            }
        }
    }

    //Metodo per evitare l'inserimento di oggetti/png vicino alle porte
    private boolean aroundDoors(int r, int c){

        if(mappa[r-1][c].getClass().getSimpleName().equals("Porta") || mappa[r+1][c].getClass().getSimpleName().equals("Porta") ||
                mappa[r][c-1].getClass().getSimpleName().equals("Porta") || mappa[r][c+1].getClass().getSimpleName().equals("Porta")){

            return true;
        }
        return false;
    }

    //Metodo per inserire i muri nel dungeon
    private void setWallsMappa(int r, int c){

        /**--- RIGHE  ---**/

        if(r % this.divisoreRighe == 0){
            mappa[r][c] = new Muro(r, c,"-");
        }else{

            mappa[r][c] = new Posizione(r, c);

        }

        /**--- COLONNE ---**/

        if(c % this.divisoreColonne == 0) {

            if (r != 0 && r != Dungeon.getRighe() - 1){
                mappa[r][c] = new Muro(r, c, "|");
            }

        } /**--- NO ELSE ALTRIMENTI SI SOVRASCRIVONO I MURI ORIZZONTALI ---**/

    }

    //Metodo per nserire le porte nel dungeon
    private int setDoors(int r, int c, int index){

        /**--- NON METTO PORTE SUI BORDI ---**/

        if(r != 0 && r != Dungeon.getRighe()-1 && c != 0 && c != Dungeon.getColonne()-1){

            /**--- METTO LE PORTE OGNI DUE RIGHE(CENTRO STANZA)  ---**/
            /**--- CONTROLLO SUGLI INCROCI(NON DEVE ESSERCI)  ---**/
            /**--- METTO LE PORTE SOLO SUI MURI VERTICALI  ---**/

            if(r % (this.divisoreRighe/2) == 0 && r % this.divisoreRighe != 0 && c % this.divisoreColonne == 0){
                mappa[r][c] = new Porta(r, c,"#", index);
                setPath(r, c);
                return ++index;
            }
            if(c % (this.divisoreColonne/2) == 0 && c % this.divisoreColonne != 0 && r % this.divisoreRighe == 0){
                mappa[r][c] = new Porta(r, c,"#", index);
                setPath(r, c);
                return ++index;
            }
        }
        return index;
    }

    //Metodo per garatire il percorso minimo
    private void setPath(int r, int c){
        /**--- PERCORSO MINIMO ---**/
        if(getClassPosizione(r,c).getSimpleName().equals("Porta")){

            Porta p = (Porta) getMappa()[r][c];
            switch (p.getIndex()) {
                case 1:
                case 10:
                case 29:
                case 48:
                    p.setBloccata(false);
                    break;
            
                default:
                    break;
            }
            if(p.getIndex() >= 58 &&  p.getIndex() <= 66){
                p.setBloccata(false);
            }
        }

    }

    //Metodo per mostrare a terminale il dungeon
    public static void showDungeon(){
        for(int r = 0; r < getRighe(); r++){
            for(int c = 0; c < getColonne(); c++){
                System.out.print(mappa[r][c].getTipo());
            }
            System.out.println();
        }
    }

    //Metodo per convertire il dungeon in stringa 
    public static String  dungeonToString(){
        String str = "";
        for(int r = 0; r < getRighe(); r++){
            for(int c = 0; c < getColonne(); c++){

                str += mappa[r][c].getTipo();
            }

            str += "\n";
        }
        return str;
    }

    /**--- IMPLEMENTAZIONE DEI METODI DELL'INTERFACCIA Serializable --**/

    public static void serialize(Dungeon dungeon, String filePath) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(dungeon);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Dungeon deserialize(String filePath) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            return (Dungeon) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**--- METODI GETTER ---**/
    
    //Metodo per prendere la posizione del giocatore
    public static Posizione getPosizione(int righe, int colonne){

        return getMappa()[righe][colonne];
    }

    //Metodo per prendere la classe di una posizione del dungeon
    public static Class<?> getClassPosizione(int righe, int colonne){

        return getMappa()[righe][colonne].getClass();
    }
    //Metodo per prendere la mappa
    public static Posizione[][] getMappa() {return mappa;}

    //Metodo per prendere il numero di righe
    public static int getRighe() {return righe;}

    //Metodo per prendere il numero di colonne
    public static int getColonne() {return colonne;}

    //Metodo per prendere il giocatore nel dungeon
    public static Giocatore getGiocatore() {return giocatore;}

    //Metodo per prendere il divisore delle righe (per inserire le porte)
    public static  int getDivisoreRighe() {return divisoreRighe;}

    //Metodo per prendere il divisore delle colonne (per inserire le porte)
    public static int getDivisoreColonne() {return divisoreColonne;}

    //Metodo per prendere il drago nel dungeon
    public static Drago getDrago() {return drago;}

    /**--- METODI SETTER ---**/

    //Metodo per settare la posizione del giocatore
    public static void setPosizioneMappa(Posizione p) {Dungeon.mappa[p.getRighe()][p.getColonne()] = p;}

    
}
