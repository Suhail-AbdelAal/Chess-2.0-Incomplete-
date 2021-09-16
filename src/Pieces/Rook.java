package Pieces;

import Tiles.Board;
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
    public boolean canMove(Board board, Square start, Square end) {
        return false;
    }

    @Override
    public LinkedList<Square> getLegalMoves(Board board) {
        this.legalMoves.clear();
        Square[][] sq = board.getSquareArray();
        Square currSq = this.getPosition();

        int x = currSq.getxNum();
        int y = currSq.getyNum();

        int[] occupations = super.Linear_Occupied_Spots(board, x, y);

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


}
