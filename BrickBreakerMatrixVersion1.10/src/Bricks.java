import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;

public class Bricks {
    private Point position;
    private Color color;
    private int strength;
    private boolean countedForScore;

    public Bricks(Point pos, Color color, int strength) {
        this.position = pos;
        this.color = color;
        this.strength = strength;
        this.countedForScore = false;
    }

    public Point getPosition() {
        return position; // Return position of the brick
    }

    public Color getColor() {
        return color; // Return color of the brick
    }

    public boolean isDestroyed() {
        return strength <= 0; // Check if the brick is destroyed
    }

    public void hit() {
        if (strength > 0) {
            strength--; // Decrease strength
        }
    }
    
    public boolean isCountedScore(){
        return countedForScore; // Return the value of countedScore
    }
    //Method to set the flag countedForScore flag
    public void setCountedForScore(boolean countedForScore){
        this.countedForScore = countedForScore; // Update the countedForScore with provided value
    }

    public Rectangle getBounds() {
        return new Rectangle(position.x, position.y, 50, 30); // Return bounds for collision detection
    }

    
}
