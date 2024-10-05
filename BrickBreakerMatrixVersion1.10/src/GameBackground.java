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


        iButton = new JButton("i");
        iButton.setFont(new Font("Serif", Font.BOLD, 13));
        iButton.setBounds(0, 0, 40, 40); // Position the button
        iButton.setFocusable(false);
        iButton.addActionListener(this);

        // Setup the panel
        this.setLayout(null); // Use null layout for absolute positioning
        this.add(iButton); // Add the button to the panel
        
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the background image
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() = Button){
            //Create and show HowTo dialog or frame
            HowTo ht = new HowTo();
        }
    }
}
