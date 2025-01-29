package test.java.game.character;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PersonaggioTest {

    public PersonaggioTest() {
        this.puntiArmatura = 0;
        this.puntiVita = 0;
        this.puntiVitaMAX = 15;
        this.nome = "?";
        this.monete = 0;
    }

    public PersonaggioTest(int righe, int colonne, String tipo, String nome, int puntiVita, int puntiArmatura, int monete) {
        this.righe = righe;
        this.colonne = colonne;
        this.tipo = tipo;
        this.nome = nome;
        this.puntiVita = puntiVita;
        this.puntiArmatura = puntiArmatura;
        this.monete = monete;
        this.puntiVitaMAX = puntiVita;
    }

    private int righe;
    private int colonne;
    private String tipo;

    public int getRighe() {
        return righe;
    }

    public int getColonne() {
        return colonne;
    }

    public String getTipo() {
        return tipo;
    }

    private int puntiArmatura;
    private int puntiVita;
    private int puntiVitaMAX;
    private String nome;
    private int monete;

    @Test
    public void testDefaultConstructor() {
        PersonaggioTest personaggio = new PersonaggioTest();
        assertNotNull(personaggio);
        assertEquals(0, personaggio.getRighe());
        assertEquals(0, personaggio.getColonne());
        assertEquals("?", personaggio.getTipo());
        assertEquals(15, personaggio.getPuntiVitaMAX());
    }

    @Test
    public void testParameterizedConstructor() {
        PersonaggioTest personaggio = new PersonaggioTest(5, 10, "Test", "Hero", 100, 50, 10);
        assertNotNull(personaggio);
        assertEquals(5, personaggio.getRighe());
        assertEquals(10, personaggio.getColonne());
        assertEquals("Test", personaggio.getTipo());
        assertEquals("Hero", personaggio.getNome());
        assertEquals(100, personaggio.getPuntiVita());
        assertEquals(50, personaggio.getPuntiArmatura());
        assertEquals(10, personaggio.getMonete());
    }

    @Test
    public void testSetAndGetNome() {
        PersonaggioTest personaggio = new PersonaggioTest();
        personaggio.setNome("Hero");
        assertEquals("Hero", personaggio.getNome());
    }

    @Test
    public void testSetAndGetPuntiVita() {
        PersonaggioTest personaggio = new PersonaggioTest();
        personaggio.setPuntiVita(100);
        assertEquals(100, personaggio.getPuntiVita());
    }

    @Test
    public void testSetAndGetPuntiArmatura() {
        PersonaggioTest personaggio = new PersonaggioTest();
        personaggio.setPuntiArmatura(50);
        assertEquals(50, personaggio.getPuntiArmatura());
    }

    @Test
    public void testSetAndGetMonete() {
        PersonaggioTest personaggio = new PersonaggioTest();
        personaggio.setMonete(10);
        assertEquals(10, personaggio.getMonete());
    }

    @Test
    public void testSetAndGetVivo() {
        PersonaggioTest personaggio = new PersonaggioTest();
        personaggio.setVivo(true);
        assertTrue(personaggio.isVivo());
    }

    private boolean vivo;

    public void setVivo(boolean vivo) {
        this.vivo = vivo;
    }

    public boolean isVivo() {
        return vivo;
    }

        //Metodo per prendere il nome del personaggio
        public String getNome() {return nome;}

        //Metodo per impostare il nome del personaggio
        public void setNome(String nome) {
            this.nome = nome;
        }

        //Metodo per prendere i punti vita attuali del personaggio
        public int getPuntiVita() {return puntiVita;}

        //Metodo per impostare i punti vita del personaggio
        public void setPuntiVita(int puntiVita) {
            this.puntiVita = puntiVita;
        }
    
        //Metodo per prendere i punti vita massimi del personaggio
        public int getPuntiVitaMAX(){return puntiVitaMAX;}
    
        //Metodo per prendere i punti armatura del personaggio
        public int getPuntiArmatura() {return puntiArmatura;}
    
        //Metodo per impostare i punti armatura del personaggio
        public void setPuntiArmatura(int puntiArmatura) {
            this.puntiArmatura = puntiArmatura;
        }
    
        //Metodo per prendere le monete del personaggio
        public int getMonete() {return monete;}

        //Metodo per impostare le monete del personaggio
        public void setMonete(int monete) {
            this.monete = monete;
        }
}