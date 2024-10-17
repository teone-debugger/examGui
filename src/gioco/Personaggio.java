package gioco;

import messaggi.Messaggio;

public class Personaggio extends Posizione {

    private String nome;
    private int puntiVita;
    private final int puntiVitaMAX;
    private int puntiArmatura;
    private int monete;

    private boolean vivo;

    public Personaggio(int righe, int colonne, String tipo, String nome, int puntiVita, int puntiArmatura, int monete) {
        super(righe, colonne, tipo);

        this.nome = nome;
        this.puntiVita = puntiVita;
        this.puntiVitaMAX = puntiVita;
        this.puntiArmatura = puntiArmatura;
        this.monete = monete;
        this.vivo = true;
    }

    /**--- LANCIO DEL DADO PER ESEGUIRE AZIONI (DISINNESCO E ATTACCO) ---**/
    int rollD20(){
        return (int)(Math.random() * 20 + 1);
    }

    /**--- LANCIO DEL DADO PER DANNO DEI PNG ---**/
    private int rollD4(){
        return (int)(Math.random() * 4 + 1);
    }

    /**--- LANCIO DEL DADO PER DANNO DEL GIOCATORE ---**/
    private int rollDWeapon(Arma arma) {

        if (this.getClass().getName().equals("gioco.Giocatore")) {

            if (Giocatore.getClasse().equals(Classe.MAGO)) {

                return (int) (Math.random() * (arma.getDado() - 4) + 4);
            }
        }
        return (int) (Math.random() * arma.getDado() + 1);
    }

    public void movements(String direzione){
        /**--- RESETTO VECCHIA POSOZIONE ---**/
        Dungeon.setPosizioneMappa(new Posizione(this.getRighe(), this.getColonne(), " ", true));

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

    public void attack(Personaggio personaggio) {
        /**--- ROLL 20 PER ATTACCARE ---**/
        if(this.rollD20() > personaggio.getPuntiArmatura()){

            Messaggio.addMessaggio("'" + this.getNome() + "' COLPISCE...");

            /**--- ROLL 20 PER DANNO GIOCATORE --> PNG ---**/
            if(personaggio.getClass().getSimpleName().equals("Png")){
                personaggio.damageTaken(this.rollDWeapon(Giocatore.getArma()));
            }else{
                personaggio.damageTaken(this.rollD4());
            }

        }else{
            Messaggio.addMessaggio("'" + personaggio.getNome() + "' HA SCHIVATO IL COLPO...");
        }
    }

    void showStats(){

         /**--- STATS PERSONAGGIO ---**/
        System.out.println("ARMATURA '" + this.getNome() + "' :" + this.getPuntiArmatura());
        System.out.println("PUNTI VITA '" + this.getNome() + "' :" + this.getPuntiVita());
        System.out.println("ORO '" + this.getNome() + "' :" + this.getMonete());
    }
    public static String statsToString(Personaggio personaggio){

        String str = "ARMATURA '" + personaggio.getNome() + "' :" + personaggio.getPuntiArmatura() +"\n";
        str += "PUNTI VITA '" + personaggio.getNome() + "' :" + personaggio.getPuntiVita()  +"\n";
        str += "ORO '" + personaggio.getNome() + "' :" + personaggio.getMonete() +"\n";

        return str;
    }

    public void damageTaken(int danno){

        Messaggio.addMessaggio(this.nome + " HA RICEVUTO DANNO: " + danno);
        this.puntiVita -= danno;

        if(this.puntiVita <= 0){
            this.vivo = false;
        }

        //System.out.println(this.nome + " PUNTI VITA: " + this.puntiVita);
    }

    public boolean heal(){
        if(this.getPuntiVita() < this.getPuntiVitaMAX()) {

            /**--- SE PRESENTE VIENE RIMOSSO PERCHE' UTILIZZATO ---**/
            Class<?> c = Giocatore.removeFromInventory("POZIONE");

            if (c != null) {

                /**--- CURO AL MASSIMO FINO ALLA SALUTE MASSIMA**/
                int cura = rollD4();

                if (this.puntiVita + cura < this.puntiVitaMAX) {

                    System.out.println(this.nome + " TI SEI CURATO DI: " + cura);
                    this.puntiVita += cura;
                } else {
                    System.out.println(this.nome + " HAI RIPRISTINATO I MASSIMI PUNTI VITA: ");
                    this.puntiVita = this.puntiVitaMAX;
                }
                return true;
                //System.out.println(this.nome + " PUNTI VITA: " + this.puntiVita);
            } else {
                System.out.println(this.nome + " NON E' PRESENTE UNA POZIONE NEL TUO INVENTARIO QUINDI NON PUOI CURARTI: ");
                return false;
            }
        }else {
            System.out.println("HAI GIA' I MASSIMI PUNTI VITA");
            return false;
        }
    }
    public void takeGold(Png png){

        this.monete += png.getMonete();
        System.out.println(this.nome + " ORA HAI : " + this.monete + " MONETE D'ORO");
    }

    public String getNome() {
        return nome;
    }

    public int getPuntiVita() {
        return puntiVita;
    }

    public int getPuntiVitaMAX(){
        return puntiVitaMAX;
    }

    public int getPuntiArmatura() {
        return puntiArmatura;
    }

    public int getMonete() {
        return monete;
    }

    public boolean isVivo() {
        return vivo;
    }

}
