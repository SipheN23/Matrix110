import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class GameBackground extends JPanel {
    private Image backgroundImage;

    public GameBackground() {
        try {
            // Load the background image 
            backgroundImage = ImageIO.read(new File("brick breaker matrix v1.1.jpeg")); // Relative path from github
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the background image
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
