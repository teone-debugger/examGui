package gioco;

import java.lang.Class;

public class Dungeon{

    private static Posizione mappa[][];
    private static int righe;
    private static int colonne;
    private final int maxOggetti;
    private final int maxPng;

    private static int divisoreRighe;
    private static int divisoreColonne;

    private static Giocatore giocatore;
    private Drago drago;

    public Dungeon(int righe, int colonne, int divisoreRighe, int divisoreColonne, Giocatore giocatore, Drago drago){
        Dungeon.righe = righe;
        Dungeon.colonne = colonne;
        this.maxOggetti = 30;
        this.maxPng = 20;
        this.giocatore = giocatore;
        this.drago = drago;

        this.divisoreColonne = divisoreColonne;
        this.divisoreRighe = divisoreRighe;
        mappa = new Posizione[righe][colonne];
        createdungeon();

        mappa[giocatore.getRighe()][giocatore.getColonne()] = this.giocatore;
        mappa[drago.getRighe()][drago.getColonne()] = this.drago;
        //mappa[giocatore.getRighe() + 1][giocatore.getColonne()+ 1] = new gioco.Oggetto(giocatore.getRighe() + 1,giocatore.getColonne()+ 1 ,"POZIONE");
    }

    private void createdungeon(){

        for(int r = 0; r < Dungeon.getRighe(); r++){
            for(int c = 0;  c< Dungeon.getColonne(); c++){

                setWallsMappa(r,c);
                setDoors(r, c);
            }
        }
            setOggetti();
            setPng();
    }

    private void setOggetti() {
        /**--- MATH.RANDOM() * (MAX - MIN + 1) + MIN ---**/
        /**--- MAX = RIGHE - 2 PERCHE ARRAY PARTE DA ZERO E COSI' EVITO UN POSSIBILE MURO ---**/
        /**--- MIN = 1 PERCHE ARRAY PARTE DA ZERO E COSI' EVITO UN POSSIBILE MURO ---**/

        int randRighe;
        int randColonne;

        for(int i = 0; i < this.maxOggetti; i++) {
            randRighe = (int) (Math.random() * (getRighe() - 2) + 1);
            randColonne = (int) (Math.random() * (getColonne() - 2) + 1);

            if (mappa[randRighe][randColonne].isLibera()) {

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
    private void setPng() {
        /**--- MATH.RANDOM() * (MAX - MIN + 1) + MIN ---**/
        /**--- MAX = RIGHE - 2 PERCHE ARRAY PARTE DA ZERO E COSI' EVITO UN POSSIBILE MURO ---**/
        /**--- MIN = 1 PERCHE ARRAY PARTE DA ZERO E COSI' EVITO UN POSSIBILE MURO ---**/

        int randRighe;
        int randColonne;


        for(int i = 0; i < this.maxPng; i++) {
            randRighe = (int) (Math.random() * (getRighe() - 2) + 1);
            randColonne = (int) (Math.random() * (getColonne() - 2) + 1);

            if (mappa[randRighe][randColonne].isLibera()) {

                if(i < 10) {

                    mappa[randRighe][randColonne] = new Png(randRighe, randColonne);
                }else {

                    mappa[randRighe][randColonne] = new Png(randRighe, randColonne,  25, 7, 150);

                }
            } else {
                i--;    /**--- ASSICURO DI AVERE SEMPRE UN MASSIMO OGGETTI ---**/
            }
        }
    }

    private void setWallsMappa(int r, int c){

        /**--- RIGHE  ---**/

        if(r % this.divisoreRighe == 0){
            mappa[r][c] = new Muro(r, c,"-");
        }else{

            mappa[r][c] = new Posizione(r, c," ", true);

        }

        /**--- COLONNE ---**/

        if(c % this.divisoreColonne == 0) {

            if (r != 0 && r != Dungeon.getRighe() - 1){
                mappa[r][c] = new Muro(r, c, "|");
            }

        } /**--- NO ELSE ALTRIMENTI SI SOVRASCRIVONO I MURI ORIZZONTALI ---**/


        /**--- ANGOLI ---**/

       // if(r == 0 && c == 0){ /**--- ANGOLO ALTO A SINISTRA ---**/

         //   mappa[r][c] = new gioco.Muro(r, c,"╔");

        //}else if(r == 0 && c == gioco.Dungeon.getColonne()-1){/**--- ANGOLO ALTO A DESTRA ---**/

        //    mappa[r][c] = new gioco.Muro(r, c,"╗");

       // } else if (r == gioco.Dungeon.getRighe()-1 && c == 0) {/**--- ANGOLO BASSO A SINISTRA ---**/

        //    mappa[r][c] = new gioco.Muro(r, c,"╚");

       // } else if (r == gioco.Dungeon.getRighe()-1 && c == gioco.Dungeon.getColonne()-1) {/**--- ANGOLO BASSO A DESTRA ---**/

         //   mappa[r][c] = new gioco.Muro(r, c,"╝");
        //}

    }

    private void setDoors(int r, int c){

        /**--- NON METTO PORTE SUI BORDI ---**/

        if(r != 0 && r != Dungeon.getRighe()-1 && c != 0 && c != Dungeon.getColonne()-1){

            /**--- METTO LE PORTE OGNI DUE RIGHE(CENTRO STANZA)  ---**/
            /**--- CONTROLLO SUGLI INCROCI(NON DEVE ESSERCI)  ---**/
            /**--- METTO LE PORTE SOLO SUI MURI VERTICALI  ---**/

            if(r % (this.divisoreRighe/2) == 0 && r % this.divisoreRighe != 0 && c % this.divisoreColonne == 0){
                mappa[r][c] = new Porta(r, c,"#");
            }
            if(c % (this.divisoreColonne/2) == 0 && c % this.divisoreColonne != 0 && r % this.divisoreRighe == 0){
                mappa[r][c] = new Porta(r, c,"#");
            }
        }
    }

    /**--- MOSTRA IL DUNGEON ---**/
    public static void showDungeon(){
        for(int r = 0; r < getRighe(); r++){
            for(int c = 0; c < getColonne(); c++){
                System.out.print(mappa[r][c].getTipo());
            }
            System.out.println();
        }
    }

    /**--- STESSO SOPRA MA CREA UNA STRINGA ---**/
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

    public static Posizione[][] getMappa() {return mappa;}

    /**--- SINGOLA POSIZIONE ---**/
    public static Class<?> getClassPosizione(int righe, int colonne){

        return getMappa()[righe][colonne].getClass();
    }

    public static int getRighe() {return righe;}
    public static int getColonne() {return colonne;}
    public static Giocatore getGiocatore() {return giocatore;}

    public static  int getDivisoreRighe() {return divisoreRighe;}
    public static int getDivisoreColonne() {return divisoreColonne;}

    public static void setPosizioneMappa(Posizione p) {
        Dungeon.mappa[p.getRighe()][p.getColonne()] = p;
    }
}
