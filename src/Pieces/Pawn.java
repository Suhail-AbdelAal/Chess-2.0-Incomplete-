package Pieces;

import Tiles.Board;
import Tiles.Square;
import java.util.LinkedList;

public class Pawn extends Piece {

    LinkedList<Square> legalMoves;
    private Square leftSide;
    private Square rightSide;
    public Pawn(int color, Square initSq, String img_path) {
        super(color, initSq, img_path);
        legalMoves = new LinkedList<>();
    }

    @Override
    public boolean canMove(Board board, Square start, Square end) {
        return this.getLegalMoves(board).contains(end);
    }

    @Override
    public LinkedList<Square> getLegalMoves(Board board) {
        this.legalMoves.clear();
        leftSide = null;
        rightSide = null;
        Square[][] sq = board.getSquareArray();
        Square pos = this.getPosition();

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
                if (leftSide.isOccupied() && leftSide.getOccupyPiece().getColor() == 0)
                    legalMoves.add(leftSide);
            }

            if (rightSide != null) {
                if (rightSide.isOccupied() && rightSide.getOccupyPiece().getColor() == 0)
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
            if (leftSide.isOccupied() && leftSide.getOccupyPiece().getColor() == 1)
                legalMoves.add(leftSide);
        }

        if (rightSide != null) {
            if (rightSide.isOccupied() && rightSide.getOccupyPiece().getColor() == 1)
                legalMoves.add(rightSide);
        }

        return legalMoves;
    }


}
