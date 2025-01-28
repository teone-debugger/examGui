package game.character;

import game.Posizione;
import game.enums.*;
import game.items.view.Arma;
import messaggi.Messaggio;
import util.RandomUtils;
import game.character.player.Giocatore;

import java.io.Serializable;

import game.Dungeon;
import interfaccia.multimedia.ImagePanel;


public class Personaggio extends Posizione{

    private String nome;
    private int puntiVita;
    private final int puntiVitaMAX;
    private int puntiArmatura;
    private int monete;

    private boolean vivo;

    private ImagePanel immagine;


    //Metodo Costruttore
    public Personaggio(int righe, int colonne, String tipo, String nome, int puntiVita, int puntiArmatura, int monete) {
        super(righe, colonne, tipo);

        this.nome = nome;
        this.puntiVita = puntiVita;
        this.puntiVitaMAX = puntiVita;
        this.puntiArmatura = puntiArmatura;
        this.monete = monete;
        this.vivo = true;
    }

    //Metodo per danno dell'arma del giocatore
    private int rollDWeapon(Arma arma) {

        if (this.getClass().getSimpleName().equals("Giocatore")) {

            if (Giocatore.getClasse().equals(Classe.MAGO)) {

                return (int) (Math.random() * (arma.getDado() - 4) + 4);
            }
        }
        return (int) (Math.random() * arma.getDado() + 1);
    }

    //Metodo per il movimento
    public void movements(String direzione){
        /**--- RESETTO VECCHIA POSOZIONE ---**/
        Dungeon.setPosizioneMappa(new Posizione(this.getRighe(), this.getColonne()));

        switch(direzione.toLowerCase()){

            case "up":
            case "u":

                if (Dungeon.getMappa()[this.getRighe()-1][this.getColonne()].isLibera()) {
                    this.setRighe(this.getRighe()-1);
                }
                break;

            case "down":
            case "d":
                if (Dungeon.getMappa()[this.getRighe()+1][this.getColonne()].isLibera()) {
                    this.setRighe(this.getRighe()+1);
                }
                break;

            case "left":
            case "l":
                if (Dungeon.getMappa()[this.getRighe()][this.getColonne()-1].isLibera()) {
                    this.setColonne(this.getColonne()-1);
                }
                break;

            case "right":
            case "r":
                if (Dungeon.getMappa()[this.getRighe()][this.getColonne()+1].isLibera()) {
                    this.setColonne(this.getColonne()+1);
                }
                break;

            default:
                System.out.println("MOSSA NON VALIDA!");
                break;
        }

        /**--- INSERISCO NUOVA POSIZIONE ---**/
        Dungeon.setPosizioneMappa(this);
    }

    //Metodo per attaccare
    public void attack(Personaggio personaggio) {
        /**--- ROLL 20 PER ATTACCARE ---**/
        if(RandomUtils.rollD20() > personaggio.getPuntiArmatura()){

            Messaggio.addMessaggio("'" + this.getNome() + "' COLPISCE...");

            /**--- ROLL 20 PER DANNO GIOCATORE --> PNG ---**/
            if(!personaggio.getClass().getSimpleName().equals("Giocatore")){

                personaggio.damageTaken(this.rollDWeapon(Giocatore.getArma()));
            }else{
               if(this.getClass().getSimpleName().equals("Png")){

                   personaggio.damageTaken(RandomUtils.rollD4());
               }else{
                   switch(RandomUtils.rollD4()){
                        case 1:
                        case 2:
                            personaggio.damageTaken(Dungeon.getDrago().getDannoMorso());
                            break;
                        case 3:
                            personaggio.damageTaken(Dungeon.getDrago().getDannoZampa());
                            break;
                        case 4:
                            personaggio.damageTaken(Dungeon.getDrago().getDannoFiammata());
                            break;
                   }
               }
            }
        }else{
            Messaggio.addMessaggio("'" + personaggio.getNome() + "' HA SCHIVATO IL COLPO...");
        }
    }

    //Metodo per vedere le statistiche
    public void showStats(){

         /**--- STATS PERSONAGGIO ---**/
        System.out.println("ARMATURA '" + this.getNome() + "' :" + this.getPuntiArmatura());
        System.out.println("PUNTI VITA '" + this.getNome() + "' :" + this.getPuntiVita());
        System.out.println("ORO '" + this.getNome() + "' :" + this.getMonete());
    }

    //Metodo per convertire le statistiche in stringa
    public static String statsToString(Personaggio personaggio){

        String str = "ARMATURA '" + personaggio.getNome() + "' :" + personaggio.getPuntiArmatura() +"\n";
        str += "PUNTI VITA '" + personaggio.getNome() + "' :" + personaggio.getPuntiVita()  +"\n";
        str += "ORO '" + personaggio.getNome() + "' :" + personaggio.getMonete() +"\n";

        return str;
    }

    //Mewtodo per il ricalcolo della vita (DANNO)
    public void damageTaken(int danno){

        Messaggio.addMessaggio(this.nome + " HA RICEVUTO DANNO: " + danno);
        this.puntiVita -= danno;

        if(this.puntiVita <= 0){
            this.vivo = false;
        }

        //System.out.println(this.nome + " PUNTI VITA: " + this.puntiVita);
    }

    //Metodo per il ricalcolo della vita (CURA)
    public boolean heal(){
        if(this.getPuntiVita() < this.getPuntiVitaMAX()) {

            /**--- SE PRESENTE VIENE RIMOSSO PERCHE' UTILIZZATO ---**/
            Class<?> c = Giocatore.getInventory().removeFromInventory("POZIONE");

            if (c != null) {

                /**--- CURO AL MASSIMO FINO ALLA SALUTE MASSIMA**/
                int cura = RandomUtils.rollD4();

                if (this.puntiVita + cura < this.puntiVitaMAX) {

                    Messaggio.addMessaggio(this.nome + " TI SEI CURATO DI: " + cura);
                    this.puntiVita += cura;
                } else {
                    Messaggio.addMessaggio(this.nome + " HAI RIPRISTINATO I MASSIMI PUNTI VITA: ");
                    this.puntiVita = this.puntiVitaMAX;
                }
                return true;
                //System.out.println(this.nome + " PUNTI VITA: " + this.puntiVita);
            } else {
                Messaggio.addMessaggio(this.nome + " NON E' PRESENTE UNA POZIONE NEL TUO INVENTARIO QUINDI NON PUOI CURARTI: ");
                return false;
            }
        }else {
            Messaggio.addMessaggio("HAI GIA' I MASSIMI PUNTI VITA");
            return false;
        }
    }

    //Metodo per aggiungere oro vinto
    public void takeGold(Personaggio personaggio){

        this.monete += personaggio.getMonete();
        Messaggio.addMessaggio(this.nome + " ORA HAI : " + this.monete + " MONETE D'ORO");
    }

    //Metodo per togliere l'oro quando si scappa
    public void loseGold(){

        this.monete -= 50;
        Messaggio.addMessaggio(this.nome + " ORA HAI : " + this.monete + " MONETE D'ORO");
    }

    /**--- METODI GETTER ---**/

    //Metodo per prendere il nome del personaggio
    public String getNome() {return nome;}

    //Metodo per prendere i punti vita attuali del personaggio
    public int getPuntiVita() {return puntiVita;}

    //Metodo per prendere i punti vita massimi del personaggio
    public int getPuntiVitaMAX(){return puntiVitaMAX;}

    //Metodo per prendere i punti armatura del personaggio
    public int getPuntiArmatura() {return puntiArmatura;}

    //Metodo per prendere le monete del personaggio
    public int getMonete() {return monete;}

    //Metodo per saper ese il personaggio è vivo
    public boolean isVivo() {return vivo;}

    //Metodo per prendere l'immagine del personaggio
    public ImagePanel getImmagine() {return immagine;}

    /**--- METODI SETTER ---**/

    //Metodo per settare l'immagione del personaggio
    public void setImmagine(ImagePanel immagine) {this.immagine = immagine;}

}
