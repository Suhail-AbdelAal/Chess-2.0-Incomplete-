package Pieces;

import Tiles.Board;
import Tiles.Square;

import java.util.LinkedList;

public class Knight extends Piece {

    public Knight(boolean white, Square initSq, String img_path) {
        super(white, initSq, img_path);
    }

    @Override
    public boolean canMove(Board board, Square start, Square end) {
        return false;
    }

    @Override
    public LinkedList<Square> getLegalMoves(Board b) {
        return null;
    }
}