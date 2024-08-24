import javax.swing.*;

public class SwingExample1 {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Menu");

        JButton startButton = new JButton("Start"); // This is a start button

        // When you press the button, a blank screen will appear
        startButton.addActionListener(e -> {
            frame.getContentPane().removeAll(); 
            frame.repaint(); 
        });

        frame.add(startButton); // Add the button
        frame.setSize(500, 400); // Size of the window
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true); // Show 
    }
}
