package Tiles;

import Pieces.Piece;

import javax.swing.*;
import java.awt.*;

public class Square extends JComponent {

    private Board board;
    private Piece occPiece;
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

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (this.color == 1)
            g.setColor(new Color(196, 196, 196));
        else
            g.setColor(new Color(194, 82, 82));
        g.fillRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());

        if (occPiece != null)
            occPiece.draw(g);
    }

    // Methods
    public void put(Piece p) {
        this.occPiece = p;
        p.setPosition(this);
    }
    public void removePiece() {
        occPiece = null;
    }
    public void capture(Piece p) {
        if (getOccPiece().isWhite())
            board.wPieces.remove(getOccPiece());
        else
            board.bPieces.remove(getOccPiece());
        this.occPiece = p;
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

    public Piece getOccPiece() {
        return occPiece;
    }

    public boolean isOccupied() {
        if (this.occPiece == null)
            return true;
        else
            return false;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
