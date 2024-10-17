package gioco;

public class Oggetto  extends Posizione {

    private int peso;
    private String descrizione;
    private static int count = 1;
    private int index;

    public Oggetto(int righe, int colonne) {
        super(righe, colonne, "*");

        this.index = count;
        count++;

        generateStats();

    }
    public Oggetto(int righe, int colonne, String descrizione) {
        super(righe, colonne, "%");

        this.index = count;
        count++;

        this.descrizione = descrizione;

    }

    private void generateStats(){
        switch((int)(Math.random() * 10 + 1)){
            case 1:
            case 2:
            case 3:
                this.descrizione = "POZIONE";
                this.peso = 10;

                break;
            case 4:
            case 5:
                this.descrizione = "TORCIA";
                this.peso = 5;

                break;
            case 6:
                this.descrizione = "MATITA";
                this.peso = 5;

                break;
            case 7:
                this.descrizione = "PIETRA FOCAIA";
                this.peso = 5;

                break;
            case 8:
                this.descrizione = "DIARIO VUOTO";
                this.peso = 5;

                break;
            case 9:
                this.descrizione = "LAPISLAZZULI";
                this.peso = 5;

                break;
            case 10:
                this.descrizione = "TAGLIACARTE";
                this.peso = 5;

                break;
        }

    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public Integer getIndex() {
        return index;
    }
}
