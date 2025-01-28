package interfaccia.frameBlocks;

import javax.swing.*;

import interfaccia.multimedia.FormEvent;
import interfaccia.multimedia.FormListener;
import util.StringUtils;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BarraStrumenti extends JPanel{

    private JButton affermativo;
    private JButton negativo;
    private JButton cura;
    private JButton back;

    private JButton salva;

    private FormListener formListener;
    private boolean clicked = false;

    public BarraStrumenti(){

        //Bottone per eseguire l'azione
        affermativo = new JButton("SI");
        affermativo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FormEvent formEvent = new FormEvent(this, "yes", (JButton)e.getSource());

                if(formListener != null){
                    formListener.formEvent(formEvent);
                }
            }
        });
        affermativo.setFont(StringUtils.getAlagardDefaultFont());
        
        //Bottone per non eseguire l'azione
        negativo = new JButton("NO");
        negativo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FormEvent formEvent = new FormEvent(this, "no", (JButton)e.getSource());

                if(formListener != null){
                    formListener.formEvent(formEvent);
                }
            }
        });
        negativo.setFont(StringUtils.getAlagardDefaultFont());
        
        //Bottone per curarsi
        cura = new JButton("CURA");
        cura.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FormEvent formEvent = new FormEvent(this, "cura", (JButton)e.getSource());

                if(formListener != null){
                    formListener.formEvent(formEvent);
                }
            }
        });
        cura.setFont(StringUtils.getAlagardDefaultFont());

        //Bottone per tornare alla vecchia stanza
        back = new JButton("INDIETRO");
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FormEvent formEvent = new FormEvent(this, "back", (JButton)e.getSource());

                if(formListener != null){
                    formListener.formEvent(formEvent);
                }
            }
        });
        back.setFont(StringUtils.getAlagardDefaultFont());

        //Bottone per salvare la partita
        salva = new JButton("SALVA");
        salva.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FormEvent formEvent = new FormEvent(this, "back", (JButton)e.getSource());

                if(formListener != null){
                    formListener.formEvent(formEvent);
                }
            }
        });
        salva.setFont(StringUtils.getAlagardDefaultFont());

        setLayout(new GridBagLayout());


        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;

        add(affermativo);

        gbc.gridx = 1;
        //gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        add(negativo);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.LINE_END;
        add(cura);

        gbc.gridx = 0;
        gbc.gridy = 2;
        //gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.LINE_END;
        add(back);

        gbc.gridx = 0;
        gbc.gridy = 3;
        //gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.LINE_END;
        add(salva);
    }

    public void setColor(Color background , Color foreground){
        setBackground(background);
        setForeground(foreground);

        //setColor(background, foreground);
    }

    public void dimension(Dimension dimension){
        setPreferredSize(dimension);
    }


    public JButton getAffermativo() {
        return affermativo;
    }

    public JButton getNegativo() {
        return negativo;
    }

    public JButton getCura() {
        return cura;
    }

    public JButton getBack() {
        return back;
    }

    public JButton getSalva() {
        return salva;
    }

    public void setFormListener(FormListener formListener) {
        this.formListener = formListener;
    }

    public boolean isClicked() {
        return clicked;
    }
    public void setClicked(boolean clicked) {
        this.clicked = clicked;
    }

}
