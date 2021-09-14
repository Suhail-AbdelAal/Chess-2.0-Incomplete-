package Pieces;

import Tiles.Board;
import Tiles.Square;
import java.util.LinkedList;

public class Knight extends Piece {

    private final LinkedList<Square> legalMoves;

    // Constructors
    public Knight(int color, Square initSq, String img_path) {
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
        int x = this.getPosition().getxNum();
        int y = this.getPosition().getyNum();

        for (int i = 2; i >= -2; i--) {
            if (i == 0) continue;
            int adjuster = (Math.abs(i) == 1) ? 2 : 1;
            for (int j = adjuster; j >= -2; j -= adjuster * 2) {
                try {
                    legalMoves.add(sq[x + i][y + j]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    continue;
                }
            }
        }
        return legalMoves;
    }
}
