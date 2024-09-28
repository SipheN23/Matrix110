import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

public class Gameplay extends JPanel implements KeyListener {
    private Paddle paddle; 
    private List<Bricks> bricks; 

    // Constructor for the Gameplay class
    public Gameplay() {
        paddle = new Paddle(450, 700); // Initialize the paddle at a specific position
        addKeyListener(this); 
        setFocusable(true); 
        setFocusTraversalKeysEnabled(false); 
        bricks = new ArrayList<>(); 
        initializeBricks(); 

        levelTimer = new GameTimer(60);  // Initialise the game timer
        levelTimer.start();

        gameUpdateTimer = new Timer(16, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                updateGame();
                repaint();
            }
        });
        gameUpdateTimer.start();
    }

    // Method to initialize the bricks
    private void initializeBricks() {
        bricks.clear(); // Clear the list before adding new bricks
        int numRows = 5; 
        int numCols = 10; 
        int brickWidth = 50; 
        int brickHeight = 30; 
        int horizontalSpacing = 10; // Space between bricks horizontally
        int verticalSpacing = 10; // Space between bricks vertically

        //Calculate total width of the grid
        int totalBrickWidth = numCols * (brickWidth + horizontalSpacing); // Total width of all bricks
        int windowWidth = 1000; // Width of the game window

        // Calculate starting X position to center the grid
        int startX = (windowWidth - totalBrickWidth) / 2; 

        // Set the top Y position where the first row starts
        int startY = 100; //Adjust as needed to vertically position bricks

        // Arrange bricks in a grid
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                //Calculate position for each brick
                int x = startX + col * (brickWidth + horizontalSpacing); // Calculate X position
                int y = startY + row * (brickHeight + verticalSpacing); // Calculate Y position
                bricks.add(new Bricks(new Point(x, y), Color.RED, 1)); // Add a new brick to the list
            }
        }
    }

    // Method to draw the bricks 
    private void drawBricks(Graphics g) {
        for (Bricks brick : bricks) {
            if (!brick.isDestroyed()) { // Check if the brick is not destroyed
                g.setColor(brick.getColor()); 
                g.fillRect(brick.getPosition().x, brick.getPosition().y, 50, 30); // Draw the brick
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g); 
        paddle.draw(g); // Call the draw method to draw the paddle
        drawBricks(g); // Call the drawBricks method to draw the bricks
    }

    
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    
    @Override
    public void keyPressed(KeyEvent e) {
        // Check if the right arrow key is pressed
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) { 
            if (paddle.getPaddleXCoord() < 1000 - paddle.getPaddleWidth()) {
                paddle.moveRight(); // Move the paddle to the right
            } else {
                paddle.setPaddleBounds(1000 - paddle.getPaddleWidth()); // Keep paddle within bounds
            }
         // Check if the left arrow key is pressed
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (paddle.getPaddleXCoord() > 0) {
                paddle.moveLeft(); // Move the paddle to the left
            } else {
                paddle.setPaddleBounds(0); // Keep paddle within bounds
            }
        }
        repaint(); // Update the display
    }
}
