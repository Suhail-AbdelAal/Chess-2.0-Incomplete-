package Pieces;

import Tiles.Game;
import Tiles.Square;
import java.util.LinkedList;

public class Knight extends Piece {

    private LinkedList<Square> legalMoves;

    // Constructors
    public Knight(int color, Square initSq, String img_path) {
        super(color, initSq, img_path);
        legalMoves = new LinkedList<>();
    }

    @Override
    public boolean canMove(Game game, Square start, Square end) {
        if (end.isOccupied())
            return start.getPiece().getColor() != end.getPiece().getColor();

        return true;
    }

    @Override
    public LinkedList<Square> getLegalMoves(Game game) {
        this.legalMoves.clear();
        Square[][] sq = game.getSquareArray();
        int x = this.getSquare().getxNum();
        int y = this.getSquare().getyNum();

        for (int i = 2; i >= -2; i--) {
            if (i == 0) continue;
            int adjuster = (Math.abs(i) == 1) ? 2 : 1;
            for (int j = adjuster; j >= -2; j -= adjuster * 2) {
                try {
                    if (this.canMove(game, sq[x][y], sq[x + i][y + j]))
                        legalMoves.add(sq[x + i][y + j]);
                } catch (ArrayIndexOutOfBoundsException ignored) {}
            }
        }
        return legalMoves;
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
