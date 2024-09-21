import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener; 
import javax.swing.JPanel;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.Timer;

public class Gameplay extends JPanel implements KeyListener {
    private Paddle paddle;
    private LivesManager livesManager;
    private List<Bricks> bricks;
    private Random rand; 
    private List <PowerUp> powerUps;
    private Timer timer;

    // Constructor for the Gameplay class
    public Gameplay() {
        setBackground(Color.BLACK);
        setFocusable(true);
        livesManager = new LivesManager(3,5);
        bricks = new ArrayList<>();
        powerUps = new ArrayList<>();
        


        paddle = new Paddle(310, 500); // Initialize the paddle at a specific position
        initializeBricks();
        addKeyListener(this); 
        setFocusable(true); 
        setFocusTraversalKeysEnabled(false); 


        timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                updateGame();
                repaint();
            }
        });
        timer.start();
    }




    private void initializeBricks() {
        bricks.clear(); // Clear the list before adding new bricks
        
        int numRows = 5;
        int numCols = 10;
        int brickWidth = 50;
        int brickHeight = 30;
        int horizontalSpacing = 10; // Space between bricks horizontally
        int verticalSpacing = 10; // Space between bricks vertically
        
        // Calculate total width of the grid
        int totalBrickWidth = numCols * (brickWidth + horizontalSpacing);
        int windowWidth = 1000; // Width of the window/frame
        
        // Calculate starting X position to center the grid
        int startX = (windowWidth - totalBrickWidth) / 2;
        
        // Set the top Y position where the first row starts
        int startY = 100; // Adjust as needed to vertically position bricks
    
        // Arrange bricks in a grid
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                // Calculate position for each brick
                int x = startX + col * (brickWidth + horizontalSpacing);
                int y = startY + row * (brickHeight + verticalSpacing);
                Point pos = new Point(x, y);
                
                Color colour = Color.RED; // All bricks are red
                int strength = 1; // Uniform strength or adjust as needed
                bricks.add(new Bricks(pos, colour, strength));
            }
        }
    }


    //private void checkBrickCollison(){
        //for(Bricks brick : bricks){
            //if(!brick.isDestroyed()){
                //if(ballCollidesWithBrick(brick)){
                   // brick.hit();
                ///}
                //if(brick.isDestroyed()){
                  //  if(rand.nextInt(100) < 20){
                    //    Point powerUpPos = new Point(brick.getPosition().x, brick.getPosition().y);
                      //  String [] powerUpTypes = {"Larger Paddle", "Extra Life", "Faster Ball"};
                        //String randomPowerUp = powerUpTypes[rand.nextInt(powerUpTypes.length)];
                        //powerUps.add(new PowerUp(powerUpPos, randomPowerUp));
//
 //                   }
   //             }
     //       }
       // }
    //}


    private void checkPowerUpCollison(){
        for(PowerUp powerUp : powerUps){
            if(powerUp.isActive() && paddleCollidesWithPowerUp(powerUp)){
                applyPowerUP(powerUp.getType());
                powerUp.deactivate();
            }
        }
    }


    private boolean paddleCollidesWithPowerUp(PowerUp powerUp){
        Point powerUpPos = powerUp.getPosition();
        return powerUpPos.y + 20 >= paddle.getPaddleYCoord() &&
        powerUpPos.x >= paddle.getPaddleXCoord() &&
        powerUpPos.x <= paddle.getPaddleXCoord() + paddle.getPaddleWidth();
    }

    private void applyPowerUP(String type){
        switch (type) {
            case "Larger Paddle":
                paddle.setPaddleBounds(paddle.getPaddleWidth()+ 50);

                break;
            case "Extra Life":
                livesManager.gainLife();
                break;
            //case "Faster Ball":
                //ba 
        
        
        }
    }

    private void updateGame(){
        for(PowerUp powerUp: powerUps){
            if(powerUp.isActive()){
                powerUp.fall();
            }
        }

        checkPowerUpCollison();
    }


    

    // Method to draw the paddle 
    public void draw(Graphics g) {
        paddle.draw(g); // Draw the paddle on the panel
    }

    
    @Override
    public void paint(Graphics g) {
        super.paint(g); 
        draw(g);//Call the draw method to draw the paddle
        drawBricks(g);
        drawPowerUps(g);

    }


    private void drawPowerUps(Graphics g){
        for(PowerUp powerUp : powerUps){
            if(powerUp.isActive()){
                powerUp.draw(g);
            }
        }
    }

    private void drawBricks(Graphics g){
        for(Bricks brick : bricks){
            if(!brick.isDestroyed()){
                g.setColor(brick.getColour());
                g.fillRect(brick.getPosition().x, brick.getPosition().y, 50, 30);
                
            }
        }
    }

    
    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    
    @Override
    public void keyReleased(KeyEvent e) {
        
    }

    // Method to handle key press events
    @Override
    public void keyPressed(KeyEvent e) {
        // Check if the right arrow key is pressed
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (paddle.getPaddleXCoord() < 1000 - paddle.getPaddleWidth()) {
                paddle.moveRight(); // Move the paddle to the right
            } else {
                paddle.setPaddleBounds(1000 - paddle.getPaddleWidth()); // Keep paddle in bounds
            }
        } 
        // Check if the left arrow key is pressed
        else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (paddle.getPaddleXCoord() > 0) {
                paddle.moveLeft(); // Move the paddle to the left
            } else {
                paddle.setPaddleBounds(0); // Keep paddle in bounds
            }
        }
        repaint(); // Update the display
    }
}
