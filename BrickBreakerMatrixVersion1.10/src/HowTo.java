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

        
        label = new JLabel();
        label.setText("^: arrow to start    <>: arrows to move the paddle   P: pause");
        label.setHorizontalAlignment(JLabel.CENTER);

   
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(label, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}

