package interfaccia.framesFight;

import game.Dungeon;
import game.Game;
import game.character.player.Giocatore;
import game.enums.*;
import messaggi.Messaggio;
import util.StringUtils;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import interfaccia.multimedia.ImagePanel;
import interfaccia.framesGame.FrameGame;

public class FrameFight extends JFrame{

    private JButton attacca;
    private JButton cura;
    private JButton fuga;

    private JLabel backgroundLabel;
    private ImagePanel characterPanel;
    private ImagePanel enemyPanel;

    private JLabel charactNameLabel;
    private JLabel enemyNameLabel;

    private JLabel characterHPLabel;
    private JLabel enemyHPLabel;

    private JProgressBar charactHealthBar;
    private JProgressBar enemyHealthBar;

    private int turnoGiocatore, turnoPng;

    public FrameFight() {
        super("COMBATTI");

        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        //turno giocatore
        turnoGiocatore = (int) (Math.random() * 20 + 1);
        turnoPng = (int) (Math.random() * 20 + 1);
 

        //carico le immagini e setto nemico e pg princ
        backgroundLabel = new JLabel(scaleImage("resources/images/background/backgroundBase.png", 800, 600));
        backgroundLabel.setBounds(0, 0, 800, 600);

        characterPanel = setImmagineGiocatore(Dungeon.getGiocatore().getClasse(), Dungeon.getGiocatore().getRazza());
        characterPanel.setBounds(50, 250, 200, 200);

        enemyPanel = setImmaginePng(Dungeon.getGiocatore().getNemico().getPuntiVitaMAX());
        enemyPanel.setBounds(500, 150, 200,  200);

        scalePanels(200, 200, 200, 200);

        //barre della vita
        charactHealthBar = createHealthBar(Dungeon.getGiocatore().getPuntiVitaMAX(), Dungeon.getGiocatore().getPuntiVita());
        charactHealthBar.setBounds(50, 230, characterPanel.getWidth(), 30);
        updateHealthBarColor(charactHealthBar);

        enemyHealthBar = createHealthBar(Dungeon.getGiocatore().getNemico().getPuntiVitaMAX(), Dungeon.getGiocatore().getNemico().getPuntiVita());
        enemyHealthBar.setBounds(500, 130, enemyPanel.getWidth(), 30);
        updateHealthBarColor(enemyHealthBar);

        //etichette nome
        charactNameLabel = createNameLabel(Dungeon.getGiocatore().getNome(), StringUtils.getAlagardDefaultFont());
        positionNameLabel(charactNameLabel, charactHealthBar);
        characterHPLabel = createHPLabel(Dungeon.getGiocatore().getPuntiVitaMAX(), Dungeon.getGiocatore().getPuntiVita(), StringUtils.getBodyFont(13));
        characterHPLabel.setBounds(charactHealthBar.getX() - 50, charactHealthBar.getY(), 50, 30);

        enemyNameLabel = createNameLabel(Dungeon.getGiocatore().getNemico().getNome(), StringUtils.getAlagardDefaultFont());
        positionNameLabel(enemyNameLabel, enemyHealthBar);
        enemyHPLabel = createHPLabel(Dungeon.getGiocatore().getNemico().getPuntiVitaMAX(), Dungeon.getGiocatore().getNemico().getPuntiVita(), StringUtils.getBodyFont(13));
        enemyHPLabel.setBounds(enemyHealthBar.getX() - 50, enemyHealthBar.getY(), 50, 30);

        
        //pulsanti
        cura = new JButton("CURA");
            setupButton(cura, 300, 500, StringUtils.getAlagardDefaultFont());

        cura.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(Dungeon.getGiocatore().heal()) {

                    //GIOCATORE
                    //png.attack(this);
                    Dungeon.getGiocatore().getNemico().attack(Dungeon.getGiocatore());
                }
                FrameGame.getMessaggi().setText(Messaggio.getMessaggio());
                Messaggio.clearMesaggio();
                FrameGame.getStatistiche().setText(Giocatore.statsToString(Dungeon.getGiocatore()) + "\n" + Giocatore.statsToString(Dungeon.getGiocatore().getNemico()));
                
                charactHealthBar.setValue(Dungeon.getGiocatore().getPuntiVita());
                updateHealthBarColor(charactHealthBar);
                updateCharacterHPLabel(Dungeon.getGiocatore().getPuntiVita(), Dungeon.getGiocatore().getPuntiVitaMAX());
            }
        });
        
        fuga = new JButton("FUGA");
            setupButton(fuga, 550, 500, StringUtils.getAlagardDefaultFont());

        fuga.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrameGame.getMessaggi().setText("MIO PAVIDO AVVENTURIERO, SEI FUGGITO DA '" + Dungeon.getGiocatore().getNemico().getNome() + "'");
                handleFuga();
                setVisible(true);

                 /**--- PERDE ORO SE FUGGE ---**/
                 Dungeon.getGiocatore().loseGold();
            }
        });

        attacca = new JButton("ATTACCA");
            setupButton(attacca, 50, 500, StringUtils.getAlagardDefaultFont());
        attacca.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                FrameGame.getMessaggi().setText("");
                actionAttacca();
                Messaggio.clearMesaggio();

                charactHealthBar.setValue(Dungeon.getGiocatore().getPuntiVita());
                updateHealthBarColor(charactHealthBar);
                updateCharacterHPLabel(Dungeon.getGiocatore().getPuntiVita(),Dungeon.getGiocatore().getPuntiVitaMAX());
                
                enemyHealthBar.setValue(Dungeon.getGiocatore().getNemico().getPuntiVita());
                updateHealthBarColor(enemyHealthBar);
                updateEnemyHPLabel(Dungeon.getGiocatore().getNemico().getPuntiVita(), Dungeon.getGiocatore().getNemico().getPuntiVitaMAX());
            }
        });

        updateHealthBarColor(charactHealthBar);
        updateHealthBarColor(enemyHealthBar);

        add(charactNameLabel);
        add(enemyNameLabel);

        add(enemyHealthBar);
        add(charactHealthBar);

        add(characterHPLabel);
        add(enemyHPLabel);
        
        //aggiungo le immagini in sequenza in base a cosa va sopra o sotto
        add(characterPanel);
        add(enemyPanel);

        add(backgroundLabel);

        //animazione fade in
        startFadeIn();

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        setVisible(true);
        setResizable(false);
        setLocation(FrameGame.getWindows()[0].getX(), FrameGame.getWindows()[0].getY());

    }
    //Metodo per generare l'immagine del giocatore a differenza della classe e razza
    public ImagePanel setImmagineGiocatore(Classe classe, Razza razza){
        ImagePanel immagine;

        System.out.println(razza.toString().toUpperCase());

        switch(razza.toString().toUpperCase()) {
            case "UMANO":
               
                switch (classe.toString().toUpperCase()) {
                    case "LADRO":
                        immagine = new ImagePanel("resources/images/pg principali/UMANO/UMANOLADRO.png", 200, 200);
                        break;
                
                    case "MAGO":
                        immagine = new ImagePanel("resources/images/pg principali/UMANO/UMANOMAGO.png", 200, 200);
                        break;

                    case "BARBARO":
                        immagine = new ImagePanel("resources/images/pg principali/UMANO/UMANOBARBARO.png", 200, 200);
                        break;

                    default:
                        immagine = new ImagePanel(classe.toString().toUpperCase(), 200, 200);
                        break;
                }
                break;

            case "NANO":

                switch (classe.toString().toUpperCase()) {
                    case "LADRO":
                        immagine = new ImagePanel("resources/images/pg principali/NANO/NANOLADRO.png", 200, 200);
                        break;
                
                    case "MAGO":
                        immagine = new ImagePanel("resources/images/pg principali/NANO/NANOMAGO.png", 200, 200);
                        break;

                    case "BARBARO":
                        immagine = new ImagePanel("resources/images/pg principali/NANO/NANOBARBARO.png", 200, 200);
                        break;

                    default:
                        immagine = new ImagePanel(classe.toString().toUpperCase(), 200, 200);
                        break;
                }
                break;

            case "ELFO":
                switch (classe.toString().toUpperCase()) {
                    case "LADRO":
                        immagine = new ImagePanel("resources/images/pg principali/ELFO/ELFOLADRO.png", 200, 200);
                        break;
                
                    case "MAGO":
                        immagine = new ImagePanel("resources/images/pg principali/ELFO/ELFOMAGO.png", 200, 200);
                        break;

                    case "BARBARO":
                        immagine = new ImagePanel("resources/images/pg principali/ELFO/ELFOBARBARO.png", 200, 200);
                        break;

                    default:
                        immagine = new ImagePanel(classe.toString().toUpperCase(), 200, 200);
                        break;
                }
                break;

            default:
                immagine = new ImagePanel("resources/images/pg principali/ELFO/image (12).png", 200, 200);
                break;

        }
        return immagine;

}

    //Metodo per inserire l'immagine del nemico in base al punteggio vita
    public ImagePanel setImmaginePng(int vita){

        if(vita == 8){
            return new ImagePanel("resources/images/nemici/image (10).png", 300, 300);

        }else if( vita == 12){
            return new ImagePanel("resources/images/nemici/image (1).png", 300, 300);

        }else if( vita == 15){
            return new ImagePanel("resources/images/nemici/ghost.png", 300, 300);

        }else if( vita == 18){
            return new ImagePanel("resources/images/nemici/image (2).png", 300, 300);

        }else if( vita == 21){
            return new ImagePanel("resources/images/nemici/image (3).png", 300, 300);

        }else if( vita == 24){
            return new ImagePanel("resources/images/nemici/image (9).png", 300, 300);

        }else if( vita == 27){
            return new ImagePanel("resources/images/nemici/image.png", 300, 300);

        }else{
            return new ImagePanel("resources/images/nemici/image (21).png", 300, 300);
        }
    }

    private void handleDeath(boolean isDead, String nome) {
        String message;

        if(isDead) {
            message = "Mio prode avventuriero, hai sconfitto " + nome + "!!!";
            EventScreen eventScreen = new EventScreen(message);
            eventScreen.showScreen(this);
        } else {
            message="";
            EventScreen eventScreen = new EventScreen(message);
            eventScreen.showScreen(this);
        }
    }

    private void handleWin(String messaggio) {
        String message = messaggio;
        EventScreen eventScreen = new EventScreen(message);
        eventScreen.showScreen(this);
    }

    private void handleNoArmi() {
        String message = "PENSAVI DI POTER COMBATTERE SENZA UN ARMA PER CASO?? AHAHAHA";

        EventScreen eventScreen = new EventScreen(message);
        eventScreen.showScreen(this);
    }

    private void handleFuga(){
        String message = "QUALCUNO QUA SE L'E' FATTA SOTTO AHAHAHAHA";

        EventScreen eventScreen = new EventScreen(message);
        eventScreen.showScreen(this);
    }

    private void setupButton(JButton button, int x, int y, Font font){
        button.setBounds(x, y, 200, 50);
        button.setFont(font);
        button.setFocusPainted(false);
        add(button);
    }

    private void startFadeIn(){
        Timer timer = new Timer(80, new ActionListener() {
            private float opacity = 0f;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (opacity < 1f) {
                    opacity += 0.05f; //incremento opacità
                    characterPanel.setOpacity(opacity);
                    enemyPanel.setOpacity(opacity);
                }
                else{
                    ((Timer) e.getSource()).stop();
                }
            }
        });
        timer.start();
    }

    //scaling delle immagini che ho importato e dei Panel
    public static ImageIcon scaleImage(String imagePath, int width, int height) {
        ImageIcon originalIcon = new ImageIcon(imagePath);
        Image scaledImage = originalIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }

    private void scalePanels(int characterWidth, int characterHeight, int enemyWidth, int enemyHeight) {
        characterPanel.setScaledSize(characterWidth, characterHeight);
        enemyPanel.setScaledSize(enemyWidth, enemyHeight);
    }

    private JProgressBar createHealthBar(int maxHealth, int health) {
        JProgressBar healthBar = new JProgressBar(0, maxHealth);
        healthBar.setValue(health);
        healthBar.setStringPainted(false);
        healthBar.setForeground(Color.GREEN);
        healthBar.setBackground(Color.DARK_GRAY);
        healthBar.setBorder(new LineBorder(Color.BLACK, 5));

        return healthBar;
    }

    private void updateHealthBarColor(JProgressBar healthBar) {
        int health = healthBar.getValue();
        int maxHealth = healthBar.getMaximum();

        if(health > maxHealth*0.6) {
            healthBar.setForeground(Color.GREEN);
        } else if (health > maxHealth*0.3) {
            healthBar.setForeground(Color.ORANGE);
        } else {
            healthBar.setForeground(Color.RED);
        }
    }

    private JLabel createHPLabel(int vitaMax, int vitaAttuale, Font font) {
        JLabel HPlabel = new JLabel(vitaAttuale + "/" + vitaMax, SwingConstants.CENTER);
        HPlabel.setFont(font);
        HPlabel.setForeground(Color.WHITE);
        HPlabel.setBackground(Color.BLACK);
        HPlabel.setOpaque(true);
        HPlabel.setBorder(BorderFactory.createEmptyBorder(2, 5, 2, 5));
        return HPlabel;
    }

    public void updateCharacterHPLabel(int currentHealth, int maxHealth) {
        characterHPLabel.setText(currentHealth + "/" + maxHealth);
        characterHPLabel.repaint();
    }

    public void updateEnemyHPLabel(int currentHealth, int maxHealth){
        enemyHPLabel.setText(currentHealth + "/" + maxHealth);
        enemyHPLabel.repaint();
    }

    private JLabel createNameLabel(String nome, Font font){
        JLabel nameLabel = new JLabel(nome, SwingConstants.CENTER);
        nameLabel.setFont(font);
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setBackground(Color.BLACK);
        nameLabel.setOpaque(true);
        nameLabel.setBorder(BorderFactory.createEmptyBorder(2, 5, 2, 5));
        return nameLabel;
    }

    private void positionNameLabel(JLabel nameLabel, JProgressBar healthBar){
        int barX = healthBar.getX();
        int barY = healthBar.getY();

        int labelWidth = nameLabel.getPreferredSize().width;
        int labelHeight = nameLabel.getPreferredSize().height;

        nameLabel.setBounds(barX + 5, barY - labelHeight, labelWidth, labelHeight);
    }

    private void actionAttacca(){

        /**--- ATTACCA SE HO UN'ARMA ---**/
        if(Giocatore.getArma() != null) {

            Dungeon.getGiocatore().fightDinamic(Dungeon.getGiocatore().getNemico(), turnoGiocatore, turnoPng);
            FrameGame.getMessaggi().setText(Messaggio.getMessaggio());
        }else{

            handleNoArmi();
            setVisible(true);
            FrameGame.getMessaggi().setText("NON PUOI COMBATTERE " + Dungeon.getGiocatore().getNemico().getNome() + " NON HAI UN ARMA");
        }

        /**--- ALLA MORTE DI UNO DEI DUE SCOMPARE LA FINESTRA ---**/
        if(!Dungeon.getGiocatore().isVivo()){

            handleDeath(Dungeon.getGiocatore().isVivo(), Dungeon.getGiocatore().getNome());
            setVisible(true);
            FrameGame.getMessaggi().setText("MI DISPIACE MIO PRODE AVVENTURIERO " + Dungeon.getGiocatore().getNome() + " SEI MORTO IN QUEST'AVVENTURA");
        }
        if(!Dungeon.getGiocatore().getNemico().isVivo()){

            handleDeath(Dungeon.getGiocatore().isVivo(), Dungeon.getGiocatore().getNemico().getNome());
            setVisible(true);
            FrameGame.getMappa().setText(Dungeon.dungeonToString());
            FrameGame.getMessaggi().setText("MIO PRODE AVVENTURIERO " + Dungeon.getGiocatore().getNome() + " HAI SCONFITTO " + Dungeon.getGiocatore().getNemico().getNome());
        }

        /**--- CAMBIO LE STATS SULLA FINESTRA PRINCIPALE (OPZIONALE NON BELLISSIMO VEDI TU GIAN :) ) ---**/
        if(Dungeon.getGiocatore().isVivo() && Dungeon.getGiocatore().getNemico().isVivo()) {

            FrameGame.getStatistiche().setText(Giocatore.statsToString(Dungeon.getGiocatore()) + "\n" + Giocatore.statsToString(Dungeon.getGiocatore().getNemico()));
        }else{
            FrameGame.getStatistiche().setText(Giocatore.statsToString(Dungeon.getGiocatore()));
            //setVisible(false);
        }
         if(Game.isWin()){
            handleWin("win");
            setVisible(true);
            FrameGame.getMessaggi().setText("HAI VINTO L'AVVENTURA!!!");
         }
    }

}
