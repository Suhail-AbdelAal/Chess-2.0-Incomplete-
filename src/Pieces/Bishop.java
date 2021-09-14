package Pieces;

import Tiles.Board;
import Tiles.Square;
import java.util.LinkedList;

public class Bishop extends Piece{

    private final LinkedList<Square> legalMoves;

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

        int x = this.getPosition().getxNum();
        int y = this.getPosition().getyNum();

        return legalMoves;
    }

}
