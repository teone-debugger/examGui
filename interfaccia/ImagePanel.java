 package interfaccia;

import javax.swing.*;
import java.awt.*;
 
 
 public class ImagePanel extends JPanel{
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
            try{
                g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,opacity));
            }catch(Exception e){
            }

            g2d.drawImage(image, 0, 0, null);
            g2d.dispose();
        }
    }