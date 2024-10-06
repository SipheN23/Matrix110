public class Gameplay1 {
    private Paddle paddle;
    private List<Ball> balls; // Store all active balls
    private List<Bricks> bricks;
    private boolean multiBallActive = false; // Flag for multi-ball status

    public Gameplay1() {
        paddle = new Paddle(450, 700);
        balls = new ArrayList<>();
        bricks = new ArrayList<>();

        // Initial ball
        balls.add(new Ball(500, 600, 20));

        // Generate bricks and randomly assign power-ups to 2 of them
        generateBricksWithPowerUp();
    }

    // Method to generate bricks and assign two power-ups randomly
    private void generateBricksWithPowerUp() {
        Random random = new Random();
        int numBricks = 30; // Example total number of bricks
        int powerUpCount = 0;
        while (powerUpCount < 2) {
            int x = random.nextInt(800);
            int y = random.nextInt(400);

            boolean hasPowerUp = powerUpCount < 2 && random.nextBoolean();
            if (hasPowerUp) {
                powerUpCount++;
            }

            bricks.add(new Bricks(x, y, hasPowerUp));
        }
    }

    public void update() {
        // Handle ball movements
        for (int i = balls.size() - 1; i >= 0; i--) {
            Ball ball = balls.get(i);
            ball.move();
            ball.checkWallCollision(1000, 800);
            ball.checkPaddleCollision(paddle);

            // Check for brick collisions
            for (Bricks brick : bricks) {
                if (!brick.isDestroyed() && ball.getBounds().intersects(brick.getBounds())) {
                    brick.destroy();
                    ball.reverseY();

                    // Activate multi-ball if brick contains power-up
                    if (brick.hasPowerUp()) {
                        activateMultiBall();
                    }
                }
            }
        }

        // Check if any balls have fallen below the paddle
        removeFallenBalls();

        // End the game if all balls are lost
        if (balls.isEmpty()) {
            endGame();
        }
    }

    private void activateMultiBall() {
        if (!multiBallActive) {
            // Add new balls to make a total of 4 balls
            while (balls.size() < 4) {
                balls.add(new Ball(500, 600, 20));
            }

            // Launch all balls with random velocities
            for (Ball ball : balls) {
                ball.launch();
            }
            multiBallActive = true;
        }
    }

    private void removeFallenBalls() {
        for (int i = balls.size() - 1; i >= 0; i--) {
            Ball ball = balls.get(i);
            if (ball.getY() > 800) { // If ball falls below the screen
                balls.remove(i);
            }
        }
    }

    public void draw(Graphics g) {
        paddle.draw(g);

        // Draw all balls
        for (Ball ball : balls) {
            ball.draw(g);
        }

        // Draw bricks
        for (Bricks brick : bricks) {
            brick.draw(g);
        }
    }

    private void endGame() {
        System.out.println("Game Over! No more balls left.");
        // Add logic to end the game (e.g., stop timers, show game over screen)
    }
}
