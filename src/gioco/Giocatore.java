package gioco;

import interfaccia.FrameFight;

import java.util.HashMap;
import java.util.Scanner;


public class Giocatore extends Personaggio{

    private  int puntiEsperienza;

    private static Classe classe;
    private Razza razza;

    private final int pesoMax; /**--- PESO MASSIMO CHE PUO' PORTARE ---**/
    private static HashMap <Integer,Oggetto> inventario; /**--- BASATO SUL PESO ---**/
    //private HashMap <Integer,Oggetto> armi; /**--- COSI' DA NON SCORRERE TUTTO L'INVENTARIO ---**/
    private static Arma arma;
    private Png nemico;

    /**--- ARMA EQUIPAGGIATA ---**/

    public Giocatore(String nome, int puntiVita, int puntiArmatura, int monete, Classe classe, Razza razza, int righe, int colonne){
        super(righe, colonne,"@",nome,puntiVita,puntiArmatura,monete);

        this.classe = classe;
        this.razza = razza;
        this.puntiEsperienza = 0;
        this.pesoMax = 500;

        inventario = new HashMap<>();
        //this.armi = new HashMap<>();
    }

    /**--- CONSENTE IL MOVIMENTO AL GIOCATORE ---**/
    public void move(String direzione, Scanner scn){

        /**--- SPOSTAMENTO ---**/
        this.movements(direzione);

        /*try {

            around(scn);
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }*/

    }

    public void messagge(Posizione p){
        switch (p.getClass().getSimpleName()) {
            case "Png":

                System.out.println("VUOI PARLARE CON '" + ((Png)p).getNome() + "' ?");
                break;

            case "Arma":
            case "Oggetto":

                System.out.println("HAI TROVATO  UN OGGETTO \n");
                break;

            case "Armatura":

                System.out.println("Armatura");
                break;
            case "Porta":

                System.out.println("VUOI OLTREPASSARE LA PORTA? ");
                break;

        }
    }
    public Posizione aroundGui(){

        /**--- UP ---**/

            switch(Dungeon.getMappa()[this.getRighe() - 1][this.getColonne()].getClass().getSimpleName()){
                case "Oggetto":
                case "Arma":
                case "Png":
                    messagge(Dungeon.getMappa()[this.getRighe() - 1][this.getColonne()]);
                    return Dungeon.getMappa()[this.getRighe() - 1][this.getColonne()];

            }


        /**--- DOWN ---**/
        switch(Dungeon.getMappa()[this.getRighe() + 1][this.getColonne()].getClass().getSimpleName()){
            case "Oggetto":
            case "Arma":
            case "Png":
                messagge(Dungeon.getMappa()[this.getRighe() + 1][this.getColonne()]);
                return Dungeon.getMappa()[this.getRighe() + 1][this.getColonne()];

        }

        /**--- LEFT ---**/
        switch(Dungeon.getMappa()[this.getRighe()][this.getColonne() - 1].getClass().getSimpleName()){
            case "Oggetto":
            case "Arma":
            case "Png":
                messagge(Dungeon.getMappa()[this.getRighe()][this.getColonne() - 1]);
                return Dungeon.getMappa()[this.getRighe()][this.getColonne() - 1];

        }

        /**--- RIGHT ---**/
        switch(Dungeon.getMappa()[this.getRighe()][this.getColonne() + 1].getClass().getSimpleName()){
            case "Oggetto":
            case "Arma":
            case "Png":
                messagge(Dungeon.getMappa()[this.getRighe()][this.getColonne() + 1]);
                return Dungeon.getMappa()[this.getRighe()][this.getColonne() + 1];

        }


        /**--- UP ---**/
        if (Dungeon.getMappa()[this.getRighe() - 1][this.getColonne()].getClass().getSimpleName().equals("Porta")) {
            messagge(Dungeon.getMappa()[this.getRighe() - 1][this.getColonne()]);
            return Dungeon.getMappa()[this.getRighe() - 1][this.getColonne()];

        }
        /**--- DOWN ---**/
        if (Dungeon.getMappa()[this.getRighe() + 1][this.getColonne()].getClass().getSimpleName().equals("Porta")) {
            messagge(Dungeon.getMappa()[this.getRighe() + 1][this.getColonne()]);
            return Dungeon.getMappa()[this.getRighe() + 1][this.getColonne()];
        }
        /**--- LEFT ---**/
        if (Dungeon.getMappa()[this.getRighe()][this.getColonne() - 1].getClass().getSimpleName().equals("Porta")) {
            messagge(Dungeon.getMappa()[this.getRighe()][this.getColonne() - 1]);
            return Dungeon.getMappa()[this.getRighe()][this.getColonne() - 1];
        }

        /**--- RIGHT ---**/
        if (Dungeon.getMappa()[this.getRighe()][this.getColonne() + 1].getClass().getSimpleName().equals("Porta")) {
            messagge(Dungeon.getMappa()[this.getRighe()][this.getColonne() + 1]);
            return Dungeon.getMappa()[this.getRighe()][this.getColonne() + 1];
        }

        return null;
    }
    public void takeUpGui(Posizione p){

        Oggetto oggetto = null;
        Png png = null;
        Porta porta = null;
        if(p.getClass().getSimpleName().equals("Arma")){

            oggetto = (Arma) p;
        }else if(p.getClass().getSimpleName().equals("Oggetto")){

            oggetto = (Oggetto) p;
        }
        if(p.getClass().getSimpleName().equals("Png")){

            png = (Png) p;
        }
        if(p.getClass().getSimpleName().equals("Porta")){

            porta = (Porta) p;
        }

        if(oggetto != null){

            if (oggetto.getClass().getSimpleName().equals("Oggetto")) {
                if ((oggetto.getPeso() + getPesoInventario()) <= pesoMax) {

                    inventario.put(oggetto.getIndex(), oggetto);

                    System.out.println("HAI RACCOLTO '" + oggetto.getDescrizione() + "'");

                } else {
                    System.out.println("MIO CARO '" + this.getNome() + "' NON PUOI RACCOGLIERE L'OGGETTO... INVENTARIO PIENO");
                }

                /**--- SE L'ARMA E' IL BASTONE O UN ARMA CON LA STESSA RAZZA LA RACCOGLIE ---**/
            } else if (((Arma) oggetto).getClasse() == null || ((Arma) oggetto).getClasse().equals(this.classe)) {
                if (oggetto.getPeso() + getPesoInventario() <= pesoMax) {
                    inventario.put(oggetto.getIndex(), oggetto);

                    /**--- POI SOSTITUITO DAL METODO EQUIPMENT ---**/
                    arma = equip((Arma) oggetto);

                    System.out.println("HAI RACCOLTO '" + oggetto.getDescrizione() + "'");
                } else {
                    System.out.println("MIO CARO '" + this.getNome() + "' NON PUOI RACCOGLIERE L'OGGETTO... INVENTARIO PIENO");
                }
            } else {

                System.out.println("MIO CARO '" + this.getNome() + "' NON PUOI RACCOGLIERE L'ARMA... RAZZE INCOMPATIBILI");
            }
            Dungeon.setPosizioneMappa(new Posizione(oggetto.getRighe(), oggetto.getColonne(), " ", true));

        }

        if(png != null){
            talkGui(png);
        }
        if(porta != null){
            
            String direzione = null;
            /**--- UP ---**/
            if (Dungeon.getMappa()[this.getRighe() - 1][this.getColonne()].getClass().getSimpleName().equals("Porta")) {

                direzione = "up";

            }
            /**--- DOWN ---**/
            if (Dungeon.getMappa()[this.getRighe() + 1][this.getColonne()].getClass().getSimpleName().equals("Porta")) {

                direzione = "down";   
            }
            /**--- LEFT ---**/
            if (Dungeon.getMappa()[this.getRighe()][this.getColonne() - 1 ].getClass().getSimpleName().equals("Porta")) {

                direzione = "left";   
            }

            /**--- RIGHT ---**/
            if (Dungeon.getMappa()[this.getRighe()][this.getColonne() + 1 ].getClass().getSimpleName().equals("Porta")) {

                direzione = "right";
            }
            goThroughGui(porta, direzione);
        }

    }

    private void goThroughGui(Porta porta, String direzione) {
        if(!porta.isBloccata()){


                    switch(direzione.toLowerCase()){

                        case "up":
                        case "u":
                            Dungeon.setPosizioneMappa(new Posizione(this.getRighe(), this.getColonne(), " ", true));

                            /**--- OLTREPASSO LA PORTA **/
                            this.setRighe(this.getRighe()-2);

                            Dungeon.setPosizioneMappa(this);
                            break;

                        case "down":
                        case "d":
                            Dungeon.setPosizioneMappa(new Posizione(this.getRighe(), this.getColonne(), " ", true));

                            /**--- OLTREPASSO LA PORTA **/
                            this.setRighe(this.getRighe()+2);

                            Dungeon.setPosizioneMappa(this);
                            break;

                        case "left":
                        case "l":
                            Dungeon.setPosizioneMappa(new Posizione(this.getRighe(), this.getColonne(), " ", true));

                            /**--- OLTREPASSO LA PORTA **/
                            this.setColonne(this.getColonne()-2);

                            Dungeon.setPosizioneMappa(this);
                            break;

                        case "right":
                        case "r":
                            Dungeon.setPosizioneMappa(new Posizione(this.getRighe(), this.getColonne(), " ", true));

                            /**--- OLTREPASSO LA PORTA **/
                            this.setColonne(this.getColonne()+2);

                            Dungeon.setPosizioneMappa(this);
                            break;
                    }

            }

    }

    private void talkGui(Png png) {

        this.nemico = png;
                System.out.println(png.getDialogo());

                if(!png.isOstile()) {
                    System.out.println("HAI PARLATO CON '" + png.getNome() + "'");

                }else{
                    new FrameFight();

                    System.out.println(" COMBATTI CON '" + png.getNome() + "'");

                }
    }

    public void around(Scanner scn) throws ClassNotFoundException {

        if(Game.isTerminal()) {
            Dungeon.showDungeon();
        }

        findOggettiPng(scn);

        /**--- UP DOOR---**/
        /*Dungeon.getClassPosizione(this.getRighe() - 1, this.getColonne()).getSimpleName()*/
        if (Dungeon.getMappa()[this.getRighe() - 1][this.getColonne()].getClass().getSimpleName().equals("Porta")) {

            goThrough((Porta) Dungeon.getMappa()[this.getRighe() - 1][this.getColonne()], "up", scn);
        }else {

            /**--- DOWN DOOR---**/
            if (Dungeon.getClassPosizione(this.getRighe() + 1, this.getColonne()).getSimpleName().equals("Porta")) {

                goThrough((Porta) Dungeon.getMappa()[this.getRighe() + 1][this.getColonne()], "down", scn);
            }else
            /**--- LEFT DOOR---**/
                if (Dungeon.getClassPosizione(this.getRighe(), this.getColonne() - 1).getSimpleName().equals("Porta")) {

                    goThrough((Porta) Dungeon.getMappa()[this.getRighe()][this.getColonne() - 1], "left", scn);
                }else
                /**--- RIGHT DOOR---**/
                    if (Dungeon.getClassPosizione(this.getRighe(), this.getColonne() + 1).getSimpleName().equals("Porta")) {

                        goThrough((Porta) Dungeon.getMappa()[this.getRighe()][this.getColonne() + 1], "right", scn);
                    }

        }

    }
    private void findOggettiPng(Scanner scn) {
        /**--- UP ---**/
        Posizione p;
        if (!Dungeon.getMappa()[this.getRighe() - 1][this.getColonne()].isLibera()) {

            p = Dungeon.getMappa()[this.getRighe() - 1][this.getColonne()];

            switch(p.getClass().getSimpleName()){
                case "Png":

                    talk((Png)p, "up", scn);
                    break;
                case "Oggetto":
                case "Arma":
                case "Armatura":

                    takeUp((Oggetto)p,"up", scn);
                    break;
            }

        }
        /**--- DOWN ---**/
        if (!Dungeon.getMappa()[this.getRighe() + 1][this.getColonne()].isLibera()) {

            p = Dungeon.getMappa()[this.getRighe() + 1][this.getColonne()];


            switch (p.getClass().getSimpleName()) {
                case "Png":

                    talk((Png) p, "down", scn);
                    break;
                case "Oggetto":
                case "Arma":
                case "Armatura":

                    takeUp((Oggetto)p,"down", scn);
                    break;
            }
        }

        /**--- LEFT ---**/
        if (!Dungeon.getMappa()[this.getRighe()][this.getColonne() - 1 ].isLibera()) {

            p = Dungeon.getMappa()[this.getRighe()][this.getColonne() - 1];

            switch (p.getClass().getSimpleName()) {
                case "Png":

                    talk((Png) p, "left", scn);
                    break;
                case "Oggetto":
                case "Arma":
                case "Armatura":

                    takeUp((Oggetto)p,"left", scn);
                    break;
            }
        }

        /**--- RIGHT ---**/
        if (!Dungeon.getMappa()[this.getRighe()][this.getColonne() + 1 ].isLibera()) {

            p = Dungeon.getMappa()[this.getRighe()][this.getColonne() + 1];
            switch (p.getClass().getSimpleName()) {
                case "Png":

                    talk((Png) p, "right", scn);
                    break;
                case "Oggetto":
                case "Arma":
                case "Armatura":

                    takeUp((Oggetto)p,"right", scn);
                    break;
            }
        }

    }

    private void goThrough(Porta p, String direzione, Scanner scn) {

        if(!p.isBloccata()){

            System.out.print("vuoi oltrepassare la porta? " + direzione + " (yes/y - no/n) ");

            switch(scn.nextLine().toLowerCase()){
                case "yes":
                case "y":

                    switch(direzione.toLowerCase()){

                        case "up":
                        case "u":
                            Dungeon.setPosizioneMappa(new Posizione(this.getRighe(), this.getColonne(), " ", true));

                            /**--- OLTREPASSO LA PORTA **/
                            this.setRighe(this.getRighe()-2);

                            Dungeon.setPosizioneMappa(this);
                            break;

                        case "down":
                        case "d":
                            Dungeon.setPosizioneMappa(new Posizione(this.getRighe(), this.getColonne(), " ", true));

                            /**--- OLTREPASSO LA PORTA **/
                            this.setRighe(this.getRighe()+2);

                            Dungeon.setPosizioneMappa(this);
                            break;

                        case "left":
                        case "l":
                            Dungeon.setPosizioneMappa(new Posizione(this.getRighe(), this.getColonne(), " ", true));

                            /**--- OLTREPASSO LA PORTA **/
                            this.setColonne(this.getColonne()-2);

                            Dungeon.setPosizioneMappa(this);
                            break;

                        case "right":
                        case "r":
                            Dungeon.setPosizioneMappa(new Posizione(this.getRighe(), this.getColonne(), " ", true));

                            /**--- OLTREPASSO LA PORTA **/
                            this.setColonne(this.getColonne()+2);

                            Dungeon.setPosizioneMappa(this);
                            break;
                    }

                    Dungeon.showDungeon();
                    findOggettiPng(scn);

                    break;
                case "no":
                case "n":
                    Dungeon.showDungeon();
                    break;
                default:
                    goThrough(p, direzione, scn);
                    break;
            }

        }
    }
    private void takeUp(Oggetto oggetto, String direzione, Scanner scn){

        System.out.print("vuoi raccogliere l'oggetto? '" + direzione + "' (yes/y - no/n) ");

        switch(scn.nextLine().toLowerCase()){
            case "yes":
            case "y":
                System.out.println("HAI TROVATO '" + oggetto.getDescrizione() + "' \n");

                if(oggetto.getClass().getSimpleName().equals("Oggetto")){
                    if((oggetto.getPeso() + getPesoInventario()) <= pesoMax){

                        inventario.put(oggetto.getIndex(), oggetto);

                        System.out.println("HAI RACCOLTO '" + oggetto.getDescrizione() + "'");

                    }else {
                        System.out.println("MIO CARO '" + this.getNome() + "' NON PUOI RACCOGLIERE L'OGGETTO... INVENTARIO PIENO");
                    }
                }else if(((Arma) oggetto).getClasse() == null || ((Arma) oggetto).getClasse().equals(this.classe)) {
                    if (oggetto.getPeso() + getPesoInventario() <= pesoMax) {
                        inventario.put(oggetto.getIndex(), oggetto);

                        /**--- POI SOSTITUITO DAL METODO EQUIPMENT ---**/
                        arma = equip((Arma) oggetto);

                        System.out.println("HAI RACCOLTO '" + oggetto.getDescrizione() + "'");
                    } else {
                        System.out.println("MIO CARO '" + this.getNome() + "' NON PUOI RACCOGLIERE L'OGGETTO... INVENTARIO PIENO");
                    }
                }else {

                    System.out.println("MIO CARO '" + this.getNome() + "' NON PUOI RACCOGLIERE L'ARMA... RAZZE INCOMPATIBILI");
                }
                Dungeon.setPosizioneMappa(new Posizione(oggetto.getRighe(), oggetto.getColonne(), " ", true));
                Dungeon.showDungeon();

                break;
            case "no":
            case "n":

                System.out.println("non hai raccolto l'oggetto " + oggetto.getDescrizione());
                break;
            default:
                takeUp(oggetto, direzione, scn);
                break;
        }

        /**--- SE L'OGGETTO E' UN ARMA LA METTO NELLE ARMI ---**/
        /*if(oggetto.getClass() == Arma.class){
            this.armi.put(oggetto.getIndex(), oggetto);
        }*/
    }

    private Arma equip(Arma arma) {
        return arma;
    }

    private void talk(Png png, String direzione, Scanner scn) {

        System.out.print("vuoi parlare con  '" + png.getNome() + "' ? '" + direzione + "' (yes/y - no/n) ");

        switch(scn.nextLine().toLowerCase()){
            case "yes":
            case "y":

                System.out.println(png.getDialogo());

                if(!png.isOstile()) {
                    System.out.println("HAI PARLATO CON '" + png.getNome() + "'");

                }else{
                    int turnoGiocatore = this.rollD20();
                    int turnoPng = png.rollD20();

                    pngInteractions(png, scn, direzione, turnoGiocatore, turnoPng);

                }

                break;
            case "no":
            case "n":

                System.out.println("non hai parlato con " + png.getNome());
                break;
            default:
                talk(png, direzione, scn);
                break;
        }
    }
    private void pngInteractions(Png png, Scanner scn, String direzione, int turnoGiocatore, int turnoPng) {

        System.out.println("SCEGLI L'AZIONE ( COMBATTERE/C - FUGGIRE/F - STATS/S) ");
        switch (scn.nextLine().toLowerCase()){
            case "combattere":
            case "c":


                if(Giocatore.getArma() != null){

                    /**--- FIGHT FINCHE' SONO VIVI ---**/
                    while(png.isVivo() && this.isVivo()) {
                        this.fight(png, turnoGiocatore, turnoPng, scn);
                    }
                }else{
                    System.out.println("NON PUOI COMBATTERE CON " + png.getNome() + " NON HAI UN ARMA");
                }

                break;
            case "fuggire":
            case "f":

                /**--- MI SI ALLONTANA PROVANDO TUTTE LE DIREZIONI (POCO SENSATO) FUNZIONA PERCHE' UNA E' SICURAMENTE OCCUPATA DAL PNG ---**/

                System.out.println("SEI SCAPPATO DA '" + png.getNome() + "' ");
                //Dungeon.showDungeon();

                switch (direzione) {
                    case "up":

                        this.movements("down");
                        this.movements("left");
                        this.movements("right");

                        break;
                    case "down":

                        this.movements("up");
                        this.movements("left");
                        this.movements("right");

                        break;
                    case "left":

                        this.movements("up");
                        this.movements("down");
                        this.movements("right");

                        break;
                    case "right":

                        this.movements("up");
                        this.movements("down");
                        this.movements("left");

                        break;
                }
                Dungeon.showDungeon();

                break;
            case "stats":
            case "s":

                /**--- MOSTRO STATISTICHE ---**/
                this.showStats();
                png.showStats();

            default:
                pngInteractions(png, scn, direzione, turnoGiocatore, turnoPng);
                break;
        }
    }


    public void fight(Png png, int turnoGiocatore, int turnoPng, Scanner scn){

        System.out.println("SCEGLI L'AZIONE ( ATTACCARE/A - CURA/C - STATS/S) ");

        switch(scn.nextLine().toLowerCase()){

            case "attaccare":
            case "a":

                this.fightDinamic(png, turnoGiocatore,turnoPng);

                break;
            case "cura":
            case "c":

                    if(this.heal()) {
                        png.attack(this);
                    }else{
                        this.fight(png, turnoGiocatore, turnoPng, scn);
                    }


                break;
            case "stats":
            case "s":

                this.showStats();
                png.showStats();

                this.fight(png, turnoGiocatore, turnoPng, scn);

                break;

            default:
                this.fight(png, turnoGiocatore, turnoPng, scn);
                break;
        }

    }
    public void fightDinamic(Png png, int turnoGiocatore, int turnoPng) {


        if(turnoGiocatore > turnoPng){
            System.out.println("IL PRIMO AD ATTACCARE SEI TU ' " + this.getNome() +" '");
            this.attack(png);

            if(png.isVivo()){

                png.attack(this);
            }
        }
        else{

            System.out.println("IL PRIMO AD ATTACCARE E' ' " + png.getNome() +" '");
            png.attack(this);

            if(this.isVivo()) {
                this.attack(png);
            }
        }

        /**--- SE IL GIOCATORE MUORE IL PROGRAMMA SI FERMA ---**/
        if(!this.isVivo()){

            System.out.println("MIO PRODE AVVENTURIERO '" + this.getNome() + "' SEI STATO SFORTUNATO HAI PERSO IN QUEST'AVEVNTURA");
            Dungeon.showDungeon();
            Giocatore.showInventario();/**--- STATIC O NON MOSTRIAMOLO ---**/

            Runtime.getRuntime().exit(404);

        }/**--- SE IL PNG MUORE LASCIA L'ORO ---**/
        else if(!png.isVivo()) {

            Dungeon.setPosizioneMappa(new Posizione(png.getRighe(), png.getColonne(), " ", true));

            this.takeGold(png);

            System.out.println("MIO PRODE AVVENTURIERO '" + this.getNome() + "' HAI SCONFITTO '" + png.getNome() + "'");
            Dungeon.showDungeon();
        }
    }

    public static Class<?> removeFromInventory(String s) {

        for (Oggetto oggetto : inventario.values()) {
            if (oggetto.getDescrizione().equals(s)) {
                Oggetto g = inventario.remove(oggetto.getIndex());
                return g.getClass();
            }
        }

        return null;
    }

    public static void showInventario() {

        System.out.print("{");
        int count = 0;
        for(Oggetto oggetto : inventario.values()) {
            if (count == 0){
                count++;
                if(oggetto.getClass().getSimpleName().equals("Arma")) {

                    System.out.print("Weapon: " + oggetto.getIndex() + " - ");
                }else{

                    System.out.print(oggetto.getIndex() + " - ");
                }
            }else{
                if(oggetto.getClass().getSimpleName().equals("Arma")) {

                    System.out.print(" ; " + "Weapon: " + oggetto.getIndex() + " - ");
                }else {

                    System.out.print(" ; " + oggetto.getIndex() + " - ");
                }
            }
            System.out.print(oggetto.getDescrizione());
        }
        System.out.println("}");
    }
    public static String inventarioToString() {

        String str = "{";
        int count = 0;
        for(Oggetto oggetto : inventario.values()) {
            if (count == 0){
                if(oggetto.getClass().getSimpleName().equals("Arma")) {

                    str += "Weapon: " + oggetto.getIndex() + " - ";
                }else{

                    str += oggetto.getIndex() + " - ";
                }
            }else{
                if(oggetto.getClass().getSimpleName().equals("Arma")) {

                    str += "Weapon: " + oggetto.getIndex() + " - " ;
                }else {

                    str +=  oggetto.getIndex() + " - ";
                }
            }
            str +=oggetto.getDescrizione() + " ; ";
            count++;
            if(count == 3){
                str += "\n";
            }
        }
        return str += "}";
    }

    /**---- UTIL METHODS: GETTER ----**/

    int getPuntiEscperienza() {return puntiEsperienza;}

    public static Classe getClasse() {return classe;}
    public Razza getRazza() {return razza;}
    public static Arma getArma(){return arma;} /**--- STATIC: USATO IN UNA SOPRACLASSE --**/
    public HashMap<Integer, Oggetto> getInventario() {return inventario;}
    public Png getNemico() {
        return nemico;
    }
    private int getPesoInventario() {
        int somma = 0;

        for(Oggetto o : inventario.values()) {
            somma += o.getPeso();
        }

        return somma;
    }

}
