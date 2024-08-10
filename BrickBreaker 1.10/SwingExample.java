import javax.swing.*;

public class SwingExample {
    public static void main(String[] args){
        JFrame frame = new JFrame("Swing Example");     //Creating a JFrame window

        JButton button = new JButton("Click Me");       //Creating a Button "click me"
        
        //add an action listener to the button to handle click events
        button.addActionListener(e -> System.out.println("Button Clicked"));

        frame.add(button);      //adds button to the frame
        frame.setSize(500, 400); //set the frame size
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);        //makes the frame visible
    }
}