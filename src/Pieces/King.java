package Pieces;

import Tiles.Game;
import Tiles.Square;
import java.util.LinkedList;

public class King extends Piece implements CheckMate {

    private LinkedList<Square> legalMoves;
    private final LinkedList<Square> blockMoves;
    private final LinkedList<Piece> allowedPieces;
    private boolean kingChecked;
    private Piece checkPiece;
    private Piece currPiece;

    public King(int color, Square initSq, String img_path) {
        super(color, initSq, img_path);
        legalMoves = new LinkedList<>();
        blockMoves = new LinkedList<>();
        allowedPieces = new LinkedList<>();
        kingChecked = false;
    }


    // Methods
    public LinkedList<Square> getBlockMoves(Game game) {
        blockMoves.clear();
        Square[][] sq = game.getSquareArray();

        int xPiece = checkPiece.getSquare().getxNum();
        int yPiece = checkPiece.getSquare().getyNum();

        int xKing = this.getSquare().getxNum();
        int yKing = this.getSquare().getyNum();

        blockMoves.add(sq[xPiece][yPiece]);
        if (xPiece < xKing) {
            for (int i = 0; i < Math.abs(xKing - xPiece); i++) {
                try {
                    if (yPiece < yKing && checkPiece.getLegalMoves(game).contains(sq[xPiece + i][yPiece + i]))
                        blockMoves.add(sq[xPiece + i][yPiece + i]);
                    else if (yPiece > yKing && checkPiece.getLegalMoves(game).contains(sq[xPiece + i][yPiece - i]))
                        blockMoves.add(sq[xPiece + i][yPiece - i]);
                    else if (yPiece == yKing && checkPiece.getLegalMoves(game).contains(sq[xPiece + i][yPiece]))
                        blockMoves.add(sq[xPiece + i][yPiece]);

                } catch(ArrayIndexOutOfBoundsException ignored) {}
            }
        }
        else if (xPiece > xKing) {
            for (int i = 0; i < Math.abs(xKing - xPiece); i++) {
                try {
                    if (yPiece < yKing && checkPiece.getLegalMoves(game).contains(sq[xPiece - i][yPiece + i]))
                        blockMoves.add(sq[xPiece - i][yPiece + i]);
                    else if (yPiece > yKing && checkPiece.getLegalMoves(game).contains(sq[xPiece - i][yPiece - i]))
                        blockMoves.add(sq[xPiece - i][yPiece - i]);
                    else if (yPiece == yKing && checkPiece.getLegalMoves(game).contains(sq[xPiece - i][yPiece]))
                        blockMoves.add(sq[xPiece - i][yPiece]);

                } catch(ArrayIndexOutOfBoundsException ignored) {}
            }
        }
        else {
            for (int i = 0; i < Math.abs(yKing - yPiece); i++) {
                try {
                    if (yPiece < yKing  && checkPiece.getLegalMoves(game).contains(sq[xPiece][yPiece + i]))
                        blockMoves.add(sq[xPiece][yPiece + i]);
                    else if (yPiece > yKing && checkPiece.getLegalMoves(game).contains(sq[xPiece][yPiece - i]))
                        blockMoves.add(sq[xPiece][yPiece - i]);

                } catch(ArrayIndexOutOfBoundsException ignored) {}
            }
        }
        return blockMoves;
    }

    @Override
    public boolean canMove(Game game, Square start, Square end) {
        if (end.isOccupied())
            return start.getPiece().getColor() != end.getPiece().getColor();

        return true;
    }

    @Override
    public LinkedList<Square> getLegalMoves(Game game) {
        legalMoves.clear();
        Square[][] sq = game.getSquareArray();

        int x = this.getSquare().getxNum();
        int y = this.getSquare().getyNum();

        for (int i = 1; i > -2; i--) {
            for (int j = 1; j > -2; j--) {
                if (i == x && j == y) continue;
                try {
                    if (this.canMove(game, this.getSquare(), sq[x + i][y + j]) && this.isSquareSave(game, sq[x + i][y + j])) {
                        legalMoves.add(sq[x + i][y + j]);
                    }
                } catch (ArrayIndexOutOfBoundsException ignored) {}
            }
        }
        return legalMoves;
    }

    @Override
    public void kingCheckDetector(Game game) {
        if (this.getColor() == 1) {
            for (Piece i : game.bPieces) {
                 kingChecked = i.getLegalMoves(game).contains(this.getSquare());
                 if (kingChecked) {
                    checkPiece = i;
                    this.getBlockMoves(game);
                    break;
                 }
            }
        }
        else {
            for (Piece i : game.wPieces) {
                kingChecked = i.getLegalMoves(game).contains(this.getSquare());
                if (kingChecked) {
                    checkPiece = i;
                    this.getBlockMoves(game);
                    break;
                }
            }
        }
        this.setKingChecked(kingChecked);
    }

    @Override
    public boolean isSquareSave(Game game, Square spot) {
        if (this.getColor() == 1) {
            for (Piece i : game.bPieces) {
                if (i instanceof King) continue;
                if (i.getLegalMoves(game).contains(spot))
                    return false;
            }
        } else {
            for (Piece i : game.wPieces) {
                if (i instanceof King) continue;
                if (i.getLegalMoves(game).contains(spot))
                    return false;
            }
        }
        return true;
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
    public boolean checkMate() {
        return false;
    }

    @Override
    public LinkedList<Square> returnLegalMoves() {
        return legalMoves;
    }
    @Override
    public void clearLegalMoves() {
        legalMoves.clear();
    }
}
