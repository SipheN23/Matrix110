package game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

//This class creates the initial game "Play" option and image
public class Launch implements ActionListener {

    JFrame frame = new JFrame();
    JButton myButton = new JButton("PLAY");
    Image backgroundImage;

    Launch() {
        // Load the background image
        try {
            backgroundImage = ImageIO.read(new File("C:\\Users\\HP\\eclipse-workspace\\Matrix110shumi\\src\\game\\MenuBackground.jpg"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Creating a JPanel for the background
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) {
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);
                }
            }
        };
        panel.setLayout(null); // Null layout to manually position components

        // The play button properties
        myButton.setBounds(100, 160, 200, 40);
        myButton.setFocusable(false);
        myButton.addActionListener(this);

        //Play button added to the JPanel
        panel.add(myButton);
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 420);
        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == myButton) {
            frame.dispose();
            new GameBackground(); // This then launches the next image as the game background
        }
    }
}