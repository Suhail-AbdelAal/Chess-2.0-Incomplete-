package Pieces;

import Tiles.Board;
import Tiles.Square;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicReference;

public class King extends Piece implements CheckMate {

    private LinkedList<Square> legalMoves;
    private LinkedList<Square> blockMoves;
    private LinkedList<Piece> allowedPieces;
    private boolean kingChecked;
    private Piece checkPiece;

    public King(int color, Square initSq, String img_path) {
        super(color, initSq, img_path);
        legalMoves = new LinkedList<>();
        blockMoves = new LinkedList<>();
        allowedPieces = new LinkedList<>();
        kingChecked = false;
    }


    // Methods
    public LinkedList<Square> getBlockMoves(Board board) {
        blockMoves.clear();
        Square[][] sq = board.getSquareArray();

        int xPiece = checkPiece.getSquare().getxNum();
        int yPiece = checkPiece.getSquare().getyNum();

        int xKing = this.getSquare().getxNum();
        int yKing = this.getSquare().getyNum();

        blockMoves.add(sq[xPiece][yPiece]);
        if (xPiece < xKing) {
            for (int i = 0; i < Math.abs(xKing - xPiece); i++) {
                try {
                    if (yPiece < yKing && checkPiece.getLegalMoves(board).contains(sq[xPiece + i][yPiece + i]))
                        blockMoves.add(sq[xPiece + i][yPiece + i]);
                    else if (yPiece > yKing && checkPiece.getLegalMoves(board).contains(sq[xPiece + i][yPiece - i]))
                        blockMoves.add(sq[xPiece + i][yPiece - i]);
                    else if (yPiece == yKing && checkPiece.getLegalMoves(board).contains(sq[xPiece + i][yPiece]))
                        blockMoves.add(sq[xPiece + i][yPiece]);

                } catch(ArrayIndexOutOfBoundsException ignored) {}
            }
        }
        else if (xPiece > xKing) {
            for (int i = 0; i < Math.abs(xKing - xPiece); i++) {
                try {
                    if (yPiece < yKing && checkPiece.getLegalMoves(board).contains(sq[xPiece - i][yPiece + i]))
                        blockMoves.add(sq[xPiece - i][yPiece + i]);
                    else if (yPiece > yKing && checkPiece.getLegalMoves(board).contains(sq[xPiece - i][yPiece - i]))
                        blockMoves.add(sq[xPiece - i][yPiece - i]);
                    else if (yPiece == yKing && checkPiece.getLegalMoves(board).contains(sq[xPiece - i][yPiece]))
                        blockMoves.add(sq[xPiece - i][yPiece]);

                } catch(ArrayIndexOutOfBoundsException ignored) {}
            }
        }
        else {
            for (int i = 0; i < Math.abs(yKing - yPiece); i++) {
                try {
                    if (yPiece < yKing  && checkPiece.getLegalMoves(board).contains(sq[xPiece][yPiece + i]))
                        blockMoves.add(sq[xPiece][yPiece + i]);
                    else if (yPiece > yKing && checkPiece.getLegalMoves(board).contains(sq[xPiece][yPiece - i]))
                        blockMoves.add(sq[xPiece][yPiece - i]);

                } catch(ArrayIndexOutOfBoundsException ignored) {}
            }
        }
        return blockMoves;
    }

    @Override
    public boolean canMove(Board board, Square start, Square end) {
        if (end.isOccupied())
            return start.getPiece().getColor() != end.getPiece().getColor();

        return true;
    }

    @Override
    public LinkedList<Square> getLegalMoves(Board board) {
        legalMoves.clear();
        Square[][] sq = board.getSquareArray();

        int x = this.getSquare().getxNum();
        int y = this.getSquare().getyNum();

        for (int i = 1; i > -2; i--) {
            for (int j = 1; j > -2; j--) {
                if (i == x && j == y) continue;
                try {
                    if (this.canMove(board, this.getSquare(), sq[x + i][y + j]))
                        legalMoves.add(sq[x + i][y + j]);
                } catch (ArrayIndexOutOfBoundsException ignored) {}
            }
        }
        return legalMoves;
    }

    @Override
    public void checkDetector(Board board) {
        if (this.getColor() == 1) {
            for (Piece i : board.bPieces) {
                i.getLegalMoves(board);
                 kingChecked = i.getLegalMoves(board).contains(this.getSquare());
                 if (kingChecked) {
                    checkPiece = i;
                    this.getAllowedPieces(board);
                     break;
                 }
            }
        }
        else {
            for (Piece i : board.wPieces) {
                i.getLegalMoves(board);
                kingChecked = i.getLegalMoves(board).contains(this.getSquare());
                if (kingChecked) {
                    checkPiece = i;
                    this.getAllowedPieces(board);
                    break;
                }
            }
        }
        this.setKingChecked(kingChecked);
    }

    @Override
    public void setKingChecked(boolean kingChecked) {
        this.kingChecked = kingChecked;
    }

    @Override
    public boolean isKingChecked() {
        return this.kingChecked;
    }

    @Override
    public LinkedList<Piece> getAllowedPieces(Board board) {
        if (this.getColor() == 1) {
            for (Piece i : board.wPieces) {
                AtomicReference<Piece> currPiece = new AtomicReference<>(i);
                i.getLegalMoves(board).clear();
                for (Square j : this.getBlockMoves(board)) {
                    if (currPiece.get().getLegalMoves(board).contains(j)) {
                        allowedPieces.add(i);
                        i.getLegalMoves(board).add(j);
                    }
                }
            }
        }
        else {
            for (Piece i : board.bPieces) {
                AtomicReference<Piece> currPiece = new AtomicReference<>(i);
                i.getLegalMoves(board).clear();
                for (Square j : this.getBlockMoves(board)) {
                    if (currPiece.get().getLegalMoves(board).contains(j)) {
                        allowedPieces.add(i);
                        i.getLegalMoves(board).add(j);
                    }
                }
            }
        }
        return allowedPieces;
    }

    @Override
    public boolean checkMate() {
        return false;
    }
}
