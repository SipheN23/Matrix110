import java.awt.Color; 
import java.awt.Graphics; 

public class Paddle {
    private int paddleXCoord; // X coordinate of the paddle
    private int paddleYCoord; // Y coordinate of the paddle
    private int paddleWidth = 100; // Width of the paddle
    private int paddleHeight = 8; // Height of the paddle

    // Constructor to initialize paddle position
    public Paddle(int startXCoord, int startYCoord) {
        this.paddleXCoord = startXCoord;
        this.paddleYCoord = startYCoord;
    }

    // Method to move the paddle to the right
    public void moveRight() {
        paddleXCoord += 20; // Increment X coordinate
    }

    // Method to move the paddle to the left
    public void moveLeft() {
        paddleXCoord -= 20; // Decrement X coordinate
    }

    // Method to draw the paddle on the screen
    public void draw(Graphics g) {
        g.setColor(Color.green); // Set the color to green
        g.fillRect(paddleXCoord, paddleYCoord, paddleWidth, paddleHeight); // Draw the paddle
    }

    // Accessor for the x coordinate of the paddle
    public int getPaddleXCoord() {
        return paddleXCoord;
    }

    // Accessor for the y coordinate of the paddle
    public int getPaddleYCoord() {
        return paddleYCoord;
    }

    // Method to set the paddle's X coordinate
    public void setPaddleBounds(int xCoords) {
        this.paddleXCoord = xCoords; // Set the X coordinate
    }

    // Accessor for the paddle's width
    public int getPaddleWidth() {
        return paddleWidth;
    }
}