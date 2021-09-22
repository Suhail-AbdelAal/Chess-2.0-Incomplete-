package Display;

import Tiles.Game;

import javax.swing.*;
import java.awt.*;

public class GameWindow {

    private int width, height;
    private JFrame frame;
    private Game game;

    // Constructors
    public GameWindow() {
        frame = new JFrame("Chess");
        ///
        game = new Game(this);
        frame.add(game);


        ///
        frame.setSize(new Dimension(width, height));
        frame.setResizable(false);
        frame.setAlwaysOnTop(true);
//        frame.setLocationRelativeTo(null);
//        frame.setUndecorated(true);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
