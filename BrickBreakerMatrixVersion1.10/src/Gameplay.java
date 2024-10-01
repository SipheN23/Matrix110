import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class Gameplay extends GameBackground implements KeyListener {
    private Paddle paddle;
    private List<Bricks> bricks;
    private Ball ball;
    private Timer timer;

    public Gameplay() {
        paddle = new Paddle(450, 700);
        ball = new Ball(500, 650, 20);
        ball.launch(); // Ensure the ball is launched
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        bricks = new ArrayList<>();
        initializeBricks();
        startGame(); // Start the game loop
    }

    public void startGame() {
        timer = new Timer(20, e -> gameLoop()); // Calls gameloop to update game every 20 ms
        timer.start(); // Start the timer
    }

    private void initializeBricks() {
        bricks.clear();// Clear the list before adding new bricks
        int numRows = 5;
        int numCols = 10;
        int brickWidth = 50;
        int brickHeight = 30;
        int horizontalSpacing = 10;// Space between bricks horizontally
        int verticalSpacing = 10;// Space between bricks vertically
        int totalBrickWidth = numCols * (brickWidth + horizontalSpacing);// Total width of all bricks
        int windowWidth = 1000;// Width of the game window
        int startX = (windowWidth - totalBrickWidth) / 2;// Calculate starting X position to center the grid
        int startY = 100;//Adjust as needed to vertically position bricks

        // Arrange bricks in a grid
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                //Calculate position for each brick
                int x = startX + col * (brickWidth + horizontalSpacing);// Calculate X position
                int y = startY + row * (brickHeight + verticalSpacing);// Calculate Y position
                bricks.add(new Bricks(new Point(x, y), Color.RED, 1));// Add a new brick to the list
            }
        }
    }

    private void drawBricks(Graphics g) {
        for (Bricks brick : bricks) {
            if (!brick.isDestroyed()) {
                g.setColor(brick.getColor());
                g.fillRect(brick.getPosition().x, brick.getPosition().y, 50, 30);
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        paddle.draw(g);
        drawBricks(g);
        ball.draw(g);
    }

    private void gameLoop() {
        ball.move(); // Move the ball
        ball.checkWallCollision(1000, 800); // Check for wall collisions
        ball.checkBrickCollision(bricks); // Check for brick collisions
        ball.checkPaddleCollision(paddle); // Check for paddle collisions
        repaint(); // Update the display
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (paddle.getPaddleXCoord() < 1000 - paddle.getPaddleWidth()) {
                paddle.moveRight();
            } else {
                paddle.setPaddleBounds(1000 - paddle.getPaddleWidth());
            }
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (paddle.getPaddleXCoord() > 0) {
                paddle.moveLeft();
            } else {
                paddle.setPaddleBounds(0);
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}
}