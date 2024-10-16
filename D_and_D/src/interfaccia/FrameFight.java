package interfaccia;

import gioco.Dungeon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrameFight extends Frame{

    private JButton attacca;
    private JButton cura;
    private JButton fuga;

    private PannelloTitled azioni;


    public FrameFight() {
        super("COMBATTI!!!");

        azioni = new PannelloTitled("SCEGLI L'AZIONE");
        azioni.dimension(new Dimension(getWidthStandard(), getHeightStandard()));

        int margine = 3 * getHeight() / 100;
        
        cura = new JButton("CURA");
            cura.setSize(getWidth() * 20 /100, getHeight() * 20 /100);

        cura.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(Dungeon.getGiocatore().heal()) {

                    //GIOCATORE
                    //png.attack(this);
                    Dungeon.getGiocatore().getNemico().attack(Dungeon.getGiocatore());
                }
            }
        });
            //cura.setMargin(new Insets(10,10,10,10));
        fuga = new JButton("FUGA");
            fuga.setSize(getWidth() * 20 /100, getHeight() * 20 /100);
            fuga.setMargin(new Insets(margine,margine,margine,margine));

        fuga.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        attacca = new JButton("ATTACCA");
            attacca.setSize(getWidth() * 20 /100, getHeight() * 20 /100);
            attacca.setMargin(new Insets(margine,margine,margine,margine));

        attacca.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int turnoGiocatore = (int)(Math.random() * 20 + 1);
                int turnoPng = (int)(Math.random() * 20 + 1);

                Dungeon.getGiocatore().fightDinamic(Dungeon.getGiocatore().getNemico(), turnoGiocatore, turnoPng);

                // ALLA MORTE DI UNO DEI DUE SCMPARE
                // OGNI ATTACCO LE STST VARIANO
            }
        });

        azioni.add(attacca, BorderLayout.NORTH);
        azioni.add(cura, BorderLayout.CENTER);
        azioni.add(fuga, BorderLayout.SOUTH);

        insertComponent(0,0, 0.01, 0.01,1,1, GridBagConstraints.CENTER, azioni);



        settings();
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);


    }
}
