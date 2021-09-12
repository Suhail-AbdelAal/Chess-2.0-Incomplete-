package Pieces;

import Tiles.Board;
import Tiles.Square;

public abstract class Piece {

    private boolean killed = false;
    private boolean isWhite = false;

    // Constructors
    public Piece(boolean white)
    {
        this.setWhite(white);
    }

    // Methods
    public abstract boolean canMove(Board board, Square start, Square end);

    // Setters & Getters
    public boolean isWhite()
    {
        return this.isWhite;
    }

    public void setWhite(boolean white)
    {
        this.isWhite = white;
    }

    public boolean isKilled()
    {
        return this.killed;
    }

    public void setKilled(boolean killed)
    {
        this.killed = killed;
    }


}