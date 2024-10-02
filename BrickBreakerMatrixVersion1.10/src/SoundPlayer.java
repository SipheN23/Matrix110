import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

/**
 * The SoundPlayer class manages audio playback for the game.
 * It handles background music and sound effects using Java's Sound API.
 */
class SoundPlayer {
    private Clip backgroundMusic; // Clip for background music
    private boolean isPlaying;     // Flag to track if music is playing

    /**
     * Plays background music from the specified file path in a continuous loop.
     * 
     * @param filePath The path to the audio file to be played.
     */
    public void playSound(String filePath) {
        try {
            // Load the audio file into an AudioInputStream
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filePath));
            backgroundMusic = AudioSystem.getClip(); // Create a new Clip
            backgroundMusic.open(audioInputStream);   // Open the audio input stream
            backgroundMusic.loop(Clip.LOOP_CONTINUOUSLY); // Loop the music continuously
            backgroundMusic.start(); // Start playing the music
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            // Print the stack trace if an exception occurs
            e.printStackTrace();
        }
    }

    /**
     * Plays a sound effect from the specified file path.
     * This sound effect is played once and does not loop.
     * 
     * @param filePath The path to the sound effect audio file to be played.
     */
    public void playSoundEffect(String filePath) {
        try {
            // Load the sound effect audio file
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filePath));
            Clip soundEffect = AudioSystem.getClip(); // Create a new Clip for sound effects
            soundEffect.open(audioInputStream); // Open the audio input stream for the sound effect
            soundEffect.start(); // Start playing the sound effect
            
            // Add a line listener to close the sound effect when it stops playing
            soundEffect.addLineListener(event -> {
                if (event.getType() == LineEvent.Type.STOP) {
                    soundEffect.close(); // Close the Clip when done playing
                }
            });
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            // Print the stack trace if an exception occurs
            e.printStackTrace();
        }
    }

    /**
     * Stops the currently playing background music, if it is active.
     */
    public void stopSound() {
        if (isPlaying && backgroundMusic != null) {
            backgroundMusic.stop(); // Stop the background music
            backgroundMusic.close(); // Close the Clip
            isPlaying = false; // Update the playing flag
        }
    }
}
