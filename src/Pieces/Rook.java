package Pieces;

import Tiles.Game;
import Tiles.Square;
import java.util.LinkedList;

public class Rook extends Piece {

    private LinkedList<Square> legalMoves;

    // Constructors
    public Rook(int color, Square initSq, String img_path) {
        super(color, initSq, img_path);
        legalMoves = new LinkedList<>();
    }

    @Override
    public boolean canMove(Game game, Square start, Square end) {
        return false;
    }

    @Override
    public LinkedList<Square> getLegalMoves(Game game) {
        this.legalMoves.clear();
        Square[][] sq = game.getBoard();

        int x = this.getSquare().getxNum();
        int y = this.getSquare().getyNum();

        int[] occupations = super.Linear_Occupied_Spots(game, x, y);

        // Vertically
        for (int i = occupations[0]; i <= occupations[1]; i++) {
            if (i != x)
                legalMoves.add(sq[i][y]);
        }
        // Horizontally
        for (int i = occupations[3]; i <= occupations[2]; i++) {
            if (i != y)
                legalMoves.add(sq[x][i]);
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
