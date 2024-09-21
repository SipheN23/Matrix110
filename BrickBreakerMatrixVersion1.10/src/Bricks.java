import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;

public class Bricks {
    private Point Position; 
    private Color colour; 
    private int strength;

    public Bricks(Point pos, Color colour, int strength) {
        this.Position = pos; 
        this.colour = colour; 
        this.strength = strength;
    }

    public Point getPosition() {
        return Position; 
    }

    public void setPosition(Point position) {
        this.Position = position; 
    }

    public Color getColor() {
        return colour; 
    }

    public void setColor(Color color) {
        this.colour = color; 
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public void hit() {
        if (strength > 0) {
            strength--;
        }
    }

    public boolean isDestroyed() {
        return strength <= 0;
    }

    public Rectangle getBounds() {
        return new Rectangle(Position.x, Position.y, 50, 30); 
    }
}

