package interfaccia;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BarraStrumenti extends JPanel{

    private JButton affermativo;
    private JButton negativo;

    private FormListener formListener;
    private boolean clicked = false;

    BarraStrumenti(){
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

        setLayout(new GridBagLayout());


        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;

        add(affermativo);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.EAST;
        add(negativo);
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

    public void setFormListener(FormListener formListener) {
        this.formListener = formListener;
    }

    public boolean isClicked() {
        return clicked;
    }
    public void setClicked(boolean clicked) {
        System.out.println("CLICKED");
        this.clicked = clicked;
    }

}
