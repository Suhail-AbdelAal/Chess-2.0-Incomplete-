package Tiles;

import Pieces.Piece;

import javax.swing.*;
import java.awt.*;

public class Square extends JComponent {

    private Board board;
    private Piece piece;
    private int color;
    private boolean isLight = true;

    private int xNum, yNum;

    // Constructors
    public Square(Board board, int color, int x, int y) {
        this.board = board;
        this.color = color;
        this.xNum = x;
        this.yNum = y;
        this.setBorder(BorderFactory.createEmptyBorder());
    }

    // Methods
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.color == 1)
            g.setColor(new Color(196, 196, 196));
        else
            g.setColor(new Color(201, 100, 100, 255));
        isLight = !isLight;
        g.fillRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
    }

    // Setters & Getters
    public int getxNum() {
        return xNum;
    }
    public void setxNum(int xNum) {
        this.xNum = xNum;
    }
    public int getyNum() {
        return yNum;
    }
    public void setyNum(int yNum) {
        this.yNum = yNum;
    }
}
