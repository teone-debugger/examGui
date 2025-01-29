package interfaccia.FrameMenu;

import game.character.enemies.Drago;
import game.Dungeon;
import game.Game;
import interfaccia.framesGame.FrameGame;
import util.StringUtils;
import interfaccia.frameBlocks.*;
import game.character.player.Giocatore;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class FrameSelection extends interfaccia.Frame{

    private JLabel labelNome;
    private JTextField textNome;

    private JRadioButton umano;
    private JRadioButton elfo;
    private JRadioButton nano;

    private JRadioButton mago;
    private JRadioButton barbaro;
    private JRadioButton ladro;

    private ButtonGroup bgRazza;
    private ButtonGroup bgClasse;

    private PannelloTitled pannelloRazza;
    private PannelloTitled pannelloClasse;
    private PannelloTitled pannelloNome;

    private JButton start;

    private String classe;
    private String razza;
    private String nome;


    public FrameSelection() {
        super("SELEZIONA PERSONAGGIO");

        /**--- RADIO GROUP RAZZA ---**/
        nano = creationRadioButton("NANO");
        umano = creationRadioButton("UMANO");
        elfo = creationRadioButton("ELFO");

        bgRazza = new ButtonGroup();
        bgRazza.add(nano);
        bgRazza.add(umano);
        bgRazza.add(elfo);
        bgRazza.setSelected(nano.getModel(), true);

        /**--- RADIO GROUP CLASSE ---**/
        mago = creationRadioButton("MAGO");
        barbaro = creationRadioButton("BARBARO");
        ladro = creationRadioButton("LADRO");

        bgClasse = new ButtonGroup();
        bgClasse.add(mago);
        bgClasse.add(barbaro);
        bgClasse.add(ladro);

        bgClasse.setSelected(barbaro.getModel(), true);


        /**--- COMPONENTI NOME ---**/
        textNome = new JTextField(15);
        //textNome.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 20));
        textNome.setFont(StringUtils.getAlagardFont(17f));
        textNome.setToolTipText("NOME GIOCATORE");

        /**--- SCRIVE LE LETTERE SEMPRE IN MAIUSCOLO ---**/
        textNome.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char keyChar = e.getKeyChar();
                if (Character.isLowerCase(keyChar)) {
                    e.setKeyChar(Character.toUpperCase(keyChar));
                }
            }
        });
        labelNome = new JLabel("NOME");


        /**--- PENNELLI ---**/

        pannelloRazza = new PannelloTitled("RAZZA");
        pannelloRazza.setColor(Color.WHITE, Color.BLACK);
        pannelloRazza.dimension(new Dimension(getWidthStandard(), getHeightStandard()));

        pannelloRazza.add(nano, BorderLayout.NORTH);
        pannelloRazza.add(umano, BorderLayout.CENTER);
        pannelloRazza.add(elfo, BorderLayout.SOUTH);

        pannelloClasse = new PannelloTitled("CLASSE");
        pannelloClasse.setColor(Color.WHITE, Color.BLACK);
        pannelloClasse.dimension(new Dimension(getWidthStandard(), getHeightStandard()));

        pannelloClasse.add(mago, BorderLayout.NORTH);
        pannelloClasse.add(barbaro, BorderLayout.CENTER);
        pannelloClasse.add(ladro, BorderLayout.SOUTH);

        pannelloNome = new PannelloTitled("NOME");
        pannelloNome.add(labelNome, BorderLayout.WEST);
        pannelloNome.add(textNome, BorderLayout.CENTER);

        start = new JButton("PARTI ALLA VOLTA DEL DUNGEON!");
        start.setFont(StringUtils.getAlagardFont(18f));
        start.setMargin(new Insets(10,10,10,10));
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                razza = bgRazza.getSelection().getActionCommand();
                classe = bgClasse.getSelection().getActionCommand();
                if(textNome.getText().equals("")){
                    nome = "HAL";
                }else {
                    nome = textNome.getText();
                }

                Giocatore g = Game.createGiocatoreGui(razza, classe, nome);

                new FrameGame(g);
                FrameSelection.this.dispose();
            }
        });

        /**--- INSERIMENTO PANNELLO RAZZA CON BOTTONI ---**/
        insertComponent(0,0,0.01, 0.01, 2,1, GridBagConstraints.CENTER, pannelloNome);

        /**--- INSERIMENTO PANNELLO RAZZA CON BOTTONI ---**/
        insertComponent(0,1,0.01, 0.01, 1,1, GridBagConstraints.CENTER, pannelloRazza);

        /**--- INSERIMENTO PANNELLO CLASSE CON BOTTONI ---**/
        insertComponent(1,1,0.01, 0.01, 1,1, GridBagConstraints.CENTER, pannelloClasse);

        /**--- INSERIMENTO BUTTON START ---**/
        insertComponent(0,2,0.01, 0.01, 2,1, GridBagConstraints.CENTER, start);


        settings();

    }

    private JRadioButton creationRadioButton(String title){
        JRadioButton button = new JRadioButton(title);
        button.setActionCommand(title);
        //button.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 20));
        button.setFont(StringUtils.getAlagardFont(18f));
        return button;
    }
}

