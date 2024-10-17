package interfaccia;

import gioco.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FrameGame extends Frame{

    private static Posizione posizione;
    private static Posizione oldPosizione;

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

        if(!Game.isWin()) {

            moveDirection();
        }


    }

    private void moveDirection() {
        mappa.getJtextArea().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
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
                mappa.setText(Dungeon.dungeonToString());
                posizione = Dungeon.getGiocatore().aroundGui();

                /**--- EVITO I DOPPI DIALOGHI CON LO STESSO PNG ---**/
                //oldPosizione = posizione;
                mappa.getJtextArea().requestFocus();
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

        /**--- FACILE INDENTIFICAZIONE PERSONAGGIO ---**/
        for (int i = 0; i < mappa.getJtextArea().getText().length(); i++) {

            if(mappa.getJtextArea().getText().charAt(i) == '@') {

                mappa.getJtextArea().setCaretColor(Color.RED);
                SwingUtilities.updateComponentTreeUI(this);
            }

        }
        mappa.dimension(new Dimension(widthMappa, heightMappa));
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
        inventario.setText(Giocatore.inventarioToString());
        inventario.dimension(new Dimension(getWidthStandard(), getHeightStandard()));

        /**--- CREAZIONE BARRA STRUMENTI ---**/
        barraStrumenti = new BarraStrumenti();
        barraStrumenti.dimension(new Dimension(getWidthStandard(), getHeightStandard()));
        barraStrumenti.setColor(Color.WHITE, Color.BLACK);
        barraStrumenti.setFormListener(new FormListener() {
            @Override
            public void formEvent(FormEvent fe) {
                if(fe.getJButton().equals(barraStrumenti.getAffermativo())){


                    if(FrameGame.posizione != null) {

                        if(posizione.getClass().getSimpleName().equals("Png")) {
                            oldPosizione = posizione;
                            //posizione = null;



                        }
                        if(FrameGame.posizione != FrameGame.oldPosizione) {
                            FrameGame.posizione = Dungeon.getGiocatore().aroundGui();
                        }
                        Dungeon.getGiocatore().takeUpGui(FrameGame.posizione);
                        inventario.setText(Giocatore.inventarioToString());
                        mappa.setText(Dungeon.dungeonToString());
                        oldPosizione = posizione;


                    }
                    mappa.getJtextArea().requestFocus();

                }
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


        /**--- INSERIMENTO PANNELLO: inventario ---**/
        insertComponent(1,0, 0.01, 0.01,1,1, GridBagConstraints.PAGE_START, inventario);
        /**--- INSERIMENTO PANNELLO: barraStrumenti ---**/
        insertComponent(1,1, 0.01, 0.01, 1, 1, GridBagConstraints.CENTER, barraStrumenti);
        /**--- INSERIMENTO PANNELLO: messaggi ---**/
        insertComponent(1,2, 0.01, 0.01, 1, 1, GridBagConstraints.PAGE_END, messaggi);
    }

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
