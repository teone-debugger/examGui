package interfaccia.frameBlocks;


import javax.swing.*;
import javax.swing.border.Border;

public class PannelloTitled extends PannelloText{


    public PannelloTitled(String title) {
        super();

        Border bordoInterno = BorderFactory.createTitledBorder(title);
        Border bordoEsterno = BorderFactory.createEmptyBorder(5,5,5,5);
        Border bordoFinale = BorderFactory.createCompoundBorder(bordoEsterno, bordoInterno);

        setBorder(bordoFinale);
    }

}
