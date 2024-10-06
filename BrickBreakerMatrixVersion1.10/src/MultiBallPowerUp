import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class MultiBallPowerup {
    private int x, y;
    private int width = 20, height = 20;
    private Color color = Color.YELLOW;
    private boolean isActive = true;

    public MultiBallPowerup(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics g) {
        if (isActive) {
            g.setColor(color);
            g.fillRect(x, y, width, height); // Draw the power-up
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public boolean isActive() {
        return isActive;
    }

    public void deactivate() {
        isActive = false; // Deactivate the power-up after being collected
    }
}
