import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener; 
import javax.swing.JPanel; 

public class Gameplay extends JPanel implements KeyListener {
    private Paddle paddle; 

    // Constructor for the Gameplay class
    public Gameplay() {
        paddle = new Paddle(450, 700); // Initialize the paddle at a specific position
        addKeyListener(this); 
        setFocusable(true); 
        setFocusTraversalKeysEnabled(false); 
    }

    // Method to draw the paddle 
    public void draw(Graphics g) {
        paddle.draw(g); // Draw the paddle on the panel
    }

    
    @Override
    public void paint(Graphics g) {
        super.paint(g); 
        draw(g);//Call the draw method to draw the paddle
    }

    
    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    
    @Override
    public void keyReleased(KeyEvent e) {
        
    }

    // Method to handle key press events
    @Override
    public void keyPressed(KeyEvent e) {
        // Check if the right arrow key is pressed
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (paddle.getPaddleXCoord() < 1000 - paddle.getPaddleWidth()) {
                paddle.moveRight(); // Move the paddle to the right
            } else {
                paddle.setPaddleBounds(1000 - paddle.getPaddleWidth()); // Keep paddle in bounds
            }
        } 
        // Check if the left arrow key is pressed
        else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (paddle.getPaddleXCoord() > 0) {
                paddle.moveLeft(); // Move the paddle to the left
            } else {
                paddle.setPaddleBounds(0); // Keep paddle in bounds
            }
        }
        repaint(); // Update the display
    }
}
