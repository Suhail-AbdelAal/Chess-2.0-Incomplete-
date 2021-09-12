package Tiles;

import Display.GameWindow;
import Pieces.Pawn;
import Pieces.Piece;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;

public class Board extends JPanel implements MouseListener {

    private GameWindow g;
    private Square[][] board;

    public final LinkedList<Piece> bPieces, wPieces;
    private Piece currPiece;
    private int currX, currY;
    private boolean isLight = true;

    // Constructors
    public Board(GameWindow g) {
        this.g = g;
        this.bPieces = new LinkedList<>();
        this.wPieces = new LinkedList<>();
        board = new Square[8][8];
        setLayout(new GridLayout(8, 8, 0, 0));

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = new Square(this, isLight, i, j);
                this.add(board[i][j]);
                isLight = !isLight;
            }
            isLight = !isLight;
        }
        setPieces();
        this.setPreferredSize(new Dimension(512, 512));
        this.setMaximumSize(new Dimension(512, 512));
        this.setMinimumSize(this.getPreferredSize());
        this.setSize(new Dimension(512, 512));
    }

    @Override
    public void paintComponent(Graphics g) {
        // super.paintComponent(g);

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Square sq = board[i][j];
                sq.paintComponent(g);
            }
        }

        if (currPiece != null) {
            if (!(currPiece.isWhite()) || (currPiece.isWhite())) {
                final Image i = currPiece.getImg();
                g.drawImage(i, currX, currY, null);
            }
        }
    }
    // Methods
    public void setPieces() {
        // Pawns
        for (int i = 0; i < 8; i++) {
            board[1][i].put(new Pawn(false, board[1][i], "/ChessAssets/blackPawn.png"));
            board[6][i].put(new Pawn(true, board[1][i], "/ChessAssets/whitePawn.png"));
        }

        // Adding pieces to the linked list
        for (int i = 0; i < 8; i++) {
            bPieces.add(board[1][i].getOccPiece());
            wPieces.add(board[6][i].getOccPiece());
        }
    }


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        currX = e.getX();
        currY = e.getY();
        Square spot = (Square) this.getComponentAt(new Point(e.getX(), e.getY()));
        if (spot.isOccupied()) {
            currPiece = spot.getOccPiece();
            System.out.println("yellow");
            repaint();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Square spot = (Square) this.getComponentAt(new Point(e.getX(), e.getY()));
        currPiece = null;
        System.out.println("red");
        repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    // Setters & Getters
    public Square[][] getSquareArray() {
        return board;
    }
}
