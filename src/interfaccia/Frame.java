package interfaccia;

import gioco.Dungeon;


import javax.swing.*;
import java.awt.*;


public class Frame extends JFrame {


    private GridBagConstraints gbc;

    private int width,height;
    private int widthStandard, heightStandard;

    public Frame(String titolo) {
        super(titolo);

        width = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        height = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();

        widthStandard = width * 38 / 100;
        heightStandard = height * 30 / 100;

        setLayout(new GridBagLayout());

    /**--- SETTING GRID BAG CONSTRAINTS ---**/
        gbc = new GridBagConstraints();

    }

    public void settings(){
        /**-- DIMENSIONI FRAME AUTOMATICHE --**/
        pack();

        /**--- METTO IN MEZZO E NON RIDIMENSIONABILE ---**/
        setLocationRelativeTo(null);
        setResizable(false);
        /**--- PROGRAMMA FINISCE SU FINESTRA CHIUSA ---**/
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /**--- FINESTRA VISIBILE ---**/
        setVisible(true);
    }
    public void insertComponent(int x, int y, double weightX, double weightY, int gridwidth, int grideight, int anchor, Component comp){

    /**--- PARTENZA CELLA E PESO PER COMPONENTE ---**/
        gbc.gridx = x;
        gbc.gridy = y;

        gbc.weightx = weightX;
        gbc.weighty = weightY;

    /**--- GRIDWIDTH GRIDHEIGHT ---**/
        gbc.gridwidth = gridwidth;
        gbc.gridheight = grideight;

    /**--- ANCHOR DEI COMPONENTI ---**/
        gbc.anchor = anchor;

    /**--- MARGINI ---**/
    gbc.insets = new Insets(0,0,0,0);

    /**--- RIEMPIMENTO SPAZIO ---**/
    gbc.fill = GridBagConstraints.BOTH;

    /**--- AGGIUNTA COMPONENTE BARRA STRUMENTI ---**/
        add(comp, gbc);
    }

    @Override
    public int getHeight() {
        return height;
    }
    @Override
    public int getWidth() {
        return width;
    }

    public int getHeightStandard() {
        return heightStandard;
    }

    public int getWidthStandard() {
        return widthStandard;
    }
}
