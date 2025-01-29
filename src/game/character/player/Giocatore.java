package game.character.player;

import game.Game;
import game.Dungeon;
import game.character.*;
import game.character.enemies.Png;
import game.Posizione;
import game.enums.*;
import game.items.view.*;
import game.room.Porta;
import game.character.enemies.Drago;
import util.RandomUtils;
import interfaccia.framesFight.FrameFight;
import messaggi.Messaggio;

import java.util.ArrayList;
import messaggi.Scn;


public class Giocatore extends Personaggio{

    private int puntiEsperienza;

    private static Classe classe;
    private static Razza razza;
    
    private static Inventario inventario;
    private static Arma arma;        /**--- ARMA EQUIPAGGIATA ---**/
    private Personaggio nemico;
    private ArrayList<Posizione> posizioniTrovate;

    private Posizione posizione=null;
    private Posizione p2=null;
    private boolean isNew=true;

    private ControllerGiocatore controllerGiocatore = ControllerGiocatore.getInstance();
    
        //Metodo costruttore
        public Giocatore(String nome, int puntiVita, int puntiArmatura, int monete, Classe classe, Razza razza, int righe, int colonne){
            super(righe, colonne,String.valueOf(nome.charAt(0)),nome,puntiVita,puntiArmatura,monete);

            this.classe = classe;
            this.razza = razza;
            this.puntiEsperienza = 0;    
            inventario = Inventario.getInstance();
            posizioniTrovate = new ArrayList<>();

            setImmagine(controllerGiocatore.getImmagine(razza, classe));

            //this.armi = new HashMap<>();
        }
    
        //Metodo per il movimento
        public void move(String direzione){
    
            /**--- SPOSTAMENTO ---**/
            this.movements(direzione);
    
            /*try {
    
                around(Scn.getInstance());
            } catch (ClassNotFoundException e) {
                System.out.println(e.getMessage());
            }*/
    
        }
        
        //Metodo per trovare la posizione utile attorno al giocatore (interfaccia grafica)
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
                        controllerGiocatore.messagge(p);
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
                    controllerGiocatore.messagge(p);
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
                    controllerGiocatore.messagge(p);
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
                    controllerGiocatore.messagge(p);
                    return p;
                }
    
            }
    
    
            /**--- UP ---**/
            if (Dungeon.getMappa()[this.getRighe() - 1][this.getColonne()].getClass().getSimpleName().equals("Porta")) {
                controllerGiocatore.messagge(Dungeon.getMappa()[this.getRighe() - 1][this.getColonne()]);
                return Dungeon.getMappa()[this.getRighe() - 1][this.getColonne()];
    
            }else
            /**--- DOWN ---**/
            if (Dungeon.getMappa()[this.getRighe() + 1][this.getColonne()].getClass().getSimpleName().equals("Porta")) {
                controllerGiocatore.messagge(Dungeon.getMappa()[this.getRighe() + 1][this.getColonne()]);
                return Dungeon.getMappa()[this.getRighe() + 1][this.getColonne()];
            }else
            /**--- LEFT ---**/
            if (Dungeon.getMappa()[this.getRighe()][this.getColonne() - 1].getClass().getSimpleName().equals("Porta")) {
                controllerGiocatore.messagge(Dungeon.getMappa()[this.getRighe()][this.getColonne() - 1]);
                return Dungeon.getMappa()[this.getRighe()][this.getColonne() - 1];
            }else
            /**--- RIGHT ---**/
            if (Dungeon.getMappa()[this.getRighe()][this.getColonne() + 1].getClass().getSimpleName().equals("Porta")) {
                controllerGiocatore.messagge(Dungeon.getMappa()[this.getRighe()][this.getColonne() + 1]);
                return Dungeon.getMappa()[this.getRighe()][this.getColonne() + 1];
            }
    
            return null;
        }

        //Metodo per raccogliere gli oggetti (interfaccia grafica)
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
                    if ((oggetto.getPeso() + inventario.getPesoInventory()) <= inventario.getPesoMax()) {
    
                        inventario.addToInventory( oggetto);
    
                        Messaggio.setMessaggio("HAI RACCOLTO '" + oggetto.getDescrizione() + "'");
    
                    } else {
                        Messaggio.setMessaggio("MIO CARO '" + this.getNome() + "' NON PUOI RACCOGLIERE L'OGGETTO... \nINVENTARIO PIENO");
                    }
    
                    /**--- SE L'ARMA E' IL BASTONE O UN ARMA CON LA STESSA RAZZA LA RACCOGLIE ---**/
                } else if (((Arma) oggetto).getClasse() == null || ((Arma) oggetto).getClasse().equals(Giocatore.classe)) {
                    if (oggetto.getPeso() + inventario.getPesoInventory() <= inventario.getPesoMax()) {
                        inventario.addToInventory( oggetto);
                        
                        if(arma != null && arma.getDado() < ((Arma) oggetto).getDado() ){
                            arma = (Arma)oggetto;
                        }else {
                            arma = (Arma)oggetto;
                        }
    
                        Messaggio.setMessaggio("HAI RACCOLTO '" + oggetto.getDescrizione() + "'");
                    } else {
                        Messaggio.setMessaggio("MIO CARO '" + this.getNome() + "' NON PUOI RACCOGLIERE L'OGGETTO... \nINVENTARIO PIENO");
                    }
                } else {
    
                    Messaggio.setMessaggio("MIO CARO '" + this.getNome() + "' NON PUOI RACCOGLIERE L'ARMA...  \nRAZZE INCOMPATIBILI");
                }
                Dungeon.setPosizioneMappa(new Posizione(oggetto.getRighe(), oggetto.getColonne()));
    
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
    
        //Metodo per passare oltre le porte (interfaccia grafica)
        private void goThroughGui(Porta porta, String direzione) {
            if(!porta.isBloccata()){
                        switch(direzione.toLowerCase()){
    
                            case "up":
                            case "u":
                                Dungeon.setPosizioneMappa(new Posizione(this.getRighe(), this.getColonne()));
    
                                /**--- OLTREPASSO LA PORTA **/
                                this.setRighe(this.getRighe()-2);

                                if(isNew){
                                    p2=Dungeon.getPosizione(this.getRighe(), this.getColonne());
                                    isNew=false;
                                    
                                } else {
                                    posizione=p2;
                                    p2=Dungeon.getPosizione(this.getRighe(), this.getColonne());
                                }
    
                                Dungeon.setPosizioneMappa(this);
                                break;
    
                            case "down":
                            case "d":
                                Dungeon.setPosizioneMappa(new Posizione(this.getRighe(), this.getColonne()));
    
                                /**--- OLTREPASSO LA PORTA **/
                                this.setRighe(this.getRighe()+2);

                                if(isNew){
                                    p2=Dungeon.getPosizione(this.getRighe(), this.getColonne());
                                    isNew=false;
                                } else {
                                    posizione=p2;
                                    p2=Dungeon.getPosizione(this.getRighe(), this.getColonne());
                                }

                                Dungeon.setPosizioneMappa(this);
                                break;
    
                            case "left":
                            case "l":
                                Dungeon.setPosizioneMappa(new Posizione(this.getRighe(), this.getColonne()));
    
                                /**--- OLTREPASSO LA PORTA **/
                                this.setColonne(this.getColonne()-2);

                                if(isNew){
                                    p2=Dungeon.getPosizione(this.getRighe(), this.getColonne());
                                    isNew=false;
                                } else {
                                    posizione=p2;
                                    p2=Dungeon.getPosizione(this.getRighe(), this.getColonne());
                                }
    
                                Dungeon.setPosizioneMappa(this);
                                break;
    
                            case "right":
                            case "r":
                                Dungeon.setPosizioneMappa(new Posizione(this.getRighe(), this.getColonne()));
    
                                /**--- OLTREPASSO LA PORTA **/
                                this.setColonne(this.getColonne()+2);

                                if(isNew){
                                    p2=Dungeon.getPosizione(this.getRighe(), this.getColonne());
                                    isNew=false;
                                } else {
                                    posizione=p2;
                                    p2=Dungeon.getPosizione(this.getRighe(), this.getColonne());
                                }
    
                                Dungeon.setPosizioneMappa(this);
                                break;
                        }
                    
                }else if(inventario.searchInInventory("CHIAVE")){
                    Messaggio.addMessaggio("PORTA BLOCCATA");
                    Messaggio.addMessaggio("VUOI SBLOCCARE LA PORTA?");
                }else {
                    Messaggio.addMessaggio("PORTA BLOCCATA");
                }
    
        }
    
        //Metodo per parlare con un png (interfaccia grafica)
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
    
        //Metodo per trovare la posizione utile attorno al giocatore
        public void around() throws ClassNotFoundException {
    
            if(Game.isTerminal()) {
                Dungeon.showDungeon();
            }
    
            findOggettiPng();
    
            /**--- UP DOOR---**/
            if (Dungeon.getMappa()[this.getRighe() - 1][this.getColonne()].getClass().getSimpleName().equals("Porta")) {
    
                goThrough((Porta) Dungeon.getMappa()[this.getRighe() - 1][this.getColonne()], "up");
            }else {
    
                /**--- DOWN DOOR---**/
                if (Dungeon.getClassPosizione(this.getRighe() + 1, this.getColonne()).getSimpleName().equals("Porta")) {
    
                    goThrough((Porta) Dungeon.getMappa()[this.getRighe() + 1][this.getColonne()], "down");
                }else
                /**--- LEFT DOOR---**/
                    if (Dungeon.getClassPosizione(this.getRighe(), this.getColonne() - 1).getSimpleName().equals("Porta")) {
    
                        goThrough((Porta) Dungeon.getMappa()[this.getRighe()][this.getColonne() - 1], "left");
                    }else
                    /**--- RIGHT DOOR---**/
                        if (Dungeon.getClassPosizione(this.getRighe(), this.getColonne() + 1).getSimpleName().equals("Porta")) {
    
                            goThrough((Porta) Dungeon.getMappa()[this.getRighe()][this.getColonne() + 1], "right");
                        }
    
            }
    
        }
        
        //Metodo per vedere se la posizione trovata è un oggetto o un png
        private void findOggettiPng() {
            /**--- UP ---**/
            Posizione p;
            if (!Dungeon.getMappa()[this.getRighe() - 1][this.getColonne()].isLibera()) {
    
                p = Dungeon.getMappa()[this.getRighe() - 1][this.getColonne()];
    
                switch(p.getClass().getSimpleName()){
                    case "Png":
                    case "Drago":
    
                        talk((Personaggio)p, "up");
                        break;
                    case "Oggetto":
                    case "Arma":
                    case "Armatura":
    
                        takeUp((Oggetto)p,"up");
                        break;
                }
    
            }
            /**--- DOWN ---**/
            if (!Dungeon.getMappa()[this.getRighe() + 1][this.getColonne()].isLibera()) {
    
                p = Dungeon.getMappa()[this.getRighe() + 1][this.getColonne()];
    
    
                switch (p.getClass().getSimpleName()) {
                    case "Png":
                    case "Drago":
    
                        talk((Personaggio)p, "down");
                        break;
                    case "Oggetto":
                    case "Arma":
                    case "Armatura":
    
                        takeUp((Oggetto)p,"down");
                        break;
                }
            }
    
            /**--- LEFT ---**/
            if (!Dungeon.getMappa()[this.getRighe()][this.getColonne() - 1 ].isLibera()) {
    
                p = Dungeon.getMappa()[this.getRighe()][this.getColonne() - 1];
    
                switch (p.getClass().getSimpleName()) {
                    case "Png":
                    case "Drago":
    
                        talk((Personaggio)p, "left");
                        break;
                    case "Oggetto":
                    case "Arma":
                    case "Armatura":
    
                        takeUp((Oggetto)p,"left");
                        break;
                }
            }
    
            /**--- RIGHT ---**/
            if (!Dungeon.getMappa()[this.getRighe()][this.getColonne() + 1 ].isLibera()) {
    
                p = Dungeon.getMappa()[this.getRighe()][this.getColonne() + 1];
                switch (p.getClass().getSimpleName()) {
                    case "Png":
                    case "Drago":
    
                        talk((Personaggio)p, "right");
                        break;
                    case "Oggetto":
                    case "Arma":
                    case "Armatura":
    
                        takeUp((Oggetto)p,"right");
                        break;
                }
            }
    
        }
    
        //Metodo per oltrepassare le porte
        private void goThrough(Porta p, String direzione) {
            
            System.out.print("vuoi oltrepassare la porta? " + direzione + " (yes/y - no/n) ");
    
                switch(Scn.getInstance().nextLine().toLowerCase()){
                    case "yes":
                    case "y":
                    if(!p.isBloccata()){
                        switch(direzione.toLowerCase()){
    
                            case "up":
                            case "u":
                                Dungeon.setPosizioneMappa(new Posizione(this.getRighe(), this.getColonne()));
    
                                /**--- OLTREPASSO LA PORTA **/
                                this.setRighe(this.getRighe()-2);

                                if(isNew){
                                    p2=Dungeon.getPosizione(this.getRighe(), this.getColonne());
                                    isNew=false;
                                    
                                } else {
                                    posizione=p2;
                                    p2=Dungeon.getPosizione(this.getRighe(), this.getColonne());
                                }
    
                                Dungeon.setPosizioneMappa(this);
                                break;
    
                            case "down":
                            case "d":
                                Dungeon.setPosizioneMappa(new Posizione(this.getRighe(), this.getColonne()));
    
                                /**--- OLTREPASSO LA PORTA **/
                                this.setRighe(this.getRighe()+2);

                                if(isNew){
                                    p2=Dungeon.getPosizione(this.getRighe(), this.getColonne());
                                    isNew=false;
                                    
                                } else {
                                    posizione=p2;
                                    p2=Dungeon.getPosizione(this.getRighe(), this.getColonne());
                                }
    
                                Dungeon.setPosizioneMappa(this);
                                break;
    
                            case "left":
                            case "l":
                                Dungeon.setPosizioneMappa(new Posizione(this.getRighe(), this.getColonne()));
    
                                /**--- OLTREPASSO LA PORTA **/
                                this.setColonne(this.getColonne()-2);

                                if(isNew){
                                    p2=Dungeon.getPosizione(this.getRighe(), this.getColonne());
                                    isNew=false;
                                    
                                } else {
                                    posizione=p2;
                                    p2=Dungeon.getPosizione(this.getRighe(), this.getColonne());
                                }
    
                                Dungeon.setPosizioneMappa(this);
                                break;
    
                            case "right":
                            case "r":
                                Dungeon.setPosizioneMappa(new Posizione(this.getRighe(), this.getColonne()));
    
                                /**--- OLTREPASSO LA PORTA **/
                                this.setColonne(this.getColonne()+2);

                                if(isNew){
                                    p2=Dungeon.getPosizione(this.getRighe(), this.getColonne());
                                    isNew=false;
                                    
                                } else {
                                    posizione=p2;
                                    p2=Dungeon.getPosizione(this.getRighe(), this.getColonne());
                                }
    
                                Dungeon.setPosizioneMappa(this);
                                break;
                        }
    
                        Dungeon.showDungeon();
                        findOggettiPng();
                    }else if(inventario.searchInInventory("CHIAVE")){

                        System.out.println("PORTA BLOCCATA");
                        System.out.println("VUOI SBLOCCARE LA PORTA? (yes/y - no/n)");

                        switch(Scn.getInstance().nextLine().toLowerCase()){
                            case "yes":
                            case "y":
                                p.setBloccata(false);
                                inventario.removeFromInventory("CHIAVE");
                                System.out.println("PORTA SBLOCCATA");
                                break;
                            case "no":
                            case "n":
                                break;
                            default:
                                goThrough(p, direzione);
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
                        goThrough(p, direzione);
                        break;
                }
    
            
        }
        
        //Metodo per raccogliere gli oggetti
        private void takeUp(Oggetto oggetto, String direzione){
    
            System.out.print("vuoi raccogliere l'oggetto? '" + direzione + "' (yes/y - no/n) ");
    
            switch(Scn.getInstance().nextLine().toLowerCase()){
                case "yes":
                case "y":
                    System.out.println("HAI TROVATO '" + oggetto.getDescrizione() + "' \n");
    
                    if(oggetto.getClass().getSimpleName().equals("Oggetto")){
                        if((oggetto.getPeso() + inventario.getPesoInventory()) <= inventario.getPesoMax()){
    
                            inventario.addToInventory( oggetto);
                            System.out.println("HAI RACCOLTO '" + oggetto.getDescrizione() + "'");
    
                        }else {
                            System.out.println("MIO CARO '" + this.getNome() + "' NON PUOI RACCOGLIERE L'OGGETTO... \nINVENTARIO PIENO");
                        }
                    }else if(((Arma) oggetto).getClasse() == null || ((Arma) oggetto).getClasse().equals(Giocatore.classe)) {
                        if (oggetto.getPeso() + inventario.getPesoInventory() <= inventario.getPesoMax()) {
                            inventario.addToInventory(oggetto);
    
                            /**--- POI SOSTITUITO DAL METODO EQUIPMENT ---**/
                            if(Giocatore.arma != null) {
    
                                System.out.println("ARMA TROVATA " + oggetto.getDescrizione() + ": DANNO d" + ((Arma)oggetto).getDado()
                                        + "\n" + "ARMA EQUIPAGGIATA " + Giocatore.arma.getDescrizione() + ": DANNO d" + Giocatore.arma.getDado());
    
                                System.out.println("VUOI RACCOGLIERE L'ARMA?");
    
                                switch (Scn.getInstance().nextLine().toLowerCase()) {
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
                            System.out.println("MIO CARO '" + this.getNome() + "' NON PUOI RACCOGLIERE L'OGGETTO... \nINVENTARIO PIENO");
                        }
                    }else {
    
                        System.out.println("MIO CARO '" + this.getNome() + "' NON PUOI RACCOGLIERE L'ARMA... \nRAZZE INCOMPATIBILI");
                    }
                    Dungeon.setPosizioneMappa(new Posizione(oggetto.getRighe(), oggetto.getColonne()));
                    Dungeon.showDungeon();
    
                    break;
                case "no":
                case "n":
    
                    System.out.println("non hai raccolto l'oggetto " + oggetto.getDescrizione());
                    break;
                default:
                    takeUp(oggetto, direzione);
                    break;
            }
    
            /**--- SE L'OGGETTO E' UN ARMA LA METTO NELLE ARMI ---**/
            /*if(oggetto.getClass() == Arma.class){
                this.armi.addToInventory( oggetto);
            }*/
        }
    
        //Metodo per parlare con i png
        private void talk(Personaggio personaggio, String direzione) {
    
            this.nemico = null;
    
            if(personaggio.getClass().getSimpleName().equals("Png"))  this.nemico = personaggio;
    
            System.out.print("vuoi parlare con  '" + personaggio.getNome() + "' ? '" + direzione + "' (yes/y - no/n) ");
    
            switch(Scn.getInstance().nextLine().toLowerCase()){
                case "yes":
                case "y":
    
                    if(this.nemico != null)
                        System.out.println(((Png)this.nemico).getDialogo());
                    else
                        System.out.println(Drago.getDialogo());
    
    
                    if(this.nemico != null && !((Png)this.nemico).isOstile()) {
                        System.out.println("HAI PARLATO CON '" + personaggio.getNome() + "'");
    
                    }else{
                        int turnoGiocatore = RandomUtils.rollD20();
                        int turnoPng = RandomUtils.rollD20();
    
                        enemieInteractions(personaggio, direzione, turnoGiocatore, turnoPng);
    
                    }
    
                    break;
                case "no":
                case "n":
    
                    System.out.println("non hai parlato con " + personaggio.getNome());
                    break;
                default:
                    talk(personaggio, direzione);
                    break;
            }
        }
        
        //Metodo per decidere cosa fare contro un png ostile
        private void enemieInteractions(Personaggio personaggio, String direzione, int turnoGiocatore, int turnoPng) {
    
            System.out.println("SCEGLI L'AZIONE ( COMBATTERE/C - FUGGIRE/F - STATS/S) ");
            switch (Scn.getInstance().nextLine().toLowerCase()){
                case "combattere":
                case "c":
    
    
                    if(Giocatore.getArma() != null){
    
                        /**--- FIGHT FINCHE' SONO VIVI ---**/
                        while(personaggio.isVivo() && this.isVivo()) {
                            this.fight(personaggio, turnoGiocatore, turnoPng);
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
                    enemieInteractions(personaggio, direzione, turnoGiocatore, turnoPng);
                    break;
            }
        }
    
        //Metodo per decidere cosa fare durante il combattimento
        public void fight(Personaggio personaggio, int turnoGiocatore, int turnoPng){
    
            System.out.println("SCEGLI L'AZIONE ( ATTACCARE/A - CURA/C - STATS/S) ");
    
            switch(Scn.getInstance().nextLine().toLowerCase()){
    
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
                            this.fight(personaggio, turnoGiocatore, turnoPng);
                        }
    
                    System.out.println(Messaggio.getMessaggio());
                        Messaggio.clearMesaggio();
    
    
                    break;
                case "stats":
                case "s":
    
                    this.showStats();
                    personaggio.showStats();
    
                    this.fight(personaggio, turnoGiocatore, turnoPng);
    
                    break;
    
                default:
                    this.fight(personaggio, turnoGiocatore, turnoPng);
                    break;
            }
    
        }
        
        //Metodo per gestione combattimento
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
                inventario.showInventory();/**--- STATIC O NON MOSTRIAMOLO ---**/
                //Runtime.getRuntime().exit(404);
    
            }/**--- SE IL PNG MUORE LASCIA L'ORO ---**/
            else if(!personaggio.isVivo()) {
    
                Dungeon.setPosizioneMappa(new Posizione(personaggio.getRighe(), personaggio.getColonne()));
    
                this.takeGold(personaggio);
    
                Messaggio.addMessaggio("MIO PRODE AVVENTURIERO '" + this.getNome() + "' HAI SCONFITTO '" + personaggio.getNome() + "'");
                if(Game.isTerminal()) {
                    Dungeon.showDungeon();
                }
            }
        }

    public Posizione getBackRoom(){
        Dungeon.setPosizioneMappa(new Posizione(p2.getRighe(), p2.getColonne()));
        return posizione;
    }
    
    /**---- UTIL METHODS: GETTER ----**/
    
    //Metodo per prendere i punti esperienza del giocatore
    public int getPuntiEscperienza() {return puntiEsperienza;}
    
    //Metodo per prendere la classe del giocatore
    public static Classe getClasse() {return classe;}

    //Metodo per prendere la razza del giocatore
    public static Razza getRazza() {return razza;}

    //Metodo per prendere l'arma con cui attacca il giocatore
    public static Arma getArma(){return arma;} /**--- STATIC: USATO IN UNA SOPRACLASSE --**/

    //Metodo per prendere l'inventario del giocatore
    public static Inventario getInventory() {return inventario;}

    //Metodo per prendere il nemmico del personaggio
    public Personaggio getNemico() {return nemico;}
    
    //metodo per prendere le posizionio già visitate (interfaccia grafica)
    public ArrayList<Posizione> getPosizioniTrovate() {return posizioniTrovate;}

}
