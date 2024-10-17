package interfaccia;

import gioco.Dungeon;
import gioco.Giocatore;
import messaggi.Messaggio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrameFight extends Frame{

    private JButton attacca;
    private JButton cura;
    private JButton fuga;

    private PannelloTitled azioni;

    private int turnoGiocatore, turnoPng;

    public FrameFight() {
        super("COMBATTI!!!");

        turnoGiocatore = (int) (Math.random() * 20 + 1);
        turnoPng = (int) (Math.random() * 20 + 1);
        int margine = 3 * getHeight() / 100;


        azioni = new PannelloTitled("SCEGLI L'AZIONE");
        azioni.dimension(new Dimension(getWidthStandard(), getHeightStandard()));
        
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
                setVisible(false);
            }
        });
        attacca = new JButton("ATTACCA");
            attacca.setSize(getWidth() * 20 /100, getHeight() * 20 /100);
            attacca.setMargin(new Insets(margine,margine,margine,margine));

        attacca.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrameGame.getMessaggi().setText("");
                actionAttacca();
                Messaggio.setMessaggio("");
            }
        });

        azioni.add(attacca, BorderLayout.NORTH);
        azioni.add(cura, BorderLayout.CENTER);
        azioni.add(fuga, BorderLayout.SOUTH);

        insertComponent(0,0, 0.01, 0.01,1,1, GridBagConstraints.CENTER, azioni);



        settings();
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);


    }

    private void actionAttacca(){

        /**--- ATTACCA SE HO UN'ARMA ---**/
        if(Giocatore.getArma() != null) {

            Dungeon.getGiocatore().fightDinamic(Dungeon.getGiocatore().getNemico(), turnoGiocatore, turnoPng);
            FrameGame.getMessaggi().setText(Messaggio.getMessaggio());
        }else{

            setVisible(false);
            FrameGame.getMessaggi().setText("NON PUOI COMBATTERE " + Dungeon.getGiocatore().getNemico().getNome() + " NON HAI UN ARMA");
        }

        /**--- ALLA MORTE DI UNO DEI DUE SCOMPARE LA FINESTRA ---**/
        if(!Dungeon.getGiocatore().isVivo()){

            FrameGame.getMessaggi().setText("MI DISPIACE MIO PRODE AVVENTURIERO " + Dungeon.getGiocatore().getNome() + " SEI MORTO IN QUEST'AVVENTURA");
            setVisible(false);
        }
        if(!Dungeon.getGiocatore().getNemico().isVivo()){

            FrameGame.getMessaggi().setText("MIO PRODE AVVENTURIERO " + Dungeon.getGiocatore().getNome() + " HAI SCONFITTO " + Dungeon.getGiocatore().getNemico().getNome());
            setVisible(false);
        }

        /**--- CAMBIO LE STATS SULLA FINESTRA PRINCIPALE (OPZIONALE NON BELLISSIMO VEDI TU GIAN :) ) ---**/
        FrameGame.getStatistiche().setText(Giocatore.statsToString(Dungeon.getGiocatore()) + "\n" + Giocatore.statsToString(Dungeon.getGiocatore().getNemico()));
    }
}
