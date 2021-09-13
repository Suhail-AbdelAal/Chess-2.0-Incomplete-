package Pieces;

import Tiles.Board;
import Tiles.Square;

import java.util.LinkedList;

public class Knight extends Piece {

    public Knight(int color, Square initSq, String img_path) {
        super(color, initSq, img_path);
    }

    @Override
    public boolean canMove(Board board, Square start, Square end) {
        return false;
    }

    @Override
    public LinkedList<Square> getLegalMoves(Board board) {
        return null;
    }
}
