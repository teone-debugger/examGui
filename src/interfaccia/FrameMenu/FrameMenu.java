package interfaccia.FrameMenu;

import javax.swing.JButton;

import interfaccia.frameBlocks.PannelloTitled;
import util.StringUtils;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrameMenu extends interfaccia.Frame{
    

    private PannelloTitled pannelloMenu;


    private JButton newGame;
    private JButton loadGAme;

    //Metodo Costruttore
    public FrameMenu(){
        super("MENU");

        pannelloMenu = new PannelloTitled("MENU'");

        newGame = new JButton("NUOVA PARTITA");
        //setupButton(newGame, 50, 500, 200, 50);
        newGame.setFont(StringUtils.getAlagardFont(18f));

        newGame.setBounds(50, 500, 200, 50);
        newGame.setMargin(new Insets(10,10,10,10));
        newGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                new FrameSelection();
                FrameMenu.this.dispose();
            }
        });

        loadGAme = new JButton("CARICA PARTITA");
        //setupButton(loadGAme, 50, 500, 200, 50);
        loadGAme.setBounds(250, 500, 200, 50);

        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        setVisible(true);
        setAlwaysOnTop(true);
        requestFocus();

        add(loadGAme);
        add(newGame);

    }

    private void setupButton(JButton button, int x, int y, Font font){
        button.setBounds(x, y, 200, 50);
        //button.setFont(font);
        button.setFocusPainted(false);
        add(button);
    }


}
