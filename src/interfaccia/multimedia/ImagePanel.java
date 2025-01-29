package interfaccia.multimedia;

import javax.swing.*;

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
 
 
 public class ImagePanel extends JPanel{
        private Image image;
        private float opacity = 0f;


        public ImagePanel(){
            super();
        }

        //Metodo costruttore
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

        //Metodo per mettere l'opacita nelle immagini
        public void setOpacity(float opacity) {
            this.opacity = opacity;
            repaint();
        }

        //Metodo per scalare le immagini
        public void setScaledSize(int width, int height) {
            image = image.getScaledInstance(width, height, image.SCALE_SMOOTH);
            repaint();
        }

        //Metodo per overrride paintComponent
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
        
        public static void serialize(ImagePanel posizione, String filePath) {
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
                oos.writeObject(posizione);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public static ImagePanel deserialize(String filePath) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
                return (ImagePanel) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
                return null;
            }
        }
}