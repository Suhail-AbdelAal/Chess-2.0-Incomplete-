package Entity;

import Pieces.Piece;

import java.util.LinkedList;

public class Player {

    private Title title;
    private LinkedList<Piece> wPieces;
    private LinkedList<Piece> bPieces;

    public Player(Title title, LinkedList<Piece> wPieces, LinkedList<Piece> bPieces) {
        this.title = title;
        this.wPieces = wPieces;
        this.bPieces = bPieces;
    }
}
