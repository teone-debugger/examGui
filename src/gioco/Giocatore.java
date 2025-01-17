package gioco;

import interfaccia.FrameFight;
import interfaccia.FrameGame;
import messaggi.Messaggio;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import org.checkerframework.checker.units.qual.A;


public class Giocatore extends Personaggio{

    private  int puntiEsperienza;

    private static Classe classe;
    private static Razza razza;
    
        private final int pesoMax; /**--- PESO MASSIMO CHE PUO' PORTARE ---**/
        private static HashMap <Integer,Oggetto> inventario; /**--- BASATO SUL PESO ---**/
        //private HashMap <Integer,Oggetto> armi; /**--- COSI' DA NON SCORRERE TUTTO L'INVENTARIO ---**/
        private static Arma arma;
        private Personaggio nemico;

        private ArrayList<Posizione> posizioniTrovate;
    
        /**--- ARMA EQUIPAGGIATA ---**/
    
        public Giocatore(String nome, int puntiVita, int puntiArmatura, int monete, Classe classe, Razza razza, int righe, int colonne){
            super(righe, colonne,String.valueOf(nome.charAt(0)),nome,puntiVita,puntiArmatura,monete);

            this.classe = classe;
            this.razza = razza;
            this.puntiEsperienza = 0;
            this.pesoMax = 500; 
    
            inventario = new HashMap<>();
            posizioniTrovate = new ArrayList<>();

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
        public Posizione aroundGui(){
    
            /**--- UP ---**/
            Posizione p = Dungeon.getMappa()[this.getRighe() - 1][this.getColonne()];

                switch(p.getClass().getSimpleName()){
                    case "Oggetto":
                    case "Arma":
                    case "Png":
                    case "Drago":
                    if(!posizioniTrovate.contains(p)){

                        posizioniTrovate.add(p);
                        messagge(p);
                        return p;
                    }
    
                }
    
    
            /**--- DOWN ---**/
            p = Dungeon.getMappa()[this.getRighe() + 1][this.getColonne()];

            switch(p.getClass().getSimpleName()){
                case "Oggetto":
                case "Arma":
                case "Png":
                case "Drago":
                if(!posizioniTrovate.contains(p)){

                    posizioniTrovate.add(p);
                    messagge(p);
                    return p;
                }
    
            }
    
            /**--- LEFT ---**/
            p = Dungeon.getMappa()[this.getRighe()][this.getColonne() - 1];

            switch(p.getClass().getSimpleName()){
                case "Oggetto":
                case "Arma":
                case "Png":
                case "Drago":

                if(!posizioniTrovate.contains(p)){

                    posizioniTrovate.add(p);
                    messagge(p);
                    return p;
                }
    
            }
    
            /**--- RIGHT ---**/
            p = Dungeon.getMappa()[this.getRighe()][this.getColonne() + 1];

            switch(p.getClass().getSimpleName()){
                case "Oggetto":
                case "Arma":
                case "Png":
                case "Drago":
                if(!posizioniTrovate.contains(p)){

                    posizioniTrovate.add(p);
                    messagge(p);
                    return p;
                }
    
            }
    
    
            /**--- UP ---**/
            if (Dungeon.getMappa()[this.getRighe() - 1][this.getColonne()].getClass().getSimpleName().equals("Porta")) {
                messagge(Dungeon.getMappa()[this.getRighe() - 1][this.getColonne()]);
                return Dungeon.getMappa()[this.getRighe() - 1][this.getColonne()];
    
            }else
            /**--- DOWN ---**/
            if (Dungeon.getMappa()[this.getRighe() + 1][this.getColonne()].getClass().getSimpleName().equals("Porta")) {
                messagge(Dungeon.getMappa()[this.getRighe() + 1][this.getColonne()]);
                return Dungeon.getMappa()[this.getRighe() + 1][this.getColonne()];
            }else
            /**--- LEFT ---**/
            if (Dungeon.getMappa()[this.getRighe()][this.getColonne() - 1].getClass().getSimpleName().equals("Porta")) {
                messagge(Dungeon.getMappa()[this.getRighe()][this.getColonne() - 1]);
                return Dungeon.getMappa()[this.getRighe()][this.getColonne() - 1];
            }else
            /**--- RIGHT ---**/
            if (Dungeon.getMappa()[this.getRighe()][this.getColonne() + 1].getClass().getSimpleName().equals("Porta")) {
                messagge(Dungeon.getMappa()[this.getRighe()][this.getColonne() + 1]);
                return Dungeon.getMappa()[this.getRighe()][this.getColonne() + 1];
            }
    
            return null;
        }
        public void takeUpGui(Posizione p){
    
            Oggetto oggetto = null;
            //this.nemico = null;
    
            if(p.getClass().getSimpleName().equals("Arma")){
    
                oggetto = (Arma) p;
            }else if(p.getClass().getSimpleName().equals("Oggetto")){
    
                oggetto = (Oggetto) p;
            }
    
            if(oggetto != null){
    
                if (oggetto.getClass().getSimpleName().equals("Oggetto")) {
                    if ((oggetto.getPeso() + getPesoInventario()) <= pesoMax) {
    
                        inventario.put(oggetto.getIndex(), oggetto);
    
                        Messaggio.setMessaggio("HAI RACCOLTO '" + oggetto.getDescrizione() + "'");
    
                    } else {
                        Messaggio.setMessaggio("MIO CARO '" + this.getNome() + "' NON PUOI RACCOGLIERE L'OGGETTO... INVENTARIO PIENO");
                    }
    
                    /**--- SE L'ARMA E' IL BASTONE O UN ARMA CON LA STESSA RAZZA LA RACCOGLIE ---**/
                } else if (((Arma) oggetto).getClasse() == null || ((Arma) oggetto).getClasse().equals(Giocatore.classe)) {
                    if (oggetto.getPeso() + getPesoInventario() <= pesoMax) {
                        inventario.put(oggetto.getIndex(), oggetto);
                        
                        if(arma != null && arma.getDado() < ((Arma) oggetto).getDado() ){
                            arma = (Arma)oggetto;
                        }else {
                            arma = (Arma)oggetto;
                        }
    
                        Messaggio.setMessaggio("HAI RACCOLTO '" + oggetto.getDescrizione() + "'");
                    } else {
                        Messaggio.setMessaggio("MIO CARO '" + this.getNome() + "' NON PUOI RACCOGLIERE L'OGGETTO... INVENTARIO PIENO");
                    }
                } else {
    
                    Messaggio.setMessaggio("MIO CARO '" + this.getNome() + "' NON PUOI RACCOGLIERE L'ARMA... RAZZE INCOMPATIBILI");
                }
                Dungeon.setPosizioneMappa(new Posizione(oggetto.getRighe(), oggetto.getColonne(), " ", true));
    
            }
    
            if(p.getClass().getSimpleName().equals("Png") || p.getClass().getSimpleName().equals("Drago")){
    
                this.nemico = (Personaggio) p;
                talkGui();
            }
            /*if(p.getClass().getSimpleName().equals("Drago")){
                this.nemico = (Personaggio) p;
                talkGui();
            }*/
    
            if(p.getClass().getSimpleName().equals("Porta")){
    
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
                    goThroughGui((Porta)p, direzione);
    
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
                    
                }else if(searchInInventory("CHIAVE")){
                    Messaggio.addMessaggio("PORTA BLOCCATA");
                    Messaggio.addMessaggio("VUOI SBLOCCARE LA PORTA?");
                    //FrameGame.getMessaggi().setText(Messaggio.getMessaggio());
                }else {
                    Messaggio.addMessaggio("PORTA BLOCCATA");
                }
    
        }
    
        private void talkGui() {
    
            if(this.nemico.getClass().getSimpleName().equals("Png")) {
    
                Messaggio.addMessaggio(((Png) this.nemico).getDialogo());
    
                if(!((Png) this.nemico).isOstile()) {
                    Messaggio.addMessaggio("HAI PARLATO CON '" + this.nemico.getNome() + "'");
    
                }else{
                    new FrameFight();
    
                    Messaggio.addMessaggio(" COMBATTI CON '" + this.nemico.getNome() + "'");
    
                }
            }else {
                Messaggio.addMessaggio(Drago.getDialogo());
    
                new FrameFight();
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
                    case "Drago":
    
                        talk((Personaggio)p, "up", scn);
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
                    case "Drago":
    
                        talk((Personaggio)p, "up", scn);
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
                    case "Drago":
    
                        talk((Personaggio)p, "up", scn);
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
                    case "Drago":
    
                        talk((Personaggio)p, "up", scn);
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
            
            System.out.print("vuoi oltrepassare la porta? " + direzione + " (yes/y - no/n) ");
    
                switch(scn.nextLine().toLowerCase()){
                    case "yes":
                    case "y":
                    if(!p.isBloccata()){
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
                    }else if(searchInInventory("CHIAVE")){
                        System.out.println("PORTA BLOCCATA");
                        System.out.println("VUOI SBLOCCARE LA PORTA? (yes/y - no/n)");
                        switch(scn.nextLine().toLowerCase()){
                            case "yes":
                            case "y":
                                p.setBloccata(false);
                                removeFromInventory("CHIAVE");
                                System.out.println("PORTA SBLOCCATA");
                                break;
                            case "no":
                            case "n":
                                break;
                            default:
                                goThrough(p, direzione, scn);
                                break;

                        }
                    }else{
                        System.out.println("PORTA BLOCCATA");
                    }
    
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
                    }else if(((Arma) oggetto).getClasse() == null || ((Arma) oggetto).getClasse().equals(Giocatore.classe)) {
                        if (oggetto.getPeso() + getPesoInventario() <= pesoMax) {
                            inventario.put(oggetto.getIndex(), oggetto);
    
                            /**--- POI SOSTITUITO DAL METODO EQUIPMENT ---**/
                            if(Giocatore.arma != null) {
    
                                System.out.println("ARMA TROVATA " + oggetto.getDescrizione() + ": DANNO d" + ((Arma)oggetto).getDado()
                                        + "\n" + "ARMA EQUIPAGGIATA " + Giocatore.arma.getDescrizione() + ": DANNO d" + Giocatore.arma.getDado());
    
                                System.out.println("VUOI RACCOGLIERE L'ARMA?");
    
                                switch (Game.getScn().nextLine().toLowerCase()) {
                                    case "yes":
                                    case "y":
    
                                        Giocatore.arma = (Arma) oggetto;
                                    case "no":
                                    case "n":
    
                                        return;
                                }
                            }else{
    
                                System.out.println("ARMA TROVATA " + oggetto.getDescrizione() + ": DANNO d" + ((Arma)oggetto).getDado());
                                System.out.println("HAI RACCOLTO '" + oggetto.getDescrizione() + "'");
                                Giocatore.arma = (Arma) oggetto;
                            }
    
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
    
        private void talk(Personaggio personaggio, String direzione, Scanner scn) {
    
            this.nemico = null;
    
            if(personaggio.getClass().getSimpleName().equals("Png"))  this.nemico = personaggio;
    
            System.out.print("vuoi parlare con  '" + personaggio.getNome() + "' ? '" + direzione + "' (yes/y - no/n) ");
    
            switch(scn.nextLine().toLowerCase()){
                case "yes":
                case "y":
    
                    if(this.nemico != null)
                        System.out.println(((Png)this.nemico).getDialogo());
                    else
                        System.out.println(Drago.getDialogo());
    
    
                    if(this.nemico != null && !((Png)this.nemico).isOstile()) {
                        System.out.println("HAI PARLATO CON '" + personaggio.getNome() + "'");
    
                    }else{
                        int turnoGiocatore = this.rollD20();
                        int turnoPng = personaggio.rollD20();
    
                        pngInteractions(personaggio, scn, direzione, turnoGiocatore, turnoPng);
    
                    }
    
                    break;
                case "no":
                case "n":
    
                    System.out.println("non hai parlato con " + personaggio.getNome());
                    break;
                default:
                    talk(personaggio, direzione, scn);
                    break;
            }
        }
        private void pngInteractions(Personaggio personaggio, Scanner scn, String direzione, int turnoGiocatore, int turnoPng) {
    
            System.out.println("SCEGLI L'AZIONE ( COMBATTERE/C - FUGGIRE/F - STATS/S) ");
            switch (scn.nextLine().toLowerCase()){
                case "combattere":
                case "c":
    
    
                    if(Giocatore.getArma() != null){
    
                        /**--- FIGHT FINCHE' SONO VIVI ---**/
                        while(personaggio.isVivo() && this.isVivo()) {
                            this.fight(personaggio, turnoGiocatore, turnoPng, scn);
                        }
                    }else{
                        System.out.println("NON PUOI COMBATTERE CON " + personaggio.getNome() + " NON HAI UN ARMA");
                    }
    
                    break;
                case "fuggire":
                case "f":
    
                    /**--- MI SI ALLONTANA PROVANDO TUTTE LE DIREZIONI (POCO SENSATO) FUNZIONA PERCHE' UNA E' SICURAMENTE OCCUPATA DAL PNG ---**/
                    loseGold();

                    System.out.println("SEI SCAPPATO DA '" + personaggio.getNome() + "' ");
                    System.out.println(Messaggio.getMessaggio());

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
                    personaggio.showStats();
    
                default:
                    pngInteractions(personaggio, scn, direzione, turnoGiocatore, turnoPng);
                    break;
            }
        }
    
    
        public void fight(Personaggio personaggio, int turnoGiocatore, int turnoPng, Scanner scn){
    
            System.out.println("SCEGLI L'AZIONE ( ATTACCARE/A - CURA/C - STATS/S) ");
    
            switch(scn.nextLine().toLowerCase()){
    
                case "attaccare":
                case "a":
    
                    this.fightDinamic(personaggio, turnoGiocatore,turnoPng);
                    System.out.println(Messaggio.getMessaggio());
                    Messaggio.clearMesaggio();
    
                    break;
                case "cura":
                case "c":
    
                        if(this.heal()) {
                            personaggio.attack(this);
                        }else{
                            this.fight(personaggio, turnoGiocatore, turnoPng, scn);
                        }
    
                    System.out.println(Messaggio.getMessaggio());
                        Messaggio.clearMesaggio();
    
    
                    break;
                case "stats":
                case "s":
    
                    this.showStats();
                    personaggio.showStats();
    
                    this.fight(personaggio, turnoGiocatore, turnoPng, scn);
    
                    break;
    
                default:
                    this.fight(personaggio, turnoGiocatore, turnoPng, scn);
                    break;
            }
    
        }
        public void fightDinamic(Personaggio personaggio, int turnoGiocatore, int turnoPng) {
    
    
            if(turnoGiocatore > turnoPng){
                Messaggio.addMessaggio("IL PRIMO AD ATTACCARE SEI TU ' " + this.getNome() +" '");
                this.attack(personaggio);
    
                if(personaggio.isVivo()){
    
                    personaggio.attack(this);
                }
            }
            else{
    
                Messaggio.addMessaggio("IL PRIMO AD ATTACCARE E' ' " + personaggio.getNome() +" '");
                personaggio.attack(this);
    
                if(this.isVivo()) {
                    this.attack(personaggio);
                }
            }
    
            /**--- SE IL GIOCATORE MUORE IL PROGRAMMA SI FERMA ---**/
            if(!this.isVivo()){
    
                Messaggio.addMessaggio("MIO PRODE AVVENTURIERO '" + this.getNome() + "' SEI STATO SFORTUNATO HAI PERSO IN QUEST'AVEVNTURA");
                Dungeon.showDungeon();
                Giocatore.showInventario();/**--- STATIC O NON MOSTRIAMOLO ---**/
                //Runtime.getRuntime().exit(404);
    
            }/**--- SE IL PNG MUORE LASCIA L'ORO ---**/
            else if(!personaggio.isVivo()) {
    
                Dungeon.setPosizioneMappa(new Posizione(personaggio.getRighe(), personaggio.getColonne(), " ", true));
    
                this.takeGold(personaggio);
    
                Messaggio.addMessaggio("MIO PRODE AVVENTURIERO '" + this.getNome() + "' HAI SCONFITTO '" + personaggio.getNome() + "'");
                if(Game.isTerminal()) {
                    Dungeon.showDungeon();
                }
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
        public static boolean searchInInventory(String s) {
    
            for (Oggetto oggetto : inventario.values()) {
                if (oggetto.getDescrizione().equals(s)) {
                    return true;
                }
            }
    
            return false;
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
        public static Razza getRazza() {return razza;}
    public static Arma getArma(){return arma;} /**--- STATIC: USATO IN UNA SOPRACLASSE --**/
    public HashMap<Integer, Oggetto> getInventario() {return inventario;}
    public Personaggio getNemico() {
        return nemico;
    }
    private int getPesoInventario() {
        int somma = 0;

        for(Oggetto o : inventario.values()) {
            somma += o.getPeso();
        }

        return somma;
    }
    public ArrayList<Posizione> getPosizioniTrovate() {
        return posizioniTrovate;
    }

}
