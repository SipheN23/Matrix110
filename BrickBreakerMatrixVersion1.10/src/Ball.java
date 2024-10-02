import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.List;

/**
 * The Ball class represents a ball in the game that moves around the screen,
 * checks for collisions with walls, bricks, and the paddle, and manages its state.
 */
public class Ball {
    private int x, y; // Current position of the ball
    private int diameter; // Diameter of the ball
    private int xVelocity; // Horizontal speed of the ball
    private int yVelocity; // Vertical speed of the ball
    private Color color; // Color of the ball
    private boolean launched = false; // Indicates whether the ball has been launched
    private double speed; // Speed multiplier for ball movement

    /**
     * Constructor to initialize a new Ball instance.
     *
     * @param x        The initial X position of the ball.
     * @param y        The initial Y position of the ball.
     * @param diameter The diameter of the ball.
     */
    public Ball(int x, int y, int diameter) {
        this.x = x;
        this.y = y;
        this.diameter = diameter;
        this.color = Color.WHITE; // Default ball color is white
        this.speed = 1.0; // Default speed multiplier
    }

    /**
     * Moves the ball according to its velocity if it has been launched.
     */
    public void move() {
        if (launched) {
            this.x += this.xVelocity * speed; // Update X position
            this.y += this.yVelocity * speed; // Update Y position
        }
    }

    /**
     * Checks for collisions with the walls of the game window.
     *
     * @param screenWidth  The width of the game window.
     * @param screenHeight The height of the game window.
     */
    public void checkWallCollision(int screenWidth, int screenHeight) {
        // Reverse X velocity on left or right wall collision
        if (this.x <= 0 || this.x + this.diameter >= screenWidth) {
            reverseXVelocity();
        }
        // Reverse Y velocity on top wall collision
        if (this.y <= 0) {
            reverseYVelocity();
        }
        // Optional: handle ball falling below the paddle
        // if (y + this.diameter >= screenHeight) {
        //     // End game or decrease lives when the ball goes past the paddle
        // }
    }

    /**
     * Checks for collisions with the given list of bricks.
     *
     * @param bricks The list of bricks to check for collisions.
     * @return true if a collision occurred, false otherwise.
     */
    public boolean checkBrickCollision(List<Bricks> bricks) {
        for (Bricks brick : bricks) {
            // Check if the brick is not destroyed and intersects with the ball
            if (!brick.isDestroyed() && getBounds().intersects(brick.getBounds())) {
                brick.hit(); // Hit the brick
                reverseYVelocity(); // Reverse Y velocity on collision
                return true; // Collision detected
            }
        }
        return false; // No collision detected
    }

    /**
     * Checks for collisions with the paddle.
     *
     * @param paddle The paddle to check for collisions.
     */
    public void checkPaddleCollision(Paddle paddle) {
        // Check if the ball is at or below the paddle's Y position and within the paddle's width
        if (getBounds().intersects(new Rectangle(paddle.getPaddleXCoord(), paddle.getPaddleYCoord(), paddle.getPaddleWidth(), paddle.getPaddleHeight()))) {
            reverseYVelocity(); // Reverse Y velocity on collision with paddle
            this.y = paddle.getPaddleYCoord() - this.diameter; // Adjust ball position above paddle
        }
    }

    // Getters and Setters
    public int getDiameter() {
        return diameter;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getXVelocity() {
        return xVelocity;
    }

    public void setVelocity(int xVelocity) {
        this.xVelocity = xVelocity;
    }

    public int getYvelocity() {
        return yVelocity;
    }

    public void setYvelocity(int yVelocity) {
        this.yVelocity = yVelocity;
    }

    /**
     * Returns the bounding rectangle for collision detection.
     *
     * @return The bounding rectangle of the ball.
     */
    public Rectangle getBounds() {
        return new Rectangle(this.x, this.y, this.diameter, this.diameter); // Return bounds for collision detection
    }

    /**
     * Reverses the vertical velocity of the ball.
     */
    public void reverseYVelocity() {
        this.yVelocity = -this.yVelocity; // Reverse Y velocity of the ball
    }

    /**
     * Reverses the horizontal velocity of the ball.
     */
    public void reverseXVelocity() {
        this.xVelocity = -this.xVelocity; // Reverse X velocity of the ball
    }

    /**
     * Draws the ball on the given Graphics context.
     *
     * @param g The Graphics context to draw on.
     */
    public void draw(Graphics g) {
        g.setColor(this.color);
        g.fillOval(this.x, this.y, this.diameter, this.diameter); // Draw the ball
    }

    /**
     * Launches the ball with specified velocities.
     */
    public void launch() {
        this.xVelocity = 4; // Set horizontal velocity
        this.yVelocity = -8; // Set vertical velocity
        this.launched = true; // Set launched flag to true
    }

    /**
     * Resets the ball's position and velocity.
     *
     * @param newX The new X position.
     * @param newY The new Y position.
     */
    public void resetPosition(int newX, int newY) {
        this.x = newX; // Set new X position
        this.y = newY; // Set new Y position
        this.xVelocity = 0; // Reset horizontal velocity
        this.yVelocity = -10; // Reset vertical velocity
    }

    /**
     * Increases the speed of the ball.
     *
     * @param increment The amount to increase the speed.
     */
    public void increaseSpeed(double increment) {
        this.speed += increment; // Increase speed by the specified amount
    }

    /**
     * Resets the speed of the ball to the default value.
     */
    public void resetSpeed() {
        this.speed = 1.0; // Reset speed to default
    }
}
