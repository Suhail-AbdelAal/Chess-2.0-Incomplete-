package Tiles;

import Pieces.Piece;

import javax.swing.*;
import java.awt.*;

public class Square extends JComponent {

    private Board board;
    private Piece occPiece;
    private boolean isLight;

    private int xNum, yNum;

    // Constructors
    public Square(Board board, boolean isLight, int x, int y) {
        this.board = board;
        this.isLight = isLight;
        this.xNum = x;
        this.yNum = y;
        this.setBorder(BorderFactory.createEmptyBorder());
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (this.isLight)
            g.setColor(new Color(206, 206, 206));
        else
            g.setColor(new Color(178, 79, 79));

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
        this.occPiece = null;
    }
    public void capture(Piece p) {
        if (getPiece().getColor() == 1) {
            board.wPieces.remove(getPiece());
            this.occPiece = null;
        }
        else {
            board.bPieces.remove(getPiece());
            this.occPiece = null;
        }
        this.put(p);
//        repaint();
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

    public Piece getPiece() {
        return occPiece;
    }

    public boolean isOccupied() {
        if (this.occPiece != null)
            return true;
        else
            return false;
    }

}
