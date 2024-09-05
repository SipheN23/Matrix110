import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Gameplay extends JPanel implements KeyListener, ActionListener {
    private boolean play = false; // Makes game not to play by itself when it starts
    private int playerPos = 310; // The starting position of the player's paddle
    private int ballPosX = 120; // The x coordinate of the ball's starting position
    private int ballPosY = 350; // The y coordinate of the ball's starting position
    private Timer ballSpeedTimer; // The timer used to set how fast the ball should move
    private int timerDelay = 8; // The speed of the timer

    public Gameplay() {
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        ballSpeedTimer = new Timer(timerDelay, this);
        ballSpeedTimer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    // Method that receives the graphics object and uses it to draw things like the paddle and ball
    public void draw(Graphics g) {

        // Background
        g.setColor(Color.black);
        g.fillRect(0, 0, 1000, 800); 

        // Borders
        g.setColor(Color.yellow);
        g.fillRect(0, 0, 3, 800);
        g.fillRect(0, 0, 1000, 3);
        g.fillRect(997, 0, 3, 800);

        // The paddle
        g.setColor(Color.green);
        g.fillRect(playerPos, 500, 100, 8);

        // The ball
        g.setColor(Color.yellow);
        g.fillOval(ballPosX, ballPosY, 20, 20);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (playerPos >= 900) { // Keep paddle in bounds
                playerPos = 900;
            } else {
                moveRight();
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (playerPos < 10) {
                playerPos = 10; // Keep the paddle in bounds
            } else {
                moveLeft();
            }
        }
    }

    public void moveRight() {
        play = true;
        playerPos += 20;
    }

    public void moveLeft() {
        play = true;
        playerPos -= 20;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint(); // Refreshes the game's display
    }
}