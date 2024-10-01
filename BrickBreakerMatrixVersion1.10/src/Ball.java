import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.List;

public class Ball {
    private int x, y;
    private int diameter;
    private int xVelocity;
    private int yVelocity;
    private Color color;
    private boolean launched = false;

    // Constructor
    public Ball(int x, int y, int diameter) {
        this.x = x;
        this.y = y;
        this.diameter = diameter;
        this.color = Color.WHITE; // default ball color is white
    }

    public void move() {
        if (launched) {
            this.x += this.xVelocity; // Update X position
            this.y += this.yVelocity; // Update Y position
        }
    }

    //Method to check for collisions with walls
    public void checkWallCollision(int screenWidth, int screenHeight) {
        if (this.x <= 0 || this.x + this.diameter >= screenWidth) {
            reverseXVelocity(); // Reverse X velocity on wall collision
        }
        if (this.y <= 0) {
            reverseYVelocity(); // Reverse Y velocity on top wall collision
        }
        // if (y + this.diameter >= screenHeight) {
        //     // End game or decrease lives when the ball goes past the paddle
        // }
    }

    //Method to check brick collison
    public void checkBrickCollision(List<Bricks> bricks) {
        for (Bricks brick : bricks) {
            if (!brick.isDestroyed() && getBounds().intersects(brick.getBounds())) {
                brick.hit(); // Hit the brick
                reverseYVelocity(); // Reverse Y velocity on collision
                break;
            }
        }
    }

    //Method to check paddle collison
    public void checkPaddleCollision(Paddle paddle) {
        // Check if the ball is at or below the paddle's Y position and within the paddle's width
        if (getBounds().intersects(new Rectangle(paddle.getPaddleXCoord(), paddle.getPaddleYCoord(), paddle.getPaddleWidth(), paddle.getPaddleHeight()))) {
            reverseYVelocity(); // Reverse Y velocity on collision with paddle
            this.y = paddle.getPaddleYCoord() - this.diameter; // Adjust ball position above paddle
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(this.x, this.y, this.diameter, this.diameter); // Return bounds for collision detection
    }

    public void reverseYVelocity() {
        this.yVelocity = -this.yVelocity; // Reverse Y velocity of the ball
    }

    public void reverseXVelocity() {
        this.xVelocity = -this.xVelocity; // Reverse X velocity of the ball
    }

    public void draw(Graphics g) {
        g.setColor(this.color);
        g.fillOval(this.x, this.y, this.diameter, this.diameter); // Draw the ball
    }

    public void launch() {
        this.xVelocity = 4; // Set horizontal velocity
        this.yVelocity = -8; // Set vertical velocity
        this.launched = true; // Set launched flag to true
    }

    public void resetPosition(int newX, int newY) {
        this.x = newX; // Set new X position
        this.y = newY; // Set new Y position
        this.xVelocity = 0; // Reset horizontal velocity
        this.yVelocity = -10; // Reset vertical velocity
    }
}