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
    private int color;
    private boolean firstMoveDone;

    // Constructors
    public Piece(int color, Square initSq, String img_path) {
        this.color = color;
        this.currSq = initSq;
        firstMoveDone = false;
        try {
            this.img = ImageIO.read(getClass().getResource(img_path));

        } catch (IOException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }

    // Methods
    public void draw(Graphics g) {
        int x = currSq.getX() + 3;
        int y = currSq.getY() + 6;
        g.drawImage(this.img, x, y, 60, 55, null);
    }

    // Setters & Getters
    public int getColor() {
        return this.color;
    }

    public void setColor(int color) {
        this.color = color;
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

    public void setFirstMoveDone(boolean firstMoveDone) {
        this.firstMoveDone = firstMoveDone;
    }

    public boolean isFirstMoveDone() {
        return firstMoveDone;
    }

    // abstract methods
    public abstract boolean canMove(Board board, Square start, Square end);
    public abstract LinkedList<Square> getLegalMoves(Board board);
}