package Tiles;

import Pieces.Piece;

import javax.swing.*;
import java.awt.*;

public class Square extends JComponent {

    private final Game game;
    private Piece occPiece;

    private int xNum, yNum;

    // Constructors
    public Square(Game game, int x, int y) {
        this.game = game;
        this.xNum = x;
        this.yNum = y;
        this.setBorder(BorderFactory.createEmptyBorder());
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        if (occPiece != null) {
//            g2.setStroke(new BasicStroke(3));
//            g2.setPaint(Color.green);
//            g2.drawRect(occPiece.getPosition().getX(), occPiece.getPosition().getY(), 64, 64);
            occPiece.draw(g2);
        }
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
        if (getPiece().getColor() == 1)
            game.wPieces.remove(getPiece());

        else
            game.bPieces.remove(getPiece());

        this.occPiece = null;
        this.put(p);
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
        return this.occPiece != null;
    }

}
