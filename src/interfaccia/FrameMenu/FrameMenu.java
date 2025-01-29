package interfaccia.FrameMenu;

import interfaccia.frameBlocks.PannelloTitled;
import util.StringUtils;
import saveManager.*;
import interfaccia.framesGame.*;
import game.Dungeon;


import javax.swing.*;

import com.google.api.client.json.Json;

import game.character.player.Giocatore;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FrameMenu extends interfaccia.Frame{
    
    private PannelloTitled pannelloMenu;

    private JLabel newGamLabel;
    private JLabel loadGamLabel;
    private ImageIcon titleIcon = interfaccia.framesFight.FrameFight.scaleImage("resources/images/logo/logo_Pixel.png", 400, 300);
    private ImageIcon backgroundTitle;
    private JLabel titleBackground;
    private JLabel titleIconJLabel = new JLabel(titleIcon);
    private ImageIcon outlineNewGame = interfaccia.framesFight.FrameFight.scaleImage("resources/images/outline/outline nuova partita.png", 200, 50);
    private JLabel outlineNewGamLabel= new JLabel(outlineNewGame);
    private ImageIcon outlineLoadGame = interfaccia.framesFight.FrameFight.scaleImage("resources/images/outline/carica partita outline.png", 200, 50);
    private JLabel outlineLoadGamLabel= new JLabel(outlineLoadGame);

    //Metodo Costruttore
    public FrameMenu(){
        super("MENU");

        pannelloMenu = new PannelloTitled("MENU'");

        backgroundTitle = interfaccia.framesFight.FrameFight.scaleImage("resources/images/background/TitleScreenBackground.jpg", 800, 600);
        titleBackground = new JLabel(backgroundTitle);
        titleBackground.setBounds(0, 0, 800, 600);
        titleIconJLabel.setBounds(380, 0, 400, 300);

        newGamLabel = createClickableLabel("NUOVA PARTITA", 580, 350);
        newGamLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new FrameSelection();
                FrameMenu.this.dispose();
            }
        });

        loadGamLabel = createClickableLabel("CARICA PARTITA", 580, 400);
        loadGamLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try{

                    //scarica da firebase il file e lo carica, nel caso non riesca a scaricarlo parte una nuova partita
                    /*FirebaseInitializer fb = new FirebaseInitializer();
                    if(fb.downloadFromCloud("save.json"," resources/firebase/savesLogs/")){
                        fb.deleteFromCloud("save.json");
                        Giocatore g = JsonSaving.loadFromFile("resources/firebase/savesLogs/save.json");
                        new FrameGame(g);
                    }else{
                        JOptionPane.showMessageDialog(null, "NON CI SONO PARTITE SALVATE", "ERRORE", JOptionPane.ERROR_MESSAGE);
                        new FrameSelection();
                    }*/
                    Giocatore g = JsonSaving.loadFromFile("resources/firebase/savesLogs/save.json");
                    new FrameGame(g);
                    FrameMenu.this.dispose();
                }catch (Exception ex){
                    ex.printStackTrace();
                }

            }
        });

        outlineNewGamLabel.setBounds(580, 350, 200, 50);
        outlineLoadGamLabel.setBounds(580, 400, 200, 50);

        // Configurazione Frame
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        setVisible(true);
        setAlwaysOnTop(true);
        requestFocus();

        //aggiunta componenti in ordine
        add(newGamLabel);
        add(loadGamLabel);
        add(outlineNewGamLabel);
        add(outlineLoadGamLabel);

        add(titleIconJLabel);

        add(titleBackground);

    }

    private JLabel createClickableLabel(String testo, int x, int y) {
        JLabel label = new JLabel(testo, SwingConstants.CENTER);
        label.setBounds(x, y, 200, 50);
        label.setFont(StringUtils.getAlagardFont(20f));
        label.setForeground(new Color(255, 255, 255, 153));
        label.setCursor(new Cursor(Cursor.HAND_CURSOR));

        //aggiunta effetti di hover e di click
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                label.setForeground(new Color(255, 255, 255));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                label.setForeground(new Color(255, 255, 255, 153));
            }

            @Override
            public void mouseClicked(MouseEvent e){
                label.setForeground(Color.YELLOW);
            }
        });

        return label;
    }


}
