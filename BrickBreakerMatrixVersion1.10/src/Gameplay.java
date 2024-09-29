import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

public class Gameplay extends JPanel implements KeyListener {
    private Paddle paddle; 
    private List<Bricks> bricks; 

    // Constructor for the Gameplay class
    public Gameplay() {
        paddle = new Paddle(450, 700); // Initialize the paddle at a specific position
        addKeyListener(this); 
        setFocusable(true); 
        setFocusTraversalKeysEnabled(false); 
        bricks = new ArrayList<>(); 
        initializeBricks(); 

        levelTimer = new GameTimer(60);  // Initialise the game timer
        levelTimer.start();

        gameUpdateTimer = new Timer(16, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                updateGame();
                repaint();
            }
        });
        gameUpdateTimer.start();
    }

    // Method to initialize the bricks
    private void initializeBricks() {
        bricks.clear(); // Clear the list before adding new bricks
        int numRows = 5; 
        int numCols = 10; 
        int brickWidth = 50; 
        int brickHeight = 30; 
        int horizontalSpacing = 10; // Space between bricks horizontally
        int verticalSpacing = 10; // Space between bricks vertically

        //Calculate total width of the grid
        int totalBrickWidth = numCols * (brickWidth + horizontalSpacing); // Total width of all bricks
        int windowWidth = 1000; // Width of the game window

        // Calculate starting X position to center the grid
        int startX = (windowWidth - totalBrickWidth) / 2; 

        // Set the top Y position where the first row starts
        int startY = 100; //Adjust as needed to vertically position bricks

        // Arrange bricks in a grid
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                //Calculate position for each brick
                int x = startX + col * (brickWidth + horizontalSpacing); // Calculate X position
                int y = startY + row * (brickHeight + verticalSpacing); // Calculate Y position
                bricks.add(new Bricks(new Point(x, y), Color.RED, 1)); // Add a new brick to the list
            }
        }
    }

    // method to check brick collison
    private void checkBrickCollison(){
    for (Bricks brick : bricks) {
        if (!brick.isDestroyed() && ballIntersectsBricks(brick)) {
            Rectangle ballBounds = new Rectangle(ball.getX(), ball.getY(), ball.getDiameter(), ball.getDiameter());
            Rectangle brickBounds = new Rectangle(brick.getPosition().x, brick.getPosition().y, 50, 30);

            if (ballBounds.intersects(brickBounds)) {
                // Check if the collision was on the sides of the brick
                if (ball.getX() + ball.getDiameter() <= brick.getPosition().x || ball.getX() >= brick.getPosition().x + 50) {
                    ball.reverseXVelocity(); // Reverse horizontal direction
                } else {
                    ball.reverseYVelocity(); // Reverse vertical direction
                }

                // Mark the brick as destroyed
                brick.hit();
                scoreManager.addScore(10);
                break; // Exit after one collision
            }
        }
    }

}
    // method to 
    private boolean ballIntersectsBricks(Bricks brick){
        //Get the current position of the ball
    Point ballPos = new Point(ball.getX(), ball.getY());
        //calculate the ball's radius
    int ballRadius = ball.getDiameter() / 2;
        // Get the position of the brick
    Point brickPos = brick.getPosition();

    //Check if the ball's boundaries overlap with the bricks's position 
    return ballPos.x + ballRadius >= brickPos.x &&
    ballPos.x - ballRadius <= brickPos.x + 50 &&
    ballPos.y + ballRadius >= brickPos.y &&
    ballPos.y - ballRadius <= brickPos.y + 30;
}

    // Method to draw the bricks 
    private void drawBricks(Graphics g) {
        for (Bricks brick : bricks) {
            if (!brick.isDestroyed()) { // Check if the brick is not destroyed
                g.setColor(brick.getColor()); 
                g.fillRect(brick.getPosition().x, brick.getPosition().y, 50, 30); // Draw the brick
            }
        }
    }


    private void checkPaddleCollision() {
        Rectangle ballBounds = new Rectangle(ball.getX(), ball.getY(), ball.getDiameter(), ball.getDiameter());
        Rectangle paddleBounds = new Rectangle(paddle.getPaddleXCoord(), paddle.getPaddleYCoord(), paddle.getPaddleWidth(), paddle.getPaddleHeight());
    
        if (ballBounds.intersects(paddleBounds)) {
            // Reverse the vertical velocity when the ball hits the paddle
            ball.reverseYVelocity();
    
            // Adjust the horizontal velocity based on where the ball hits the paddle
            int paddleCenter = paddle.getPaddleXCoord() + (paddle.getPaddleWidth() / 2);
            int ballCenter = ball.getX() + (ball.getDiameter() / 2);
    
            // Determine the new X velocity based on how far from the paddle center the ball hit
            int offset = ballCenter - paddleCenter;
            int maxVelocity = 7; // Maximum horizontal speed
            ball.setXVelocity((offset * maxVelocity) / (paddle.getPaddleWidth() / 2));
    
            // Ensure the ball stays above the paddle after the collision
            ball.setY(paddle.getPaddleYCoord() - ball.getDiameter());
        }
    }


    private void updateGame(){
        if(gameOver) return;


        if(ballLaunched){
            ball.move();
            ball.checkWallCollision(1000, 800);
            checkPaddleCollision();
            checkBrickCollison();
            //checkPowerUpCollision();
        }

        if(ball.getY() > paddle.getPaddleYCoord() + paddle.getPaddleHeight()){
            handleBallFell();
            }
        if(levelTimer.isTimeUp()){
            handleGameOver();
        }

        if(isPaddleEnlarged && (System.currentTimeMillis() - powerUpActivatedTime) > powerUpDuration){
            paddle.setPaddleBounds(paddle.getPaddleWidth() - 50);
            isPaddleEnlarged = false;
            }
        
            if(bricks.stream().allMatch(Bricks::isDestroyed)){
                advanceToNextLevel();
                
        }

        levelTimer.updateTimeRemaining();
    }

    private void handleBallFell(){
        if(gameOver) return;


        System.out.println("Ball fell! Resetting");
        livesManager.loseLife();


        if(!livesManager.isGameOver()){
            //resetBallAndPaddle();
           // resetGame();
            resetlevel();
            levelTimer.reset(60);
            System.out.println("Lives remaining: " + livesManager.getRemainingLives());
        }
        else{
            handleGameOver();
        }
    }

    private void resetGame(){
        ballLaunched = false;
        resetBallAndPaddle();
        //ball.resetPosition(paddle.getPaddleXCoord()+ (paddle.getPaddleWidth() /2 ) -(ball.getDiameter()/2), paddle.getPaddleYCoord() - ball.getDiameter());
        levelTimer.reset(60);
        initializeBricks(2,5);
        gameOver = false;
        gameUpdateTimer.start();
        repaint();
    }

    private void displayGameOver(Graphics g){
        g.setColor(Color.WHITE);
        g.drawString("Game over! ",450, 300);
        g.drawString("Press R to restart ", 410, 350);
    }


    private void handleGameOver(){
        if(gameOver) return;


        System.out.println("Time's up! Game over");
        gameOver = true;
        gameUpdateTimer.stop();
        levelTimer.stop();

         System.out.println("Press R to restart");
        
    }



    private void advanceToNextLevel(){
        if(currentLevel < maxLevels){
            currentLevel++;

            //Adjust the brick arrangement based on the level
            if(currentLevel == 1){
                initializeBricks(2,5);
            }else if(currentLevel ==2){
                initializeBricks(3,7);
            }
            else{
                initializeBricks(5, 10);
            }

            // Reset ball and paddle positions
            resetBallAndPaddle();

            // Reset level timer
            levelTimer.reset(60);

            // Set ballLaunched to false to allow launching in the new level
            ballLaunched = false;


            System.out.println("Level " + currentLevel + " starts now!");

        }else{
            // Handle game completion scenario
            System.out.println("Congratulations! You've completed the game!");
            gameOver = true;
            gameUpdateTimer.stop();
        }
    }

    public void resetBallAndPaddle(){

        paddle.setPaddleBounds(450);
        // Position the ball above the paddle
        ball.setX(paddle.getPaddleXCoord() + (paddle.getPaddleWidth() /2 ) - (ball.getDiameter() /2));
        ball.setY(paddle.getPaddleYCoord() - ball.getDiameter());
       
       // Reset ball velocities to zero
        ball.setXVelocity(0);
        ball.setYVelocity(0);


        //paddle.setPaddleBounds(paddle.getPaddleWidth());
        //ballLaunched = false;
    }

    private void resetlevel(){
        resetBallAndPaddle();
        initializeBricks(2, 5);
        levelTimer.reset(60);
        //gameUpdateTimer.start();
        gameOver = false;
        ballLaunched = false;
    }



    

    @Override
    public void paint(Graphics g) {
        super.paint(g); 
        paddle.draw(g); // Call the draw method to draw the paddle
        drawBricks(g); // Call the drawBricks method to draw the bricks
        if(gameOver){
            displayGameOver(g);
        }
        
    }

     // Method to draw the paddle 
    public void draw(Graphics g) {
        paddle.draw(g); // Draw the paddle on the panel
        ball.draw(g);
        drawBricks(g);
        drawPowerUps(g);

        // displays the following details on the top left of the screen
        g.setColor(Color.WHITE);
        g.drawString("Time remaining: "+ levelTimer.toString(), 10,20);
        g.drawString("Lives: "+ livesManager.getRemainingLives(),10,40);
        g.drawString("Level: " + currentLevel, 10,60);
        g.drawString("Score: " + scoreManager.getCurrentScore(), 10, 80);
        g.drawString("High Score: " + scoreManager.getHighScore(), 10, 100);

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

    
    @Override
    public void keyPressed(KeyEvent e) {

         // Check if the right arrow key is pressed
        if (e.getKeyCode() == KeyEvent.VK_UP && !ballLaunched) {
                {
                ball.setX(paddle.getPaddleXCoord() + (paddle.getPaddleWidth() / 2) - (ball.getDiameter() / 2));
                ball.setY(paddle.getPaddleYCoord() - ball.getDiameter());
                ball.launch();
                ballLaunched = true;
        }   
        // Check if the right arrow key is pressed
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) { 
            if (paddle.getPaddleXCoord() < 1000 - paddle.getPaddleWidth()) {
                paddle.moveRight(); // Move the paddle to the right
            } else {
                paddle.setPaddleBounds(1000 - paddle.getPaddleWidth()); // Keep paddle within bounds
            }
         // Check if the left arrow key is pressed
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (paddle.getPaddleXCoord() > 0) {
                paddle.moveLeft(); // Move the paddle to the left
            } else {
                paddle.setPaddleBounds(0); // Keep paddle within bounds
            }
            if(gameOver && e.getKeyCode() == KeyEvent.VK_R){
            resetGame();
            }
            
        }
        repaint(); // Update the display
    }
}
