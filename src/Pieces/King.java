package Pieces;

import Tiles.Board;
import Tiles.Square;
import java.util.LinkedList;

public class King extends Piece {

    private LinkedList<Square> legalMoves;

    public King(int color, Square initSq, String img_path) {
        super(color, initSq, img_path);
        legalMoves = new LinkedList<>();
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

        int x = this.getPosition().getxNum();
        int y = this.getPosition().getyNum();

        for (int i = 1; i > -2; i--) {
            for (int j = 1; j > -2; j--) {
                if (i == x && j == y) continue;
                try {
                    if (this.canMove(board, this.getPosition(), sq[x + i][y + j]))
                        legalMoves.add(sq[x + i][y + j]);
                } catch (ArrayIndexOutOfBoundsException ignored) {}
            }
        }
        return legalMoves;
    }
}
