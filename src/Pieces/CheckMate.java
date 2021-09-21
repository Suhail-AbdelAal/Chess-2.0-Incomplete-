package Pieces;

import Tiles.Board;

import java.util.LinkedList;

public interface CheckMate {
    void checkDetector(Board board);
    void setKingChecked(boolean kingChecked);
    boolean isKingChecked();
    LinkedList<Piece> getAllowedPieces(Board board);
    boolean checkMate();
}
