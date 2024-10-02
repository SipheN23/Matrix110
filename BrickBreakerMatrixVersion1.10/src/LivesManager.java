public class LivesManager {
    private int lives;

    public LivesManager(){
        this.lives=3;
    }

    public void loseLife(){
        this.lives-=1;
    }

    public void gainLife(){
        this.lives+=1;
    }

    public boolean isGameOver(){
        return this.lives==0;
    }
}
