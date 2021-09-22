package Pieces;

import Tiles.Game;
import Tiles.Square;
import java.util.LinkedList;

public class Pawn extends Piece {

    private LinkedList<Square> legalMoves;
    private Square leftSide;
    private Square rightSide;
    public Pawn(int color, Square initSq, String img_path) {
        super(color, initSq, img_path);
        legalMoves = new LinkedList<>();
    }

    @Override
    public boolean canMove(Game game, Square start, Square end) {
        return this.getLegalMoves(game).contains(end);
    }

    @Override
    public LinkedList<Square> getLegalMoves(Game game) {
        this.legalMoves.clear();
        leftSide = null;
        rightSide = null;

        Square[][] sq = game.getBoard();
        Square pos = this.getSquare();

        int m = !this.isFirstMoveDone() ? 2 : 1;

        if (this.getColor() == 1) {
            for (int i = 1; i <= m; i++) {
                if (sq[pos.getxNum() - i][pos.getyNum()].isOccupied())
                    break;

                legalMoves.add(sq[pos.getxNum() - i][pos.getyNum()]);
            }

            if (pos.getxNum() - 1 >= 0 && pos.getyNum() - 1 >= 0)
                leftSide = sq[pos.getxNum() - 1][pos.getyNum() - 1];

            if (pos.getxNum() - 1 >= 0&& pos.getyNum() + 1 <= 7)
                rightSide = sq[pos.getxNum() - 1][pos.getyNum() + 1];

            if (leftSide != null) {
                if (leftSide.isOccupied() && leftSide.getPiece().getColor() == 0)
                    legalMoves.add(leftSide);
            }

            if (rightSide != null) {
                if (rightSide.isOccupied() && rightSide.getPiece().getColor() == 0)
                    legalMoves.add(rightSide);
            }
            return legalMoves;
        }

        // black
        for (int i = 1; i <= m; i++) {
            if (sq[pos.getxNum() + i][pos.getyNum()].isOccupied())
                break;

            legalMoves.add(sq[pos.getxNum() + i][pos.getyNum()]);
        }

        if (pos.getxNum() + 1 <= 7 && pos.getyNum() + 1 <= 7)
            leftSide = sq[pos.getxNum() + 1][pos.getyNum() + 1];

        if (pos.getxNum() + 1 <= 7 && pos.getyNum() - 1 >= 0)
            rightSide = sq[pos.getxNum() + 1][pos.getyNum() - 1];

        if (leftSide != null) {
            if (leftSide.isOccupied() && leftSide.getPiece().getColor() == 1)
                legalMoves.add(leftSide);
        }

        if (rightSide != null) {
            if (rightSide.isOccupied() && rightSide.getPiece().getColor() == 1)
                legalMoves.add(rightSide);
        }
        return legalMoves;
    }
    @Override
    public LinkedList<Square> returnLegalMoves() {
        return legalMoves;
    }
    @Override
    public void clearLegalMoves() {
        legalMoves.clear();
    }
}
