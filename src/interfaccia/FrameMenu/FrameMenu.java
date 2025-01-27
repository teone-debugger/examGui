package interfaccia.FrameMenu;

import interfaccia.frameBlocks.PannelloTitled;
import util.StringUtils;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FrameMenu extends interfaccia.Frame{
    

    private PannelloTitled pannelloMenu;


    //private JButton newGame;
    //private JButton loadGAme;
    private JLabel newGamLabel;
    private JLabel loadGamLabel;
    private ImageIcon titleIcon = interfaccia.framesFight.FrameFight.scaleImage("resources/images/logo/logo_Pixel.png", 400, 300);
    private ImageIcon backgroundTitle;
    private JLabel titleBackground;
    private JLabel titleIconJLabel = new JLabel(titleIcon);

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
                //aggiungere codice per caricare partita
            }
        });

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
