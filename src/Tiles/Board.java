package Tiles;

import Display.GameWindow;
import Pieces.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;

public class Board extends JPanel implements MouseListener {
    int c = 0;
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
        setLayout(new GridLayout(8, 8));

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

//        if (currPiece != null) {
//            if (!(currPiece.isWhite()) || (currPiece.isWhite())) {
//                final Image i = currPiece.getImg();
//                g.drawImage(i, currX, currY, null);
//            }
//        }
    }

    // Methods
    public void setPieces() {
        // Kings
        board[7][4].put(new King(true, board[7][4], "/ChessAssets/whiteKing.png"));
        board[0][4].put(new King(false, board[0][4], "/ChessAssets/blackKing.png"));

        // Queens
        board[7][3].put(new Queen(true, board[7][3], "/ChessAssets/whiteQueen.png"));
        board[0][3].put(new Queen(false, board[0][3], "/ChessAssets/blackQueen.png"));

        // Rooks
        board[7][0].put(new Rook(true, board[7][0], "/ChessAssets/whiteRook.png"));
        board[7][7].put(new Rook(true, board[7][7], "/ChessAssets/whiteRook.png"));
        board[0][0].put(new Rook(false, board[0][0], "/ChessAssets/blackRook.png"));
        board[0][7].put(new Rook(false, board[0][7], "/ChessAssets/blackRook.png"));

        // Bishops
        board[7][2].put(new Bishop(true, board[7][2], "/ChessAssets/whiteBishop.png"));
        board[7][5].put(new Bishop(true, board[7][5], "/ChessAssets/whiteBishop.png"));
        board[0][2].put(new Bishop(false, board[0][2], "/ChessAssets/blackBishop.png"));
        board[0][5].put(new Bishop(false, board[0][5], "/ChessAssets/blackBishop.png"));


        // Knights
        board[7][1].put(new Knight(true, board[7][1], "/ChessAssets/whiteKnight.png"));
        board[7][6].put(new Knight(true, board[7][6], "/ChessAssets/whiteKnight.png"));
        board[0][1].put(new Knight(false, board[0][1], "/ChessAssets/blackKnight.png"));
        board[0][6].put(new Knight(false, board[0][6], "/ChessAssets/blackKnight.png"));

        // Pawns
        for (int i = 0; i < 8; i++) {
            board[6][i].put(new Pawn(true, board[6][i], "/ChessAssets/whitePawn.png"));
            board[1][i].put(new Pawn(false, board[1][i], "/ChessAssets/blackPawn.png"));
        }

        // Adding pieces to the linked list
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 8; j++) {
                bPieces.add(board[i][j].getOccPiece());
                wPieces.add(board[7 - j][j].getOccPiece());
            }
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
