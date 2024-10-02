/**
 * The LivesManager class manages the player's lives in the game.
 * It handles the logic for losing and gaining lives, checking for
 * game-over conditions, resetting lives, and facilitating game-related
 * actions such as displaying the remaining lives on screen.
 */


 public class LivesManager {

    // Attributes
    private int lives; // Current number of lives (not used in logic but can be for display)
    private int maxLives; // Maximum number of lives allowed
    private int remainingLives; // Lives remaining for the 


/*
 * Constructor for LivesManager.
 * Initializes the LivesManager with a starting number of lives
 * and maximum number of lives.
 */

    public LivesManager(){
        this.lives = 3;  // this the default number of lives per game. 
        this.maxLives = 5; // default max number of lives


    } // this can be modified depending on the number of lives we want to use.

    
    
/*
 * Constructor for livesManager.
 * @param startingLives the number of lives the player starts with.
 * @param maxLives the maximum number of lives the player can have.
 * 
 */
     public LivesManager(int startingLives, int maxLives){
        this.remainingLives = startingLives; // Set remaining lives to starting lives
        this.maxLives = maxLives; // Set maximum lives
    }


/**
     * Decreases the number of lives by one.
     * Calls onLifeLost() and checks if the game is over.
     */
    public void loseLife(){  // Decrement lives to indicate loss of life.
        if(remainingLives > 0){
            remainingLives--; // Decrement remaining lives
            OnlifeLost(); // Notify that a life was lost
        }
        if(isGameOver()){
            handleGameOver(); // Handle game over logic if no lives remain
        }
    }


/*
 * Increases the number of lives by one.
 * Calls onLifeGain() when a life is added
 */

    public void gainLife(){ // increment lives to indicate gain of life.
        if(remainingLives < maxLives){
            remainingLives ++;
        
        OnLifeGain();
        }
        
    }
    /*
     * Checks if the player has no lives left.
     * 
     * @return true if the game is over, false otherwise.
     */

    public boolean isGameOver(){ // Method called when the number of lives reaches 0.
        return remainingLives == 0; // Return true if no remaining lives
    }

    /**
     * Gets the current number of remaining lives.
     * 
     * @return the number of remaining lives.
     */
    public int getRemainingLives(){  
        return remainingLives; // Return the remaining lives
    }

    /**
     * Sets the current number of lives.
     * 
     * @param lives the number of lives to set.
     */
    public void setLives(int lives){
        if(lives <= maxLives){
            this.lives = lives; // Set lives if within maxLives
        } else {
            this.lives = maxLives; // Cap lives at maxLives
        }
    }

    /**
     * Method called when a life is lost.
     * Displays the remaining lives in the console.
     */
    public void OnlifeLost(){
        System.out.println("Life lost. Remaining lives: " + remainingLives); // Notify life lost
    }

    /**
     * Method called when a life is gained.
     * Displays the current number of lives in the console.
     */
    public void OnLifeGain(){
        System.out.println("Life rewarded. Current number of lives: " + remainingLives); // Notify life gained
    }

    /**
     * Resets the lives to the initial startingLives value.
     * 
     * @param initialLives the desired number of lives to reset to.
     *                    If initialLives is greater than maxLives, 
     *                    the lives will be reset to maxLives.
     */
    public void resetLives(int initialLives){
        if(initialLives <= maxLives){
            this.remainingLives = initialLives; // Reset remaining lives if within maxLives
        } else {
            this.remainingLives = maxLives; // Cap remaining lives at maxLives
        }
    }

    /**
     * Handles the game-over condition.
     * Displays a game-over message in the console.
     */
    public void handleGameOver(){
        System.out.println("Game Over!! "); // Notify game over
    }
}
