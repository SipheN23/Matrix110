import java.awt.Color;
import java.awt.Graphics;

public class Paddle {
    private int paddleXCoord;
    private int paddleYCoord;
    private int paddleWidth = 100;
    private int paddleHeight = 8;

    // Constructor to initialize paddle position
    public Paddle(int startXCoord, int startYCoord) {
        this.paddleXCoord = startXCoord;
        this.paddleYCoord = startYCoord;
    }

    // Method to move the paddle to the right
    public void moveRight() {
        this.paddleXCoord += 20; // Move paddle right
    }

    // Method to move the paddle to the left
    public void moveLeft() {
        this.paddleXCoord -= 20; // Move paddle left
    }

    public void draw(Graphics g) {
        g.setColor(Color.GREEN); // Set the color to green
        g.fillRect(this.paddleXCoord, this.paddleYCoord, this.paddleWidth, this.paddleHeight);// Draw the paddle
    }
    // Accessor for the paddle's X coordinate
    public int getPaddleXCoord() {
        return this.paddleXCoord;
    }
    // Accessor for the paddle's Y coordinate
    public int getPaddleYCoord(){
        return this.paddleYCoord;
    }
    // Accessor for the paddle's width
    public int getPaddleWidth() {
        return this.paddleWidth; 
    }
    // Accessor for the paddle's height
    public int getPaddleHeight() {
        return this.paddleHeight; 
    }//Accessors


    public void setPaddleBounds(int xCoords) {
        this.paddleXCoord = xCoords; 
    }//Setter
    
}