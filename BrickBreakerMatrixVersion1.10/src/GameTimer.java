import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class GameTimer{

    private int totalTime;
    private int timeRemaning;
    private boolean isRunning;
    private long lastUpdateTime;
    private Timer timer;


    // Constructor to initialize the timer 
    public GameTimer(int startTime){
        this.timeRemaning = startTime;
        timer = new Timer(1000, new ActionListener() {
            
        @Override
        public void actionPerformed(ActionEvent e){
            if(timeRemaning > 0){
                timeRemaning--;
            }
        }
    });
    timer.start();
}

    //method to start or resume the timer
    public void start(){
        if(!isRunning){
            isRunning = true;
            lastUpdateTime = System.currentTimeMillis();
        }
    }

    // method to pause the game
    public void pause(){
        if(isRunning){
            updateTimeRemaining();
            isRunning = false;
        }
    }

    // method to reset the timer
    public void reset(int newTime){
        this.totalTime = newTime;
        this.timeRemaning = newTime;
        this.isRunning = false;
    }


    // method to update the time remaining based on the time that has passed
    public void updateTimeRemaining(){
        if(isRunning){
            long currentTime = System.currentTimeMillis();
            int elapsedTime = (int) ((currentTime - lastUpdateTime) /1000); // convert milliseconds to seconds
            timeRemaning -= elapsedTime; // reduce time remaining
            lastUpdateTime = currentTime; // Update the last update time

            if(timeRemaning <= 0){
                timeRemaning = 0; // ensure time does not go below zero;
                stop();
            }
        }
    }


    // method to stop the timer when the time runs out
    public void stop(){
        isRunning = false;
        timeRemaning = 0;
    }


    // method to check if time has run out
    public boolean isTimeUp(){
        return timeRemaning <= 0;
    }


    // method to get the remaining time in seconds
    public int getRemaningTime(){
        updateTimeRemaining();
        return timeRemaning;
    }


    // method to display thr time in minutes and seconds format
    public String toString(){
        updateTimeRemaining();
        int minutes = timeRemaning / 60;
        int seconds = timeRemaning % 60;

        return String.format("%02d:%02d", minutes, seconds);

    }


    // method to check if the timer is running
    public boolean isRunning(){
        return isRunning;
    }






    
}
