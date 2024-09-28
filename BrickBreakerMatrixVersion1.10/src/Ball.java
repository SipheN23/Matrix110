import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.List;




public class Ball {
    private int x, y;
    private int diameter;
    private int xVelocity;
    private int yVelocity;
    private Color color;
    private boolean launched = false;

// Constructor
public Ball(int x, int y, int diameter){
    this.x = x;
    this.y = y;
    this.diameter = diameter;
    this.xVelocity = 0;
    this.yVelocity = 0;
    this.color = Color.WHITE; // default ball color is white
    
}

// Method to move the ball
public void move(){
    if(launched){
    x += xVelocity;
    y += yVelocity;
    }

}

//Method to check for collisions with walls

public void checkWallCollision(int screenWidth, int screenHeight){
    LivesManager life = new LivesManager();
    // Left or right wall collision
    if(x <= 0 || x + diameter >= screenWidth){
        reverseXVelocity();
    }

    // Top wall collision
    if(y <= 0){
        reverseYVelocity();
    }

    //Bottom wall( game over collision)
    if(y + diameter >= screenHeight){
        handleBottomCollision();
        life.loseLife();
    }
}


    // Method to reverse the ball's Y-velocity when a collision happens
public void reverseYVelocity() {
    yVelocity = -yVelocity;
    }

    // Method to reverse the ball's X-velocity (e.g., when it hits side walls)
public void reverseXVelocity() {
    xVelocity = -xVelocity;
    }

public void handleBottomCollision(){
    System.out.println("Ball hit the bottom of the screen!");
}

public void checkPaddleCollision(Paddle paddle){
    // Check if the ball is at or below the paddle's Y position and within the paddle's width
    if (y + diameter >= paddle.getPaddleYCoord() &&
        x + diameter > paddle.getPaddleXCoord() &&
        x < paddle.getPaddleXCoord() + paddle.getPaddleWidth()) {
        
        // Reverse the Y velocity of the ball
        reverseYVelocity();
        
        // Position the ball just above the paddle
        setY(paddle.getPaddleYCoord() - diameter);
    }
}
public void draw(Graphics g){
    g.setColor(color);
    g.fillOval(x,y,diameter,diameter);
}

public void launch(){

        xVelocity = 2;
        yVelocity =-4;
        launched = true;

    }

public void resetPosition(int newX, int newY){
    this.x = newX;
    this.y = newY;
    this.xVelocity = 0;
    this.yVelocity = -10;
}


// Getters and Setters for the ball's position, velocity, and other attributes
public int getX() {
    return x;
}

public int getY() {
    return y;
}

public int getDiameter() {
    return diameter;
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

public void setXVelocity(int xVelocity) {
    this.xVelocity = xVelocity;
}

public int getYVelocity() {
    return yVelocity;
}

public void setYVelocity(int yVelocity) {
    this.yVelocity = yVelocity;
}
    
}

