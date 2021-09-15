package Tiles;

import Display.GameWindow;
import Input.MouseInput;
import Pieces.*;
import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class Board extends JPanel {

    private GameWindow g;

    private Square[][] board;
    private Square spot_start;

    private Piece currPiece;
    private boolean whiteTurn;
    public final LinkedList<Piece> wPieces;
    public final LinkedList<Piece> bPieces;
    private boolean isLight;

    private MouseInput mouseInput;

    // Constructors
    public Board(GameWindow g) {
        this.g = g;
        this.bPieces = new LinkedList<>();
        this.wPieces = new LinkedList<>();

        board = new Square[8][8];
        isLight = true;
        whiteTurn = true;

        mouseInput = new MouseInput(this, currPiece, spot_start);
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
        addMouseListener(mouseInput);
        setPreferredSize(new Dimension(512, 512));
        setMaximumSize(new Dimension(512, 512));
        setMinimumSize(this.getPreferredSize());
        setSize(new Dimension(512, 512));
    }

    @Override
    public void paintComponent(Graphics g) {
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
        board[7][4].put(new King(1, board[7][4], "/ChessAssets/whiteKing.png"));
        board[0][4].put(new King(0, board[0][4], "/ChessAssets/blackKing.png"));

        // Queens
        board[7][3].put(new Queen(1, board[7][3], "/ChessAssets/whiteQueen.png"));
        board[0][3].put(new Queen(0, board[0][3], "/ChessAssets/blackQueen.png"));

        // Rooks
        board[7][0].put(new Rook(1, board[7][0], "/ChessAssets/whiteRook.png"));
        board[7][7].put(new Rook(1, board[7][7], "/ChessAssets/whiteRook.png"));
        board[0][0].put(new Rook(0, board[0][0], "/ChessAssets/blackRook.png"));
        board[0][7].put(new Rook(0, board[0][7], "/ChessAssets/blackRook.png"));

//        // Bishops
        board[7][2].put(new Bishop(1, board[7][2], "/ChessAssets/whiteBishop.png"));
        board[7][5].put(new Bishop(1, board[7][5], "/ChessAssets/whiteBishop.png"));
        board[0][2].put(new Bishop(0, board[0][2], "/ChessAssets/blackBishop.png"));
        board[0][5].put(new Bishop(0, board[0][5], "/ChessAssets/blackBishop.png"));


        // Knights
        board[7][1].put(new Knight(1, board[7][1], "/ChessAssets/whiteKnight.png"));
        board[7][6].put(new Knight(1, board[7][6], "/ChessAssets/whiteKnight.png"));
        board[0][1].put(new Knight(0, board[0][1], "/ChessAssets/blackKnight.png"));
        board[0][6].put(new Knight(0, board[0][6], "/ChessAssets/blackKnight.png"));

        // Pawns
        for (int i = 0; i < 8; i++) {
            board[6][i].put(new Pawn(1, board[6][i], "/ChessAssets/whitePawn.png"));
            board[1][i].put(new Pawn(0, board[1][i], "/ChessAssets/blackPawn.png"));
        }

        // Adding pieces to the linked list
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 8; j++) {
                bPieces.add(board[i][j].getPiece());
                wPieces.add(board[7 - i][j].getPiece());
            }
        }
    }

    // Setters & Getters
    public Square[][] getSquareArray() {
        return board;
    }

    public boolean isWhiteTurn() {
        return whiteTurn;
    }

    public void setWhiteTurn(boolean whiteTurn) {
        this.whiteTurn = whiteTurn;
    }
}
