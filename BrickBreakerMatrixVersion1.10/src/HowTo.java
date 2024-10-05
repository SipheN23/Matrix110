import javax.swing.*;
import java.awt.*;

// this class dislapys text with info on how to play the game
public class HowTo {

    private JFrame frame;
    private JLabel label;

    public HowTo() {
   
        frame = new JFrame("How To Play");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLocationRelativeTo(null); 

        
        label = new JLabel("<html>"
        +"<h2>How to Play:</h2>"
        +"<p>⬆: Press the Up arrow to the start the game</p>"
        +"<p> ⬅️ ➡️: Use the Left and Right arrows to move the paddle</p"
        +"<p>P: Press 'P' to pause the game</p>"
        +"</html>");
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setFont(new Font("Arial", Font.PLAIN,16);

   
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(label, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}

