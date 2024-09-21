import java.awt.Point;
import java.awt.Color;
import java.awt.Rectangle;

public class Bricks {

    private Point Position;
    private Color colour;
    private int strength;


    public Bricks(Point pos, Color colour, int strength){
        Position = pos;
        this.colour = colour;
        this.strength = strength;
    }


    public Point getPosition(){
        return Position;
    }

    public void setPosition(Point position){
        this.Position = position;
       
    }
    public Color getColour(){
        return colour;
    }

    public void setColour(Color colour){
        this.colour = colour;
    }

    public int getStrength(){
        return strength;
    }

    public void setStrength(int strength){
        this.strength = strength;
    }




    public void hit(){
        if( strength > 0){
            strength--;
        }

    }

    public boolean isDestroyed(){
        return strength <=0;

    }

    public Rectangle getBounds(){
        return new Rectangle(Postion.x, Postion.y, 50, 30);
    }




    
}
    
}
