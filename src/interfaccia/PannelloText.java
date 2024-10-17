package interfaccia;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PannelloText extends JPanel {

    private JTextArea jtextArea;

    PannelloText(){

        setLayout(new BorderLayout());

        this.jtextArea = new JTextArea();

    }

    public void dimension(Dimension dimension){
        setPreferredSize(dimension);
    }
    public void setColor(Color background , Color foreground){
        jtextArea.setBackground(background);
        jtextArea.setForeground(foreground);
        setBackground(background);
    }

    public void setText(String testo){
        jtextArea.setText(testo);

        addTextArea();
    }

    public void addText(String testo){
        jtextArea.append(testo + "\n");

        addTextArea();
    }

    private void addTextArea(){

        jtextArea.setEditable(false);
        jtextArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 20));
        jtextArea.setPreferredSize(this.getPreferredSize());

        add(jtextArea);
    }

    public JTextArea getJtextArea() {
        return jtextArea;
    }
}
