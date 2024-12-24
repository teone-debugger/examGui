package interfaccia;

import gioco.Dungeon;
import gioco.Giocatore;
import gioco.Razza;
import gioco.Classe;
import messaggi.Messaggio;

import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrameFight extends JFrame{

    private JButton attacca;
    private JButton cura;
    private JButton fuga;

    private JLabel backgroundLabel;
    private ImagePanel characterPanel;
    private ImagePanel enemyPanel;

    private JLabel charactNameLabel;
    private JLabel enemyNameLabel;

    private JProgressBar charactHealthBar;
    private JProgressBar enemyHealthBar;

    //private PannelloTitled azioni;

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
        backgroundLabel = new JLabel(scaleImage("src/images/background/backgroundBase.png", 800, 600));
        backgroundLabel.setBounds(0, 0, 800, 600);
        //add(backgroundLabel);

        characterPanel = getImmagine();
        characterPanel.setBounds(50, 250, 200, 200);
        //add(characterPanel);

        enemyPanel = new ImagePanel("src/images/nemici/ghost.png", 200, 200);
        enemyPanel.setBounds(500, 150, 200, 200);
        //add(enemyPanel);

        scalePanels(200, 200, 200, 200);

        //barre della vita
        charactHealthBar = createHealthBar(Dungeon.getGiocatore().getPuntiVita());
        charactHealthBar.setBounds(50, 230, 200, 20);

        enemyHealthBar = createHealthBar(Dungeon.getGiocatore().getNemico().getPuntiVita());
        enemyHealthBar.setBounds(500, 130, 200, 20);

        //carico il font personalizzato
        Font alagardFont;
        try {
            alagardFont = Font.createFont(Font.TRUETYPE_FONT, new java.io.File("src/font/alagard.ttf")).deriveFont(24f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(alagardFont); // Registra il font

        }
        catch (Exception e) {
            e.printStackTrace();
            alagardFont = new Font("Serif", Font.PLAIN, 24); // Font di fallback
        }

        //etichette nome
        charactNameLabel = createNameLabel(Dungeon.getGiocatore().getNome(), alagardFont);
        positionNameLabel(charactNameLabel, charactHealthBar);

        enemyNameLabel = createNameLabel(Dungeon.getGiocatore().getNemico().getNome(), alagardFont);
        positionNameLabel(enemyNameLabel, enemyHealthBar);
        
        //pulsanti
        cura = new JButton("CURA");
            setupButton(cura, 300, 500, alagardFont);

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
            }
        });
        
        fuga = new JButton("FUGA");
            setupButton(fuga, 550, 500, alagardFont);

        fuga.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrameGame.getMessaggi().setText("MIO PAVIDO AVVENTURIERO, SEI FUGGITO DA '" + Dungeon.getGiocatore().getNemico().getNome() + "'");
                setVisible(false);
            }
        });

        attacca = new JButton("ATTACCA");
            setupButton(attacca, 50, 500, alagardFont);
        attacca.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrameGame.getMessaggi().setText("");
                actionAttacca();
                Messaggio.clearMesaggio();
            }
        });

        updateHealthBarColor(charactHealthBar);
        updateHealthBarColor(enemyHealthBar);

        add(charactNameLabel);
        add(enemyNameLabel);

        add(enemyHealthBar);
        add(charactHealthBar);
        
        //aggiungo le immagini in sequenza in base a cosa va sopra o sotto
        add(characterPanel);
        add(enemyPanel);

        add(backgroundLabel);

        //animazione fade in
        startFadeIn();

        //settings
        //settings();
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        setVisible(true);

    }

    private ImagePanel getImmagine(){
            ImagePanel immagine;
            Dungeon.getGiocatore();

            String razza = toStringRazza(Giocatore.getRazza());
            String classe = toStringClasse(Giocatore.getClasse());

            switch(razza) {
                case "UMANO":
                   
                    switch (classe) {
                        case "LADRO":
                            immagine = new ImagePanel("src/images/pg principali/UMANO/UMANOLADRO.png", 200, 200);
                            break;
                    
                        case "MAGO":
                            immagine = new ImagePanel("src/images/pg principali/UMANO/UMANOMAGO.png", 200, 200);
                            break;

                        case "BARBARO":
                            immagine = new ImagePanel("src/images/pg principali/UMANO/UMANOBARBARO.png", 200, 200);
                            break;

                        default:
                            immagine = new ImagePanel(classe, 200, 200);
                            break;
                    }
                    break;

                case "NANO":

                    switch (classe) {
                        case "LADRO":
                            immagine = new ImagePanel("src/images/pg principali/NANO/NANOLADRO.png", 200, 200);
                            break;
                    
                        case "MAGO":
                            immagine = new ImagePanel("src/images/pg principali/NANO/NANOMAGO.png", 200, 200);
                            break;

                        case "BARBARO":
                            immagine = new ImagePanel("src/images/pg principali/NANO/NANOBARBARO.png", 200, 200);
                            break;

                        default:
                            immagine = new ImagePanel(classe, 200, 200);
                            break;
                    }
                    break;

                case "ELFO":
                    switch (classe) {
                        case "LADRO":
                            immagine = new ImagePanel("src/images/pg principali/ELFO/ELFOLADRO.png", 200, 200);
                            break;
                    
                        case "MAGO":
                            immagine = new ImagePanel("src/images/pg principali/ELFO/ELFOMAGO.png", 200, 200);
                            break;

                        case "BARBARO":
                            immagine = new ImagePanel("src/images/pg principali/ELFO/ELFOBARBARO.png", 200, 200);
                            break;

                        default:
                            immagine = new ImagePanel(classe, 200, 200);
                            break;
                    }
                    break;

                default:
                    immagine = new ImagePanel("src/images/pg principali/ELFO/image (12).png", 200, 200);
                    break;

            }
            return immagine;

    }

    private String toStringRazza(Razza razza) {return razza + "";}
    private String toStringClasse(Classe classe) {return classe + "";}

    private void setupButton(JButton button, int x, int y, Font font){
        button.setBounds(x, y, 200, 50);
        button.setFont(font);
        button.setFocusPainted(false);
        add(button);
    }

    private void startFadeIn(){
        Timer timer = new Timer(100, new ActionListener() {
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
    private ImageIcon scaleImage(String imagePath, int width, int height) {
        ImageIcon originalIcon = new ImageIcon(imagePath);
        Image scaledImage = originalIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }

    private void scalePanels(int characterWidth, int characterHeight, int enemyWidth, int enemyHeight) {
        characterPanel.setScaledSize(characterWidth, characterHeight);
        enemyPanel.setScaledSize(enemyWidth, enemyHeight);
    }

    private class ImagePanel extends JPanel{
        private Image image;
        private float opacity = 0f;

        public ImagePanel(String imagePath, int width, int height) {
            super();
            try{
                image = new ImageIcon(imagePath).getImage().getScaledInstance(width, height, image.SCALE_SMOOTH);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            setOpaque(false);
        }

        public void setOpacity(float opacity) {
            this.opacity = opacity;
            repaint();
        }

        public void setScaledSize(int width, int height) {
            image = image.getScaledInstance(width, height, image.SCALE_SMOOTH);
            repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,opacity));
            g2d.drawImage(image, 0, 0, null);
            g2d.dispose();
        }
    }

    private JProgressBar createHealthBar(int health) {
        JProgressBar healthBar = new JProgressBar(0, health);
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

            setVisible(false);
            FrameGame.getMessaggi().setText("NON PUOI COMBATTERE " + Dungeon.getGiocatore().getNemico().getNome() + " NON HAI UN ARMA");
        }

        /**--- ALLA MORTE DI UNO DEI DUE SCOMPARE LA FINESTRA ---**/
        if(!Dungeon.getGiocatore().isVivo()){

            FrameGame.getMessaggi().setText("MI DISPIACE MIO PRODE AVVENTURIERO " + Dungeon.getGiocatore().getNome() + " SEI MORTO IN QUEST'AVVENTURA");
        }
        if(!Dungeon.getGiocatore().getNemico().isVivo()){

            FrameGame.getMessaggi().setText("MIO PRODE AVVENTURIERO " + Dungeon.getGiocatore().getNome() + " HAI SCONFITTO " + Dungeon.getGiocatore().getNemico().getNome());

        }

        /**--- CAMBIO LE STATS SULLA FINESTRA PRINCIPALE (OPZIONALE NON BELLISSIMO VEDI TU GIAN :) ) ---**/
        if(Dungeon.getGiocatore().isVivo() && Dungeon.getGiocatore().getNemico().isVivo()) {

            FrameGame.getStatistiche().setText(Giocatore.statsToString(Dungeon.getGiocatore()) + "\n" + Giocatore.statsToString(Dungeon.getGiocatore().getNemico()));
        }else{
            FrameGame.getStatistiche().setText(Giocatore.statsToString(Dungeon.getGiocatore()));
            setVisible(false);
        }
    }

}
