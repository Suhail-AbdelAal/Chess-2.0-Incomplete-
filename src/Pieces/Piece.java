package Pieces;

import Tiles.Game;
import Tiles.Square;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;

public abstract class Piece {

    private Square currSq;
    private BufferedImage img;
    private JPanel panel = new JPanel();
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

    public Square getSquare() {
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
        Graphics2D g2 = (Graphics2D) g;

        int x = currSq.getX() + 3;
        int y = currSq.getY() + 6;

        g2.drawImage(this.img, x, y, 60, 55, null);
    }

    public int[] Linear_Occupied_Spots(Game game, int x, int y) {
        Square[][] sq = game.getSquareArray();

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

    public int[] Diagonal_Occupied_Spots(Game game, int x, int y) {
        Square[][] sq = game.getSquareArray();

        int top_x_left = x;
        int top_y_left = y;
        int top_x_right = x;
        int top_y_right = y;

        int bottom_x_left = x;
        int bottom_y_left = y;
        int bottom_x_right = x;
        int bottom_y_right = y;

        // TOP RIGHT
        for (int i = 1; i <= 7; i++) {
            try {
                if (sq[x - i][y + i].isOccupied()) {
                    if (sq[x - i][y + i].getPiece().getColor() == sq[x][y].getPiece().getColor()) {
                        top_x_right = x - i + 1;
                        top_y_right = y + i - 1;
                    }
                    else {
                        top_x_right = x - i;
                        top_y_right = y + i;
                    }
                    break;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                break;
            }
            top_x_right = x - i;
            top_y_right = y + i;
        }

        // TOP LEFT
        for (int i = 1; i <= 7; i++) {
            try {
                if (sq[x - i][y - i].isOccupied()) {
                    if (sq[x - i][y - i].getPiece().getColor() == sq[x][y].getPiece().getColor()) {
                        top_x_left = x - i + 1;
                        top_y_left = y - i + 1;
                    }
                    else {
                        top_x_left = x - i;
                        top_y_left = y - i;
                    }
                    break;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                break;
            }
            top_x_left = x - i;
            top_y_left = y - i;
        }

        // BOTTOM RIGHT
        for (int i = 1; i <= 7; i++) {
            try {
                if (sq[x + i][y + i].isOccupied()) {
                    if (sq[x + i][y + i].getPiece().getColor() == sq[x][y].getPiece().getColor()) {
                        bottom_x_right = x + i - 1;
                        bottom_y_right = y + i - 1;
                    }
                    else {
                        bottom_x_right = x + i;
                        bottom_y_right = y + i;
                    }
                    break;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                break;
            }
            bottom_x_right = x + i;
            bottom_y_right = y + i;
        }

        // BOTTOM LEFT
        for (int i = 1; i <= 7; i++) {
            try {
                if (sq[x + i][y - i].isOccupied()) {
                    if (sq[x + i][y - i].getPiece().getColor() == sq[x][y].getPiece().getColor()) {
                        bottom_x_left = x + i - 1;
                        bottom_y_left = y - i + 1;
                    }
                    else {
                        bottom_x_left = x + i;
                        bottom_y_left = y - i;
                    }
                    break;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                break;
            }
            bottom_x_left = x + i;
            bottom_y_left = y - i;
        }

        return new int[] {top_x_right, top_y_right, top_x_left, top_y_left,
                bottom_x_right, bottom_y_right, bottom_x_left, bottom_y_left};
    }

    // abstract methods
    public abstract boolean canMove(Game game, Square start, Square end);
    public abstract LinkedList<Square> getLegalMoves(Game game);
    public abstract LinkedList<Square> returnLegalMoves();
    public abstract void clearLegalMoves();
}