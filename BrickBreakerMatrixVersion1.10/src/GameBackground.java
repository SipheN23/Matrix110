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
            backgroundImage = ImageIO.read(new File("BrickBreakerMatrixVersion1.10/src/_b35486c8-42a6-4f64-b4b0-30c60f537c6c.jpeg")); // Relative path from github
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
