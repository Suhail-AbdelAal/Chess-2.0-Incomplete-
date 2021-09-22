package Pieces;

import Tiles.Game;
import Tiles.Square;

public interface CheckMate {
    void kingCheckDetector(Game game);
    boolean isSquareSave(Game game, Square spot);
    void setKingChecked(boolean kingChecked);
    boolean isKingChecked();
    boolean checkMate();
}
