package Pieces;

import Tiles.Board;
import Tiles.Square;

import java.util.LinkedList;

public class Queen extends Piece{

    private LinkedList<Square> legalMoves;

    public Queen(int color, Square initSq, String img_path) {
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

        // --- Linear Occupations ---

        int[] linear_occupations = super.Linear_Occupied_Spots(board, x, y);

        // Vertically
        for (int i = linear_occupations[0]; i <= linear_occupations[1]; i++) {
            if (i != x)
                legalMoves.add(sq[i][y]);
        }
        // Horizontally
        for (int i = linear_occupations[3]; i <= linear_occupations[2]; i++) {
            if (i != y)
                legalMoves.add(sq[x][i]);
        }

        // --- Diagonal Occupations ---
        int[] diagonal_occupations = super.Diagonal_Occupied_Spots(board, x, y);

        // TOP RIGHT -> BOTTOM LEFT
        for (int i = diagonal_occupations[0], j = diagonal_occupations[1]; i <= diagonal_occupations[6] && j >= diagonal_occupations[7]; i++, j--) {
            if (i != x && j != y)
                legalMoves.add(sq[i][j]);
        }
        // TOP LEFT -> BOTTOM RIGHT
        for (int i = diagonal_occupations[2], j = diagonal_occupations[3]; i <= diagonal_occupations[4] && j <= diagonal_occupations[5]; i++, j++) {
            if (i != x && j != y)
                legalMoves.add(sq[i][j]);
        }
        return legalMoves;
    }

}
