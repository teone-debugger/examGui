package interfaccia.multimedia;

import javax.swing.*;
import java.util.EventObject;

public class FormEvent extends EventObject {

    private static String str;
    private JButton jButton;

    public FormEvent(Object source){
        super(source);
    }

    public FormEvent(Object source, String str,  JButton jButton) {
        super(source);
        this.str = str;
        this.jButton = jButton;
    }

    public static String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }
    public JButton getJButton() {
        return jButton;
    }
}
