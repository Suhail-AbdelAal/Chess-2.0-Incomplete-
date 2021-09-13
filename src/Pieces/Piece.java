package Pieces;

import Tiles.Board;
import Tiles.Square;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;

public abstract class Piece {

    private Square currSq;
    private BufferedImage img;
    private boolean isWhite = false;
    private boolean killed = false;

    // Constructors
    public Piece(boolean white, Square initSq, String img_path) {
        this.isWhite = white;
        this.currSq = initSq;

        try {
            if (this.img == null)
                this.img = ImageIO.read(getClass().getResource(img_path));

        } catch (IOException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }

    // Methods
    public void draw(Graphics g) {
        int x = currSq.getX() + 8;
        int y = currSq.getY() + 6;
        g.drawImage(this.img, x, y, 50, 55, null);
    }

    // Setters & Getters
    public boolean isWhite() {
        return this.isWhite;
    }

    public void setWhite(boolean white) {
        this.isWhite = white;
    }

    public boolean isKilled() {
        return this.killed;
    }

    public void setKilled(boolean killed) {
        this.killed = killed;
    }

    public Square getPosition() {
        return currSq;
    }

    public void setPosition(Square currSq) {
        this.currSq = currSq;
    }

    public BufferedImage getImg() {
        return img;
    }

    public void setImg(BufferedImage img) {
        this.img = img;
    }

    // abstract methods
    public abstract boolean canMove(Board board, Square start, Square end);
    public abstract LinkedList<Square> getLegalMoves(Board b);
}