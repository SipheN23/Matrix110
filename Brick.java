import java.awt.*;  

public  class Brick{

    private Point position;         //declare a point object for brick position
    private Color color;            //declare a color object
    private int strength;           //brickstrength  
    private int width;
    private int height;

    //contructor
    public Brick(){
        this.position = new Point(0, 0);
        this.color = Color.black;
        this.strength = 0;
        this.width = 0;
        this.height = 0;
    }

    //constructor to initialize the brick's properties
    public Brick(Point position, Color color, int strength, int width, int height){
        this.position = position;          //a point for one brick
        this.color = color;                //the color of the brick
        this.strength = strength;          //the strength of the brick
        this.width = width;
        this.height = height;
    }

    //get position
    public Point getPosition(){
        return position;
    }

    //to set positions for other bricks
    public void setPosition(Point coordinates){
        this.position = coordinates;
    }

    //get color
    public Color getColor(){
        return color;
    }
    
    // set Color(to set the initial bricks with their strengths)
    public void setColor(Color color){
        this.color = color;
    }


    //this method allows a brick to break
    public void hitBrick(){
        
        //if the brick is Red make the strength 5 (1 hit to break)
        if(this.color == Color.red){
            this.strength = 5;

        //if the brick is dark red make the strength 15 (3 hits to break)
        }else if(this.color == new Color(178, 0, 0)){
            this.strength = 15; 

        //if the brick is silver make the strength 30 (6 hits to break)    
        }else{
            this.strength = 30;
        }
        
        //for (int i = this.strength; i > 0; i-=5){
         //   if(collides()){
           //     
          //  }
        //}
    }
    // clamp method to restrict bounderies
    private int clamp(int value, int min, int max){
        if(value < min){
            return min;
        }else if(value > max){
            return max;
        }else{
            return value;
        }
        
    }
    //collision detection method for ball and brick
    public boolean collides(){
        //brick's coordinates
        int x1 = this.position.x;
        int y1 = this.position.y;
        int w = this.width;
        int h = this.height;

        //balls coordinates(considering we have the ball's attribute!)
        int Xc = ball.getXvalue();
        int Yc = ball.getYvalue();
        int R = ball.getRadius();
        
        // intersection calculation
        int R_sqrd = R * R;
        int closestX = clamp(Xc, x1, x1+w);
        int closestY = clamp(Yc, y1, y1+h);

        int distX = Xc - closestX;
        int distY = Yc - closestY;

        int dist = distX * distX + distY * distY;
        return dist < R_sqrd;
    }
   
}    