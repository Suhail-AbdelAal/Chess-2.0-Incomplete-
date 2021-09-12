package Tiles;

import Display.GameWindow;

import javax.swing.*;
import java.awt.*;

public class Board extends JPanel {

    private GameWindow g;
    private Square[][] board;

    // Constructors
    public Board(GameWindow g) {
        this.g = g;
        board = new Square[8][8];
        setLayout(new GridLayout(8, 8, 0, 0));

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                int xMod = i % 2;
                int yMod = j % 2;

                if ((xMod == 0 && yMod == 0) || (xMod == 1 && yMod == 1)) {
                    board[i][j] = new Square(this, 1, j, i);
                    this.add(board[i][j]);
                } else {
                    board[i][j] = new Square(this, 0, j, i);
                    this.add(board[i][j]);
                }
            }
        }
        this.setPreferredSize(new Dimension(512, 512));
        this.setMaximumSize(new Dimension(512, 512));
        this.setMinimumSize(this.getPreferredSize());
        this.setSize(new Dimension(512, 512));
    }

    // Methods
    @Override
    public void paintComponent(Graphics g) {
        // super.paintComponent(g);

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Square sq = board[j][i];
                sq.paintComponent(g);
            }
        }
    }
    // Setters & Getters
    public Square[][] getSquareArray() {
        return board;
    }

}
