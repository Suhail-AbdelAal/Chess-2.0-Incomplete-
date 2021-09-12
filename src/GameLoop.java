import States.Game;

import java.awt.*;

public class GameLoop extends Canvas implements Runnable{

    private boolean isRunning;
    private Game game;
    public GameLoop(Game game) {
        this.game = game;
    }

    @Override
    public void run() {
        isRunning = true;
        final int FPS = 60;
        final int UPS = 60;
        final double OPTIMAL_TIME = 1000000000d / UPS;
        double deltaTime = 0;
        long lastTime = System.nanoTime();
        int frames = 0, updates = 0;
        long timer = System.currentTimeMillis();
        while (isRunning) {
            long currentTime = System.nanoTime();
            deltaTime += (currentTime - lastTime);
            lastTime = currentTime;
            if (deltaTime >= OPTIMAL_TIME) {
                update();
                draw();
                frames++;
                updates++;
                deltaTime -= OPTIMAL_TIME;
            }
            if (System.currentTimeMillis() - timer >= 1000) {
                System.out.println("FPS: " + frames + " | " + "UPS: " + updates);
                updates = 0;
                frames = 0;
                timer += 1000;
            }
        }
    }
    public void update() {
        game.update();
    }
    public void draw() {
        game.draw();

    }
}
