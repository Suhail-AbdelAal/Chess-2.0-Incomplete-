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

    // Methods
    public void draw(Graphics g) {
        int x = currSq.getX() + 3;
        int y = currSq.getY() + 6;
        g.drawImage(this.img, x, y, 60, 55, null);
    }

    public int[] Linear_Occupied_Spots(Board board, int x, int y) {
        Square[][] sq = board.getSquareArray();

        int top = 0, bottom = 7;
        int right = 7, left = 0;

        // TOP
        for (int  i = x - 1; i >= 0; i--) {
            if (sq[i][y].isOccupied()) {
                if (sq[i][y].getPiece().getColor() == currSq.getPiece().getColor()) {
                    top = sq[i + 1][y].getxNum();
                }
                else
                    top = i;

                break;
            }

        }

        // BOTTOM
        for (int  i = x + 1; i <= 7; i++) {
            if (sq[i][y].isOccupied()) {
                if (sq[i][y].getPiece().getColor() == currSq.getPiece().getColor()) {
                    bottom = sq[i - 1][y].getxNum();
                }
                else
                    bottom = i;

                break;
            }
        }

        // RIGHT
        for (int i = y + 1; i <= 7; i++) {
            if (sq[x][i].isOccupied()) {
                if (sq[x][i].getPiece().getColor() == currSq.getPiece().getColor()) {
                    right = sq[x][i - 1].getyNum();
                }
                else
                    right = i;

                break;
            }
        }

        // LEFT
        for (int i = y - 1; i >= 0; i--) {
            if (sq[x][i].isOccupied()) {
                if (sq[x][i].getPiece().getColor() == currSq.getPiece().getColor()) {
                    left = sq[x][i + 1].getyNum();
                }
                else
                    left = i;

                break;
            }
        }
        return new int[] {top, bottom, right, left};
    }

    public int[] Diagonal_Occupied_Spots(Board board, int x, int y) {
        return  null;
    }

    // abstract methods
    public abstract boolean canMove(Board board, Square start, Square end);
    public abstract LinkedList<Square> getLegalMoves(Board board);
}