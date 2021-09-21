package Pieces;

import Tiles.Board;
import Tiles.Square;
import java.util.LinkedList;

public class Bishop extends Piece{

    private LinkedList<Square> legalMoves;

    // Constructors
    public Bishop(int color, Square initSq, String img_path) {
        super(color, initSq, img_path);
        legalMoves = new LinkedList<>();
    }

    @Override
    public boolean canMove(Board board, Square start, Square end) {
        return false;
    }

    @Override
    public LinkedList<Square> getLegalMoves(Board board) {
        legalMoves.clear();
        Square[][] sq = board.getSquareArray();

        int x = this.getSquare().getxNum();
        int y = this.getSquare().getyNum();

        int[] occupations = super.Diagonal_Occupied_Spots(board, x, y);

        // TOP RIGHT -> BOTTOM LEFT
        for (int i = occupations[0], j = occupations[1]; i <= occupations[6] && j >= occupations[7]; i++, j--) {
            if (i != x && j != y)
                legalMoves.add(sq[i][j]);
        }
        // TOP LEFT -> BOTTOM RIGHT
        for (int i = occupations[2], j = occupations[3]; i <= occupations[4] && j <= occupations[5]; i++, j++) {
            if (i != x && j != y)
                legalMoves.add(sq[i][j]);
        }
        return legalMoves;
    }

}
