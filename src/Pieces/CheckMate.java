package Pieces;

public interface CheckMate {
    boolean isKingChecked();
    void setKingChecked(boolean kingChecked);
    boolean causesCheck();
    boolean canBlock();
    boolean checkMate();
}
