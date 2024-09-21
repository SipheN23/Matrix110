import java.awt.Point;
import java.awt.Color;
import java.awt.Rectangle;

public class Bricks {

    private Point Postion;
    private Color colour;
    private int strength;


    public Bricks(Point pos, Color colour, int strength){
        Postion = pos;
        this.colour = colour;
        this.strength = strength;
    }


    public Point getPosition(){
        return Postion;
    }

    public void setPositon(Point postion){
        this.Postion = postion;
       
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
