import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

//This class creates the initial game "Play" option and image
public class Launch implements ActionListener {
    private JFrame frame;
    private JButton playButton;
    private Image backgroundImage;
    private static final int WINDOW_WIDTH = 800; // Set the width of the window
    private static final int WINDOW_HEIGHT = 600; // Set the height of the window
    Font MatrixFont;

    public Launch() {
        // Load the background image
        try {
            backgroundImage = ImageIO.read(new File("BrickBreakerMatrixVersion1.10/src/brick breaker matrix v1.1.jpeg")); // Relative path from github file
        } catch (IOException e) {
            e.printStackTrace();
        }

        //creation of the matrix font and registering it
        try{
            MatrixFont = Font.createFont(Font.TRUETYPE_FONT, new File("BrickBreakerMatrixVersion1.10/src/Matrix-MZ4P.ttf")).deriveFont(30f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("BrickBreakerMatrixVersion1.10/src/Matrix-MZ4P.ttf")));
            
        } catch(Exception e){
            e.printStackTrace();
        }//end of try catch

        // Create the frame and button
        frame = new JFrame();
        playButton = new JButton("PLAY");
        playButton.setFont(MatrixFont);

        // Create a JPanel for the background
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) {
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };


        //adding custom game name font
        

        panel.setLayout(null); // Use null layout to manually position components
        playButton.setBounds(WINDOW_WIDTH / 2 - 100, WINDOW_HEIGHT / 2 - 20, 200, 40); // Center the button
        playButton.setFocusable(false);
        playButton.addActionListener(this);
        panel.add(playButton);

        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setLocationRelativeTo(null); // Center the window on the screen
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == playButton) {
            frame.dispose(); // Close the launch window
            Game.main(new String[]{}); // Start the game by calling the main method of the Game class
        }
    }

    public static void main(String[] args) {
        new Launch(); // Start the launch screen
    }
    
}
