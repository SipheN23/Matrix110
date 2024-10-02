import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.imageio.ImageIO;
import javax.io.File;
import java.io.IOException;
import java.util.Random;

**
 * The Gameplay class manages the main game logic for a brick breaker game.
 * It handles user input, rendering of game objects, and manages game state, including levels and scoring.
 */
public class Gameplay extends GameBackground implements KeyListener {
    private Paddle paddle; // The paddle controlled by the player
    private List<Bricks> bricks; // List of bricks to be destroyed
    private Ball ball; // The ball that bounces around the screen
    private Timer timer; // Timer to control the game loop
    private SoundPlayer soundPlayer; // Sound player for game sound effects
    private boolean ballLaunched; // Flag to check if the ball has been launched
    private boolean gameOver; // Flag to indicate if the game is over
    private boolean isPaused; // Flag to check if the game is paused
    private int score; // Player's score
    private Image pauseImage; // Image to display when the game is paused
    private String[] menuOptions = {"Resume", "Restart", "Quit"}; // Pause menu options
    private int selectedOption = 0; // The currently selected option in the pause menu
    private level[] levels = { // Different levels of the game with varying properties
        new level(5, 10, Color.RED),
        new level(6, 12, Color.BLUE),
        new level(7, 14, Color.GREEN),
    };
    private int currentLevelIndex = 0; // The index of the current level

    /**
     * Constructs a Gameplay object, initializes game components, and starts the game.
     */
    public Gameplay() {
        paddle = new Paddle(450, 700);
        ball = new Ball(500, 650, 20);
        ball.launch(); // Ensure the ball is launched
        ballLaunched = false;
        balls = new ArrayList<>();
        balls.add(ball);
        livesManager = new LivesManager(3, 5);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        bricks = new ArrayList<>();
        initializeBricks();
        startGame(); // Start the game loop

        try {
            pauseImage = ImageIO.read(new File("/Matrix110/BrickBreakerMatrixVersion1.10/src/_b35486c8-42a6-4f64-b4b0-30c60f537c6c.jpeg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Starts the game timer that triggers the game loop.
     */
    public void startGame() {
        timer = new Timer(20, e -> gameLoop()); // Calls gameloop to update game every 20 ms
        timer.start(); // Start the timer
    }

    /**
     * Initializes the bricks for the current level.
     */
    private void initializeBricks() {
        bricks.clear(); // Clear the list before adding new bricks
        level currentLevel = levels[currentLevelIndex]; // Get the current level's layout and properties
        int numRows = currentLevel.getNumRows(); // Number of rows of bricks in the current level
        int numCols = currentLevel.getNumCols(); // Number of columns of bricks in the current level
        Color brickColor = currentLevel.getBrickColor(); // Color assigned to all the bricks in this level
        int brickWidth = 50; // Width of each brick
        int brickHeight = 30; // Height of each brick
        int horizontalSpacing = 10; // Space between bricks horizontally
        int verticalSpacing = 10; // Space between bricks vertically
        int totalBrickWidth = numCols * (brickWidth + horizontalSpacing); // Total width of all bricks
        int windowWidth = 1000; // Width of the game window
        int startX = (windowWidth - totalBrickWidth) / 2; // Calculate starting X position to center the grid
        int startY = 100; // Adjust as needed to vertically position bricks

        // Arrange bricks in a grid
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                // Calculate position for each brick
                int x = startX + col * (brickWidth + horizontalSpacing); // Calculate X position
                int y = startY + row * (brickHeight + verticalSpacing); // Calculate Y position
                bricks.add(new Bricks(new Point(x, y), brickColor, 1)); // Add a new brick to the list
            }
        }
    }

    /**
     * Draws the bricks on the screen.
     * @param g Graphics object used for drawing.
     */
    private void drawBricks(Graphics g) {
        for (Bricks brick : bricks) {
            if (!brick.isDestroyed()) {
                g.setColor(brick.getColor());
                g.fillRect(brick.getPosition().x, brick.getPosition().y, 50, 30);
            }
        }
    }

    /**
     * Checks for collisions between the ball and the bricks.
     */
    private void checkBrickCollision() {
        // Check if the ball has collided with any bricks in the game
        if (ball.checkBrickCollision(bricks)) {
            // If there was a collision, play the sound effect for hitting a brick
            soundPlayer.playSoundEffect("/Matrix110/BrickBreakerMatrixVersion.1.10/src/HitBallEffect.wav");
        }
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        paddle.draw(g);
        drawBricks(g);
        ball.draw(g);
        drawScore(g);
        drawLives(g);
        if(gameOver){
            drawGameOver(g);
        }else if(isPaused){
            drawPauseScreen(g);
        };
            
    }
    /**
     * Draws the player's score on the screen.
     * @param g Graphics object used for drawing.
     */

    private void drawScore(Graphics g){
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD,20));
        g.drawString("Score: "+ score, 850,30); // draw  the score at the top right corner
    }
    /**
     * Draws the remaining lives on the screen.
     * @param g Graphics object used for drawing.
     */
    private void drawLives(Graphics g){
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Lives: " + livesManager.getRemainingLives(), 50, 30); // draw the remaining lives at the top-left corner
    }
    /**
     * Displays the pause screen with options when the game is paused.
     * @param g Graphics object used for drawing.
     */
    private void drawPauseScreen(Graphics g) {
        // Check if the pause image is loaded
        if (pauseImage != null) {
            // Set a semi-transparent black background over the entire screen
            g.setColor(new Color(0, 0, 0, 150));
            g.fillRect(0, 0, getWidth(), getHeight());

            // Get the width and height of pause image
            int imgWidth = pauseImage.getWidth(null);
            int imgHeight = pauseImage.getHeight(null);

            // Calculate the position to center the image on the screen
            int x = (getWidth() - imgWidth) / 2;
            int y = (getHeight() - imgHeight) / 2;

            // Draw the pause image at the calculated position
            g.drawImage(pauseImage, x, y, this);

            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 40));
            String pausedText = "Game paused";
            int pausedTextWidth = g.getFontMetrics().stringWidth(pausedText);
            g.drawString(pausedText, (getWidth() - pausedTextWidth) / 2, getHeight() / 4);

            // Draw menu options
            g.setFont(new Font("Arial", Font.BOLD, 30));
            for (int i = 0; i < menuOptions.length; i++) {
                if (i == selectedOption) {
                    g.setColor(Color.YELLOW);
                } else {
                    g.setColor(Color.WHITE);
                }
                g.drawString(menuOptions[i], getWidth() / 2 - 50, getHeight() / 2 + i * 40);
            }
        } else {
            // Fallback in case the image is not loaded
            g.setColor(Color.YELLOW);
            g.setFont(new Font("Arial", Font.BOLD, 40));
            g.drawString("Paused", 400, 400);
        }
    }

    /**
     * Draws the game over screen when the game is finished.
     * @param g Graphics object used for drawing.
     */

    private void drawGameOver(Graphics g){
        g.setColor(Color.RED);
        g.setFont(new Font("Arial", Font.BOLD,50));
        g.drawString("Game Over", 350 ,400);
    }
    /**
     * Main game loop that updates the game state and redraws the game.
     */
    private void gameLoop() {
        if(ballLaunched){
            ball.move(); // Move the ball
            ball.checkWallCollision(1000, 800); // Check for wall collisions
            checkBrickCollision(); // Check for brick collisions
            ball.checkPaddleCollision(paddle); // Check for paddle collisions
            
            repaint(); // Update the display
            checkLevelCompletion() // check for level completion
        if(ball.getY() > paddle.getPaddleYCoord() + paddle.getPaddleHeight()){
                    handleBallfell();
                }

        // Iterate through each brick in the bricks list
        bricks.forEach(brick -> {
            // Check if the brick has been destroyed and if its score hasn't been counted yet
            if (brick.isDestroyed() && !brick.isCountedScore()) {
                // Increment the score by 100 for each destroyed brick
                score += 100;
                // Mark the brick as counted to prevent adding a score for the same brick multiple times
                brick.setCountedForScore(true);
    }
        });

        }          
    }
}

  /**
 * Restarts the game by resetting all relevant game state variables 
 * to their initial values. This includes resetting the score, 
 * creating new instances of the Ball and Paddle objects, 
 * resetting the number of lives, initializing the bricks, 
 * and starting the game timer. 
 * The game is unpaused and marked as not over.
 */
private void restartGame() {
    //Clear the balls in the balls list
    balls.clear();
    // Reset the score to 0
    score = 0;

    // Create a new Ball object at the initial position (480, 680) with a diameter of 20
    ball = new Ball(480, 680, 20);
    balls.add(ball) // add new ball to list

    // Create a new Paddle object at the initial position (450, 700)
    paddle = new Paddle(450, 700);

    // Reset the lives to 3
    livesManager.resetLives(3);

    // Initialize the bricks for the new game
    initializeBricks();

    // Mark that the ball has not been launched yet
    ballLaunced = false;

    // Unpause the game
    isPaused = false;

    // Set the game over flag to false
    gameOver = false;

    // Start the game timer
    timer.start();

    // Redraw the game
    repaint();
}

/**
 * Handles the scenario when the ball falls below the paddle.
 * This method checks if the game is over and manages the 
 * player's remaining lives. If lives are left, it resets 
 * the level; otherwise, it marks the game as over.
 */
private void handleBallfell() {
    // If the game is over, do nothing
    if (gameOver) return;

    // Log to the console that the ball has fallen
    System.out.println("Ball fell! Resetting");
    // Reduce the number of remaining lives
    livesManager.loseLife();

    // If there are still lives left, reset the level
    if (!livesManager.isGameOver()) {
        resetlevel();
    } else {
        // If no lives are left, mark the game as over and repaint the screen
        gameOver = true;
        repaint();
    }
    // Mark the ball as not launched
    ballLaunced = false;
}

/**
 * Resets the current level by placing the ball back 
 * to the center of the paddle and just above it.
 */
private void resetlevel() {
    // Reset the ball's position to the center of the paddle and place it just above the paddle
    ball.resetPosition(
        paddle.getPaddleXCoord() + (paddle.getPaddleWidth() / 2) - (ball.getDiameter() / 2),
        paddle.getPaddleYCoord() - ball.getDiameter()
    );
    // Mark the ball as not launched
    ballLaunced = false;
}

/**
 * Checks if the current level is completed by verifying 
 * whether all bricks have been destroyed. If all bricks 
 * are destroyed, it moves to the next level or handles 
 * game completion if no more levels are left.
 */
private void checkLevelCompletion() {
    // Check if all bricks have been destroyed
    boolean allBricksDestroyed = bricks.stream().allMatch(Bricks::isDestroyed);
    
    // If all bricks are destroyed, move to the next level
    if (allBricksDestroyed) {
        currentLevelIndex++;

        // If there are more levels, initialize the new level and reset the ball
        if (currentLevelIndex < levels.length) {
            initializeBricks();
            resetlevel();
        } else {
            // If there are no more levels, handle game completion
            handleGameCompletion();
        }
    }
}

/**
 * Handles game completion by logging a congratulatory 
 * message to the console when all levels are completed.
 */
private void handleGameCompletion() {
    // Log a congratulatory message to the console when all levels are completed
    System.out.println("Congratulations! You completed all levels!");
}

/**
 * Toggles the pause state of the game. If the game is 
 * paused, it resumes it; otherwise, it pauses the game.
 */
private void togglePause() {
    // If the game is currently paused, resume it by starting the timer and unpausing the game
    if (isPaused) {
        timer.start();
        isPaused = false;
    } else {
        // If the game is not paused, stop the timer and pause the game
        timer.stop();
        isPaused = true;
    }
    // Redraw the game screen
    repaint();
}

/**
 * Handles the user's menu selection based on the selected 
 * option. It performs actions like resuming the game, 
 * restarting the game, or quitting the application.
 */
private void handleMenuSelection() {
    // Handle the user's menu selection based on the selected option
    switch (selectedOption) {
        case 0: // Resume the game
            togglePause();
            break;
        case 1: // Restart the game
            restartGame();
            break;
        case 2: // Quit the game
            System.exit(0);
            break;
    }
}

/**
 * Responds to key press events for controlling the game.
 * Launches the ball, moves the paddle, and handles menu 
 * navigation based on the current game state.
 * 
 * @param e The key event triggered by a key press.
 */
@Override
public void keyPressed(KeyEvent e) {
    // If the UP arrow key is pressed and the ball hasn't been launched, launch the ball
    if (e.getKeyCode() == KeyEvent.VK_UP && !ballLaunced) {
        // Set the ball's position to the center of the paddle
        ball.setX(paddle.getPaddleXCoord() + (paddle.getPaddleWidth() / 2) - (ball.getDiameter() / 2));
        ball.setY(paddle.getPaddleYCoord() - ball.getDiameter());
        // Launch the ball
        ball.launch();
        // Mark the ball as launched
        ballLaunced = true;
    }

    // Move the paddle right when the RIGHT arrow key is pressed
    if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
        if (paddle.getPaddleXCoord() < 1000 - paddle.getPaddleWidth()) {
            paddle.moveRight();
        } else {
            paddle.setPaddleBounds(1000 - paddle.getPaddleWidth());
        }
    }
    // Move the paddle left when the LEFT arrow key is pressed
    else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
        if (paddle.getPaddleXCoord() > 0) {
            paddle.moveLeft();
        } else {
            paddle.setPaddleBounds(0);
        }
    }

    // Handle menu navigation if the game is paused
    if (isPaused) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            // Move the selection up in the menu
            selectedOption = (selectedOption - 1 + menuOptions.length) % menuOptions.length;
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            // Move the selection down in the menu
            selectedOption = (selectedOption + 1) % menuOptions.length;
        } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            // Execute the selected menu option
            handleMenuSelection();
        }
        // Redraw the game screen
        repaint();
    } else {
        // Pause or unpause the game if the 'P' key is pressed
        if (e.getKeyCode() == KeyEvent.VK_P) {
            togglePause();
        }
    }
}


    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}
}
