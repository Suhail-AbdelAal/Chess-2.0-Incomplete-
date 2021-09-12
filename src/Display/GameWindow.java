package Display;

import Tiles.Board;

import javax.swing.*;
import java.awt.*;

public class GameWindow {

    private int width, height;
    private JFrame frame;
    private Board board;

    // Constructors
    public GameWindow() {
        frame = new JFrame("Chess");
        ///
        board = new Board(this);
        frame.add(board);


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
