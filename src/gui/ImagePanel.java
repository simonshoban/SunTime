package gui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import org.apache.commons.io.FileUtils;

public class ImagePanel extends JPanel {
    
    private BufferedImage image;
    
    public ImagePanel(String fileLocation) {
        try {
            URL url = new URL(fileLocation);
            File file = new File("not_null");
            
            FileUtils.copyURLToFile(url, file);
            image = ImageIO.read(file);
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
    
    public BufferedImage getImage() {
        return image;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }

}
