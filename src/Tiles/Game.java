package Tiles;

import Display.GameWindow;
import Input.MouseInput;
import Pieces.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;

public class Game extends JPanel {

    private GameWindow gameWindow;
    private final Square[][] board;
    private Square spot_start;
    private Square start, end;
    private BufferedImage chessBoard;

    private boolean whiteTurn;
    private Piece currPiece;
    public static King whiteKing, blackKing;
    public final LinkedList<Piece> wPieces;
    public final LinkedList<Piece> bPieces;

    // Constructors
    public Game(GameWindow gameWindow) {
        try {
            chessBoard = ImageIO.read(Game.class.getResource("/ChessAssets/chessBoard.png"));
        } catch (IOException e) {
            System.out.println("File not found: " + e.getMessage());
        }
        this.gameWindow = gameWindow;
        this.bPieces = new LinkedList<>();
        this.wPieces = new LinkedList<>();
        start = null;
        end = null;

        board = new Square[8][8];
        whiteTurn = true;

        MouseInput mouseInput = new MouseInput(this, spot_start);

        setLayout(new GridLayout(8, 8));

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = new Square(this, i, j);
                this.add(board[i][j]);
            }
        }
        setPieces();
        addMouseListener(mouseInput);
        setPreferredSize(new Dimension(513, 512));
        setMaximumSize(new Dimension(513, 512));
        setMinimumSize(this.getPreferredSize());
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(chessBoard, 0, 0, 512, 512, null);

        // Check
        if (whiteKing.isKingChecked()) {
            System.out.println(whiteKing.getBlockMoves(this).size());
            g2.setStroke(new BasicStroke(5));
            g2.setPaint(Color.blue);
            for (Square i : whiteKing.getBlockMoves(this))
                g2.drawRect(i.getX() + 2, i.getY() + 1, 62, 63);

            g2.setPaint(new Color(255, 0, 0, 180));
            g2.fillRect(whiteKing.getSquare().getX(), whiteKing.getSquare().getY(), 65, 65);
        }
        else if (blackKing.isKingChecked()) {
            System.out.println(blackKing.getBlockMoves(this).size());
            g2.setStroke(new BasicStroke(5));
            g2.setPaint(Color.blue);
            for (Square i : blackKing.getBlockMoves(this))
                g2.drawRect(i.getX() + 2, i.getY() + 1, 62, 63);

            g2.setPaint(new Color(255, 0, 0, 180));
            g2.fillRect(blackKing.getSquare().getX(), blackKing.getSquare().getY(), 65, 65);
        }

        // Last Move
        if (start != null) {
            g2.setPaint(new Color(246, 246, 105));
            g2.fillRect(start.getX(), start.getY(), 65, 65);
            g2.fillRect(end.getX(), end.getY(), 65, 65);
        }

        // Guide Lines
        if (currPiece != null) {
            g2.setPaint(new Color(246, 246, 105));
            g2.fillRect(currPiece.getSquare().getX(), currPiece.getSquare().getY(), 65, 65);

            if (!whiteKing.isKingChecked() && !blackKing.isKingChecked()) {
                for (Square i : currPiece.getLegalMoves(this)) {
                    g2.setStroke(new BasicStroke(3));
                    if (i.isOccupied()) {
                        g2.setPaint(Color.red);
                        g2.drawRect(i.getX() + 2, i.getY() + 1, 62, 63);
                    } else {
                        g2.setPaint(new Color(180, 100, 100));
                        g2.fillOval(i.getX() + 19, i.getY() + 19, 25, 25);
                    }
                }
            }
            else if (whiteKing.isKingChecked() && !(currPiece instanceof King)) {
                for (Square i : currPiece.getLegalMoves(this)) {
                    if (whiteKing.getBlockMoves(this).contains(i)) {
                        g2.setStroke(new BasicStroke(3));
                        if (i.isOccupied()) {
                            g2.setPaint(Color.red);
                            g2.drawRect(i.getX() + 2, i.getY() + 1, 62, 63);
                        } else {
                            g2.setPaint(new Color(180, 100, 100));
                            g2.fillOval(i.getX() + 19, i.getY() + 19, 25, 25);
                        }
                    }
                }
            }
            else if (blackKing.isKingChecked()){
                for (Square i : currPiece.getLegalMoves(this)) {
                   if (blackKing.getBlockMoves(this).contains(i)) {
                       g2.setStroke(new BasicStroke(3));
                       if (i.isOccupied()) {
                           g2.setPaint(Color.red);
                           g2.drawRect(i.getX() + 2, i.getY() + 1, 62, 63);
                       } else {
                           g2.setPaint(new Color(180, 100, 100));
                           g2.fillOval(i.getX() + 19, i.getY() + 19, 25, 25);
                       }
                   }
                }
            }
        }

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Square sq = board[i][j];
                sq.paintComponent(g2);
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
        whiteKing = new King(1, board[7][4], "/ChessAssets/whiteKing.png");
        board[7][4].put(whiteKing);
        blackKing = new King(0, board[0][4], "/ChessAssets/blackKing.png");
        board[0][4].put(blackKing);

        // Queens
        board[7][3].put(new Queen(1, board[7][3], "/ChessAssets/whiteQueen.png"));
        board[0][3].put(new Queen(0, board[0][3], "/ChessAssets/blackQueen.png"));

        // Rooks
        board[7][0].put(new Rook(1, board[7][0], "/ChessAssets/whiteRook.png"));
        board[7][7].put(new Rook(1, board[7][7], "/ChessAssets/whiteRook.png"));
        board[0][0].put(new Rook(0, board[0][0], "/ChessAssets/blackRook.png"));
        board[0][7].put(new Rook(0, board[0][7], "/ChessAssets/blackRook.png"));

        // Bishops
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
    public Square[][] getBoard() {
        return board;
    }

    public boolean isWhiteTurn() {
        return whiteTurn;
    }

    public void setWhiteTurn(boolean whiteTurn) {
        this.whiteTurn = whiteTurn;
    }

    public void setCurrPiece(Piece currPiece) {
        this.currPiece = currPiece;
    }

    public void setLastPlay(Square start, Square end) {
        this.start = start;
        this.end = end;
    }
}
