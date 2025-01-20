package interfaccia;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BarraStrumenti extends JPanel{

    private JButton affermativo;
    private JButton negativo;
    private JButton cura;
    private JButton back;

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
