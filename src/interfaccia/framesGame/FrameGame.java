package interfaccia.framesGame;

import game.Posizione;
import messaggi.Messaggio;
import game.Game;
import game.Dungeon;
import game.room.*;
import interfaccia.frameBlocks.*;
import interfaccia.multimedia.FormEvent;
import interfaccia.multimedia.FormListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import interfaccia.Frame;
import game.items.view.*;
import game.character.player.Giocatore;

public class FrameGame extends Frame{

    private static Posizione posizione;
    //private static Posizione oldPosizione;

    private PannelloText mappa;
    private static PannelloTitled inventario;
    private static PannelloTitled statistiche;
    private static PannelloTitled messaggi;

    private int widthMappa, heightMappa;

    private BarraStrumenti barraStrumenti;

    public FrameGame() {
        super("D&D");


        widthMappa = getWidth() * 58 /100;
        heightMappa = getHeight() * 60 /100;

        creazioneLatoSX();
        creazioneLatoDX();
        settings();
        /**--- AUTO FOCUS SULLA JTEXT AREA PER MUOVERSI DALL'INIZIO ---**/
        this.addWindowListener(new WindowAdapter() {
            public void windowActivated(WindowEvent e) {
                mappa.getJtextArea().requestFocusInWindow();

                /**--- AGGIORNAMENTO FRAME ---**/
                // SwingUtilities.updateComponentTreeUI(FrameGame.this);
            }
        });

        moveDirection();
        setResizable(true);

    }

    private void moveDirection() {
        mappa.getJtextArea().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(!Game.isWin()){
                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_UP:

                            messaggi.setText("");
                            Dungeon.getGiocatore().move("up", Game.getScn());
                            break;
                        case KeyEvent.VK_DOWN:

                            messaggi.setText("");
                            Dungeon.getGiocatore().move("down", Game.getScn());

                            break;
                        case KeyEvent.VK_LEFT:

                            messaggi.setText("");
                            Dungeon.getGiocatore().move("left", Game.getScn());

                            break;
                        case KeyEvent.VK_RIGHT:

                            messaggi.setText("");
                            Dungeon.getGiocatore().move("right", Game.getScn());

                            break;
                        default:
                            messaggi.setText("MOSSA NON VALIDA!!!");
                            break;
                    }
                    barraStrumenti.setClicked(false);

                    Dungeon.getGiocatore().getPosizioniTrovate().clear();

                    Messaggio.clearMesaggio();
                    mappa.setText(Dungeon.dungeonToString());
                    posizione = Dungeon.getGiocatore().aroundGui();
                    //posizioniTrovate.add(posizione);

                    mappa.getJtextArea().requestFocus();


                    /**--- MOSTRO LE STATS DELLE DUE ARMI ---**/
                    if(posizione != null && posizione.getClass().getSimpleName().equals("Arma")) {

                        if (Giocatore.getArma() != null) {

                            Messaggio.addMessaggio("ARMA TROVATA " + ((Arma) posizione).getDescrizione() + ": DANNO d" + ((Arma) posizione).getDado()
                                    + "\n" + "ARMA EQUIPAGGIATA " + Giocatore.getArma().getDescrizione() + ": DANNO d" + Giocatore.getArma().getDado());
                        } else {
                            Messaggio.addMessaggio("ARMA TROVATA " + ((Arma) posizione).getDescrizione() + ": DANNO d" + ((Arma) posizione).getDado());

                        }
                    }
                    messaggi.setText(Messaggio.getMessaggio());
                }else{
                    mappa.setText("HAI VINTO!!!");
                }

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

    }

    private void creazioneLatoSX() {
        /**--- PARTE SX ---**/

        /**--- CREAZIONE PANNELLO MAPPA ---**/
        mappa = new PannelloText();
        mappa.setColor(Color.BLACK, Color.GREEN);

        System.out.println(widthMappa);
        mappa.dimension(new Dimension(widthMappa, heightMappa));
        /*mappa.getJtextArea().setRows(Dungeon.getRighe());
        mappa.getJtextArea().setColumns(Dungeon.getColonne());*/
        mappa.setText(Dungeon.dungeonToString());


        /**--- CREAZIONE PANNELLO STATISTICHE ---**/
        statistiche = new PannelloTitled("STATISTICHE");
        statistiche.setColor(Color.WHITE, Color.BLACK);
        statistiche.setText(Giocatore.statsToString(Dungeon.getGiocatore()));
        statistiche.dimension(new Dimension(getWidthStandard(), getHeightStandard()));

        /**--- INSERIMENTO PANNELLO: mappa ---**/
        insertComponent(0,0, 0.01, 0.01,1, 2, GridBagConstraints.FIRST_LINE_START, mappa);
        /**--- INSERIMENTO PANNELLO: statistiche ---**/
        insertComponent(0,2, 0.01, 0.01, 1, 1, GridBagConstraints.LAST_LINE_START, statistiche);

    }

    private void creazioneLatoDX() {
        /**--- PARTE DX ---**/


        /**--- CREAZIONE PANNELLO INVENTARIO ---**/
        inventario = new PannelloTitled("INVENTARIO");
        inventario.setColor(Color.WHITE, Color.BLACK);
        inventario.getJtextArea().setCaretColor(Color.BLUE);
        inventario.setText(Giocatore.getInventory().inventoryToString());
        inventario.dimension(new Dimension(getWidthStandard(), getHeightStandard()));

        inventario.getJtextArea().setRows(20);
        inventario.getJtextArea().setColumns(20);

        /**--- CREAZIONE BARRA STRUMENTI ---**/
        barraStrumenti = new BarraStrumenti();
        barraStrumenti.dimension(new Dimension(getWidthStandard(), getHeightStandard()));
        barraStrumenti.setColor(Color.WHITE, Color.BLACK);
        barraStrumenti.setFormListener(new FormListener() {
            @Override
            public void formEvent(FormEvent fe) {
                if(fe.getJButton().equals(barraStrumenti.getAffermativo())){


                    if(posizione != null) {
                        //messaggi.clearText();
                        Dungeon.getGiocatore().takeUpGui(posizione);
                        messaggi.setText(Messaggio.getMessaggio());
                        Messaggio.clearMesaggio();
                        inventario.setText(Giocatore.getInventory().inventoryToString());
                        

                        if(posizione.getClass().getSimpleName().equals("Porta") ) {
                            
                            if(((Porta) posizione).isBloccata()) {

                                
                                /**--- SE E' UNA PORTA BLOCCATA LA SBLOCCO ---**/
                                if(Giocatore.getInventory().searchInInventory("CHIAVE") ) {
                                    if(barraStrumenti.isClicked()){
                                        Giocatore.getInventory().removeFromInventory("CHIAVE");

                                        System.out.println("CHIAVE RIMOSSA DALL'INVENTARIO");

                                        barraStrumenti.setClicked(false);
                                        ((Porta) posizione).setBloccata(false);
                                        mappa.setText(Dungeon.dungeonToString());
                                        messaggi.setText("PORTA SBLOCCATA!!!");
                                    }else{
                                        barraStrumenti.setClicked(true);
                                    }
                                }
                                
                            }
                            

                        }else{
                            //Messaggio.clearMesaggio();
                            

                            posizione = Dungeon.getGiocatore().aroundGui();
                            //System.out.println("POSIZIONE: " + posizione.getClass());

                            if(posizione!= null && !posizione.getClass().getSimpleName().equals("Porta")){
                                messaggi.setText(Messaggio.getMessaggio());
                            }
                        }
                        mappa.setText(Dungeon.dungeonToString());
                            //messaggi.setText(Messaggio.getMessaggio());
                            
                            
                            

                            //Messaggio.clearMesaggio();
                            /*mappa.setText(Dungeon.dungeonToString());

                            posizione = Dungeon.getGiocatore().aroundGui();
                            //System.out.println("POSIZIONE: " + posizione.getClass());

                            if(posizione!= null && !posizione.getClass().getSimpleName().equals("Porta")){
                                messaggi.setText(Messaggio.getMessaggio());
                            }*/
                        
                    }


                }else if(fe.getJButton().equals(barraStrumenti.getNegativo())){

                    if(posizione != null) {

                        if(posizione.getClass().getSimpleName().equals("Porta")){
                                
                            if(((Porta) posizione).isBloccata() && Giocatore.getInventory().searchInInventory("CHIAVE")) {

                                barraStrumenti.setClicked(false);
                                messaggi.setText("NON HAI SBLOCCATO LA PORTA");
                            }
                        }else{
                            /**--- CAMBIO INTERAZIONE ---**/
                        
                            posizione = Dungeon.getGiocatore().aroundGui();
                            messaggi.setText(Messaggio.getMessaggio());
                        }
                    }

                }else if(fe.getJButton().equals(barraStrumenti.getCura())) {

                    Dungeon.getGiocatore().heal();
                    FrameGame.getStatistiche().setText(Giocatore.statsToString(Dungeon.getGiocatore()));
                    FrameGame.getMessaggi().setText(Messaggio.getMessaggio());
                    Messaggio.clearMesaggio();
                }


                mappa.getJtextArea().requestFocus();
                mappa.getJtextArea().requestFocus();
                SwingUtilities.updateComponentTreeUI(FrameGame.this);

            }
        });

        /*{barraStrumenti.setTextListener(new TextListener(){

            @Override
            public void printText(String testo) {
                if(barraStrumenti.getPremuto().equals(barraStrumenti.getAffermativo())) {

                    inventario.setTextLable(testo);

                }else if (barraStrumenti.getPremuto().equals(barraStrumenti.getNegativo())){

                    statistiche.setText(testo);
                }

            }
        });}*/

        /**--- CREAZIONE PANNELLO MESSAGGI ---**/
        messaggi = new PannelloTitled("MESSAGGI");
        messaggi.setColor(Color.WHITE, Color.BLACK);
        messaggi.dimension(new Dimension(getWidthStandard(), getHeightStandard()));
        messaggi.getJtextArea().setColumns(20);


        /**--- INSERIMENTO PANNELLO: inventario ---**/
        insertComponent(1,0, 0.01, 0.01,1,1, GridBagConstraints.PAGE_START, inventario);
        /**--- INSERIMENTO PANNELLO: barraStrumenti ---**/
        insertComponent(1,1, 0.01, 0.01, 1, 1, GridBagConstraints.CENTER, barraStrumenti);
        /**--- INSERIMENTO PANNELLO: messaggi ---**/
        insertComponent(1,2, 0.01, 0.01, 1, 1, GridBagConstraints.PAGE_END, messaggi);
    }

    /**--- CONTROLLA LE POSIZIONE TRA LE POSIZIONI TROVATE ---**/
    /*private boolean isFind(Posizione posizione){

        for (Posizione value : posizioniTrovate) {
            if (value.equals(posizione)) {
                return true;
            }
        }
        return false;
    }*/

    public PannelloText getMappa() {
        return mappa;
    }

    public static PannelloTitled getStatistiche() {
        return statistiche;
    }

    public static PannelloTitled getMessaggi() {
        return messaggi;
    }

    public static PannelloTitled getInventario() {
        return inventario;
    }
}
