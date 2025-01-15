package gioco;

/**public interface gioco.Posizione {
    int righe = 0;
    int colonne = 0;
    String tipo = "";
    boolean libera = false;


    default int getrighe(){return righe;}
    default int getcolonne(){return colonne;}
    default String getTipo(){return tipo;}
    public boolean getLibera(){return libera;}
}*/

public class Posizione {
    int righe;        /**--- RIGHE ---**/
    int colonne;        /**--- colonne ---**/
    String tipo;
    boolean libera;

    public Posizione(int righe, int colonne, String tipo) {
        this.righe = righe;
        this.colonne = colonne;
        this.tipo = tipo;
        libera = false;
    }
    public Posizione(int righe, int colonne, String tipo, boolean libera) {
        this.righe = righe;
        this.colonne = colonne;
        this.tipo = tipo;
        this.libera = libera;
    }

    public int getRighe(){return righe;}
    public int getColonne(){return colonne;}
    public String getTipo(){return tipo;} /***--- toString() ---***/
    public boolean isLibera(){return libera;}

    public void setRighe(int righe) {this.righe = righe;}
    public void setColonne(int colonne) {this.colonne = colonne;}
    public void setTipo(String tipo) {this.tipo = tipo;}
}
