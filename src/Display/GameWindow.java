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
        this.board = new Board(this);
        frame.add(board);


        ///
        frame.setSize(new Dimension(width, height));
        frame.setResizable(false);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
