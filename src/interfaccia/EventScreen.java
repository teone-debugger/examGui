package interfaccia;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class EventScreen extends JPanel{
    private JLabel messageLabel;
    private JLabel hintLabel;

    public EventScreen(String message, String hintImagePath) {
        setLayout(null);
        setBackground(new Color(0, 0, 0, 200));
        setFocusable(true);

        Font bodyFont;
        try {
            bodyFont = Font.createFont(Font.TRUETYPE_FONT, new java.io.File("src/font/Perfect DOS VGA 437.ttf")).deriveFont(30f);
            GraphicsEnvironment te = GraphicsEnvironment.getLocalGraphicsEnvironment();
            te.registerFont(bodyFont);
        }
        catch (Exception e) {
            e.printStackTrace();
            bodyFont = new Font("Serif", Font.PLAIN, 20);
        }

        messageLabel = new JLabel(message, SwingConstants.CENTER);
        messageLabel.setForeground(Color.WHITE);
        messageLabel.setFont(bodyFont);
        messageLabel.setBounds(100, 200, 600, 100);
        add(messageLabel);

        hintLabel = new JLabel(new ImageIcon(hintImagePath));
        hintLabel.setBounds(350, 400, 100, 100);
        add(hintLabel);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    closeScreen();
                }
            }
        });
    }

    public void showScreen(JFrame parentFrame) {
        parentFrame.getContentPane().add(this);
        parentFrame.revalidate();
        parentFrame.repaint();
        requestFocusInWindow();
    }

    private void closeScreen() {
        Container parent = getParent();
        if(parent != null) {
            parent.remove(this);
            parent.revalidate();
            parent.repaint();
        }
    }
}
