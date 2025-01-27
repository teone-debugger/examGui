package interfaccia.framesFight;

import javax.swing.*;

import util.StringUtils;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class EventScreen extends JLayeredPane {
    private JLabel messageLabel;
    private JLabel hintLabel;
    private JLabel messaggioBase;
    private JLabel gameOverLabel;
    private JLabel winLabel;

    //Metodo Costruttore
    public EventScreen(String message) {
        String controllo = message;
        setLayout(null);
        setFocusable(true);
        
        messaggioBase = new JLabel("UNA VOCE LONTANA RIECHEGGIA NEL DUNGEON:", SwingConstants.CENTER);
        messaggioBase.setForeground(Color.RED);
        messaggioBase.setFont(StringUtils.getBodyFont(15f));
        messaggioBase.setBounds(100, 80, 600, 100);
        add(messaggioBase);

        messageLabel = new JLabel(message, SwingConstants.CENTER);
        messageLabel.setForeground(Color.WHITE);
        messageLabel.setFont(StringUtils.getBodyDefaultFont());
        messageLabel.setBounds(100, 200, 600, 100);
        add(messageLabel);

        String hint = "PREMI 'ENTER' PER USCIRE";
        hintLabel = new JLabel(hint, SwingConstants.CENTER);
        hintLabel.setBounds(295, 400, 200, 50);
        hintLabel.setFont(StringUtils.getBodyFont(13f));
        add(hintLabel);

        gameOverLabel = new JLabel("Game Over", SwingConstants.CENTER);
        gameOverLabel.setFont(StringUtils.getGameOverFont(110f));
        gameOverLabel.setForeground(Color.RED);
        gameOverLabel.setBounds(100, 150, 600, 200);
        add(gameOverLabel);
        gameOverLabel.setVisible(false);

        winLabel = new JLabel("HAI VINTO", SwingConstants.CENTER);
        winLabel.setFont(StringUtils.getGameOverFont(110f));
        winLabel.setForeground(Color.YELLOW);
        winLabel.setBounds(100, 150, 600, 200);
        add(winLabel);
        winLabel.setVisible(false);

        if(controllo=="") {
            gameOver();
        }

        if(controllo=="win") {
            winScreen();
        }

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    closeScreen(controllo);
                }
            }
        });
    }

    //Metodo override per colorare lo schermo
    @Override
    protected void paintComponent (Graphics g){
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());
    }

    //Metodo per mostrare le schermate situazionali (fuga, sconfitta, vittoria) 
    public void showScreen(JFrame parentFrame) {
        this.setBounds(0, 0, parentFrame.getWidth(), parentFrame.getHeight());
        parentFrame.setLayeredPane(this);
        parentFrame.revalidate();
        parentFrame.repaint();
        SwingUtilities.updateComponentTreeUI(this);
        requestFocusInWindow();
    }

    //Metodo per settare la scritta sconfitta
    public void gameOver() {
        messaggioBase.setVisible(false);
        messageLabel.setVisible(false);
        gameOverLabel.setVisible(true);
    }

    //Metodo per settare la scritta vittoria
    public void winScreen(){
        messaggioBase.setVisible(false);
        messageLabel.setVisible(false);
        winLabel.setVisible(true);
    }

    //Metodo per chiudere la finestra
    private void closeScreen(String total) {
        Window parentWindow = SwingUtilities.getWindowAncestor(this);
        if(parentWindow != null) {
            parentWindow.dispose();
            if(total=="") {
                Runtime.getRuntime().exit(404);
            }
            if(total=="win") {
                Runtime.getRuntime().exit(404);
            }
        }
    }
}