package interfaccia;

import javax.swing.*;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class EventScreen extends JLayeredPane {
    private JLabel messageLabel;
    private JLabel hintLabel;
    private JLabel messaggioBase;
    private JLabel gameOverLabel;

    public EventScreen(String message) {
        setLayout(null);
        setFocusable(true);

        Font bodyFont;
        try {
            bodyFont = Font.createFont(Font.TRUETYPE_FONT, new java.io.File("src/font/Perfect DOS VGA 437.ttf")).deriveFont(17f);
            GraphicsEnvironment te = GraphicsEnvironment.getLocalGraphicsEnvironment();
            te.registerFont(bodyFont);
        }
        catch (Exception e) {
            e.printStackTrace();
            bodyFont = new Font("Serif", Font.PLAIN, 25);
        }

        Font gameOverFont;
        try {
            gameOverFont = Font.createFont(Font.TRUETYPE_FONT, new java.io.File("src/font/alagard.ttf")).deriveFont(25f);
            GraphicsEnvironment ye = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ye.registerFont(gameOverFont);
        }
        catch (Exception e) {
            e.printStackTrace();
            gameOverFont = new Font("Serif", Font.PLAIN, 15);
        }
        

        messaggioBase = new JLabel("UNA VOCE LONTANA RIECHEGGIA NEL DUNGEON:", SwingConstants.CENTER);
        messaggioBase.setForeground(Color.RED);
        messaggioBase.setFont(bodyFont.deriveFont(15f));
        messaggioBase.setBounds(100, 80, 600, 100);
        add(messaggioBase);

        messageLabel = new JLabel(message, SwingConstants.CENTER);
        messageLabel.setForeground(Color.WHITE);
        messageLabel.setFont(bodyFont);
        messageLabel.setBounds(100, 200, 600, 100);
        add(messageLabel);

        String hint = "PREMI 'ENTER' PER USCIRE";
        hintLabel = new JLabel(hint, SwingConstants.CENTER);
        hintLabel.setBounds(295, 400, 200, 50);
        hintLabel.setFont(bodyFont.deriveFont(13f));
        add(hintLabel);

        gameOverLabel = new JLabel("Game Over", SwingConstants.CENTER);
        gameOverLabel.setFont(gameOverFont);
        gameOverLabel.setForeground(Color.RED);
        gameOverLabel.setBounds(100, 200, 600, 300);
        add(gameOverLabel);
        gameOverLabel.setVisible(false);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    closeScreen();
                }
            }
        });
    }

    @Override

    protected void paintComponent (Graphics g){
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());
    }

    public void showScreen(JFrame parentFrame) {
        this.setBounds(0, 0, parentFrame.getWidth(), parentFrame.getHeight());
        parentFrame.setLayeredPane(this);
        parentFrame.revalidate();
        parentFrame.repaint();
        SwingUtilities.updateComponentTreeUI(this);
        requestFocusInWindow();
    }

    public void gameOver() {
        messaggioBase.setVisible(false);
        messageLabel.setVisible(false);
        gameOverLabel.setVisible(true);
    }

    private void closeScreen() {
        Window parentWindow = SwingUtilities.getWindowAncestor(this);
        if(parentWindow != null) {
            parentWindow.dispose();
        }
    }
}