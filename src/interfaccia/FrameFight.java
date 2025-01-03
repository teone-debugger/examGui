package interfaccia;

import gioco.Dungeon;
import gioco.Giocatore;
import gioco.Razza;
import gioco.Classe;
import messaggi.Messaggio;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
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

    private JLabel characterHPLabel;
    private JLabel enemyHPLabel;

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
        BlurredEllipsePanel ellipsePanel = new BlurredEllipsePanel(Color.WHITE, 0.2F, 40);
        ellipsePanel.setBounds(characterPanel.getX()-50, characterPanel.getY()-50, 300, 300);

        enemyPanel = getNemico();
        enemyPanel.setBounds(500, 150, 200,  200);
        BlurredEllipsePanel ellipsePanel2 = new BlurredEllipsePanel(Color.WHITE, 0.2f, 40);
        ellipsePanel2.setBounds(enemyPanel.getX()-50, enemyPanel.getY()-50, 300, 300);

        scalePanels(200, 200, 200, 200);

        //barre della vita
        charactHealthBar = createHealthBar(Dungeon.getGiocatore().getPuntiVitaMAX(), Dungeon.getGiocatore().getPuntiVita());
        charactHealthBar.setBounds(50, 230, characterPanel.getWidth(), 30);
        updateHealthBarColor(charactHealthBar);

        enemyHealthBar = createHealthBar(Dungeon.getGiocatore().getNemico().getPuntiVita(), Dungeon.getGiocatore().getNemico().getPuntiVita());
        enemyHealthBar.setBounds(500, 130, enemyPanel.getWidth(), 30);

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

        Font bodyFont;
        try {
            bodyFont = Font.createFont(Font.TRUETYPE_FONT, new java.io.File("src/font/Perfect DOS VGA 437.ttf")).deriveFont(14f);
            GraphicsEnvironment te = GraphicsEnvironment.getLocalGraphicsEnvironment();
            te.registerFont(bodyFont);
        }
        catch (Exception e) {
            e.printStackTrace();
            bodyFont = new Font("Serif", Font.PLAIN, 20);
        }

        //etichette nome
        charactNameLabel = createNameLabel(Dungeon.getGiocatore().getNome(), alagardFont);
        positionNameLabel(charactNameLabel, charactHealthBar);
        characterHPLabel = createHPLabel(Dungeon.getGiocatore().getPuntiVitaMAX(), Dungeon.getGiocatore().getPuntiVita(), bodyFont);
        characterHPLabel.setBounds(charactHealthBar.getX() - 50, charactHealthBar.getY(), 50, 30);

        enemyNameLabel = createNameLabel(Dungeon.getGiocatore().getNemico().getNome(), alagardFont);
        positionNameLabel(enemyNameLabel, enemyHealthBar);
        enemyHPLabel = createHPLabel(Dungeon.getGiocatore().getNemico().getPuntiVitaMAX(), Dungeon.getGiocatore().getNemico().getPuntiVita(), bodyFont);
        enemyHPLabel.setBounds(enemyHealthBar.getX() - 50, enemyHealthBar.getY(), 50, 30);

        
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

                charactHealthBar.setValue(Dungeon.getGiocatore().getPuntiVita());
                updateHealthBarColor(charactHealthBar);
                updateCharacterHPLabel(Dungeon.getGiocatore().getPuntiVita(), Dungeon.getGiocatore().getPuntiVitaMAX());
            }
        });
        
        fuga = new JButton("FUGA");
            setupButton(fuga, 550, 500, alagardFont);

        fuga.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrameGame.getMessaggi().setText("MIO PAVIDO AVVENTURIERO, SEI FUGGITO DA '" + Dungeon.getGiocatore().getNemico().getNome() + "'");
                handleFuga();
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

        add(ellipsePanel);
        add(ellipsePanel2);

        add(backgroundLabel);

        //animazione fade in
        startFadeIn();

        //settings
        //settings();
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        setVisible(true);

    }

    private void handleDeath(boolean isDead, String nome) {
        String message;
        String hintImagePath = "src/images/background/enter-button-sticker-pixel-art-260nw-1988589401.jpg";

        if(isDead) {
            message = "Purtroppo mio giovane " + nome + " sei morto, ti ricorderemo calorosamente.... forse";
        } else {
            message = "Mio prode avventuriero, hai sconfitto " + nome + "!!!";
        }

        EventScreen eventScreen = new EventScreen(message, hintImagePath);
        eventScreen.showScreen(this);
    }

    private void handleNoArmi() {
        String message = "Pensavi di combattere senza nessuna arma per caso?? AHAHAHA";
        String hintImagePath = "src/images/background/enter-button-sticker-pixel-art-260nw-1988589401.jpg";

        EventScreen eventScreen = new EventScreen(message, hintImagePath);
        eventScreen.showScreen(this);
    }

    private void handleFuga(){
        String message = "Qualcuno qua se l'è fatta sotto AHAHAHAHA";
        String hintImagePath = "src/images/background/enter-button-sticker-pixel-art-260nw-1988589401.jpg";

        EventScreen eventScreen = new EventScreen(message, hintImagePath);
        eventScreen.showScreen(this);
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

    private ImagePanel getNemico() {
        ImagePanel immagine;
        int randomNumber = (int) (Math.random()*6)+1;

        switch(randomNumber){
            case 0:
                immagine = new ImagePanel("src/images/nemici/ghost.png", 300, 300);
                break;

            case 1:
                immagine = new ImagePanel("src/images/nemici/image.png", 300, 300);
                break;

            case 2:
                immagine = new ImagePanel("src/images/nemici/image (1).png", 300, 300);
                break;

            case 3:
                immagine = new ImagePanel("src/images/nemici/image (2).png", 300, 300);
                break;

            case 4:
                immagine = new ImagePanel("src/images/nemici/image (3).png", 300, 300);
                break;

            case 5:
                immagine = new ImagePanel("src/images/nemici/image (9).png", 300, 300);
                break;

            case 6:
                immagine = new ImagePanel("src/images/nemici/image (10).png", 300, 300);
                break;

            default:
                immagine = new ImagePanel("src/images/nemici/image (21).png", 300, 300);
                break;
        }

        randomNumber = 0;
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

            /**--- GENERA EXCEPTION ---**/
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,opacity));

            g2d.drawImage(image, 0, 0, null);
            g2d.dispose();
        }
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

    public class BlurredEllipsePanel extends JPanel {
        private final Color color;
        private final float opacity;
        private final int blurRadius;

        public BlurredEllipsePanel(Color color, float opacity, int blurRadius) {
            this.color = new Color(color.getRed(), color.getGreen(), color.getBlue(), (int) (opacity * 255));
            this.opacity = opacity;
            this.blurRadius = blurRadius;
            setOpaque(false);
        }

        @Override
        
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            int width = getWidth();
            int height = getHeight();

            BufferedImage ellipseImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = ellipseImage.createGraphics();

            g2d.setColor(color);
            g2d.fillOval(blurRadius, blurRadius, width-2*blurRadius, height-2*blurRadius);
            g2d.dispose();

            BufferedImage blurredImage = applyGaussianBlur(ellipseImage,blurRadius);

            g.drawImage(blurredImage, 0, 0, null);
        }

        private BufferedImage applyGaussianBlur(BufferedImage src, int radius)  {
            int size = radius * 2 + 1;
            float[] weights = new float[size * size];
            float sigma = radius / 3.0f;
            float sum = 0f;

            for (int y = -radius; y<= radius; y++) {
                for(int x = -radius; x<= radius; x++) {
                    float weight = (float) Math.exp(-(x*x + y*y) / (2*sigma*sigma));
                    weights[(y+radius)*size + (x+radius)] = weight;
                    sum += weight;
                }
            }

            for (int i = 0; i< weights.length; i++) {
                weights[i] /= sum;
            }

            Kernel kernel = new Kernel(size, size, weights);
            ConvolveOp op = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);
            return op.filter(src, null);
        }
    }

    private void actionAttacca(){

        /**--- ATTACCA SE HO UN'ARMA ---**/
        if(Giocatore.getArma() != null) {

            Dungeon.getGiocatore().fightDinamic(Dungeon.getGiocatore().getNemico(), turnoGiocatore, turnoPng);
            FrameGame.getMessaggi().setText(Messaggio.getMessaggio());
        }else{

            handleNoArmi();
            setVisible(false);
            FrameGame.getMessaggi().setText("NON PUOI COMBATTERE " + Dungeon.getGiocatore().getNemico().getNome() + " NON HAI UN ARMA");
        }

        /**--- ALLA MORTE DI UNO DEI DUE SCOMPARE LA FINESTRA ---**/
        if(!Dungeon.getGiocatore().isVivo()){

            handleDeath(Dungeon.getGiocatore().isVivo(), Dungeon.getGiocatore().getNome());
            FrameGame.getMessaggi().setText("MI DISPIACE MIO PRODE AVVENTURIERO " + Dungeon.getGiocatore().getNome() + " SEI MORTO IN QUEST'AVVENTURA");
        }
        if(!Dungeon.getGiocatore().getNemico().isVivo()){

            handleDeath(Dungeon.getGiocatore().isVivo(), Dungeon.getGiocatore().getNemico().getNome());
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
