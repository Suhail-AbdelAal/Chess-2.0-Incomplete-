package Pieces;

import Tiles.Board;
import Tiles.Square;
import java.util.LinkedList;

public class Rook extends Piece {

    private LinkedList<Square> legalMoves;

    // Constructors
    public Rook(int color, Square initSq, String img_path) {
        super(color, initSq, img_path);
        legalMoves = new LinkedList<>();
    }

    @Override
    public boolean canMove(Board board, Square start, Square end) {
        return start.getOccupyPiece().getColor() != end.getOccupyPiece().getColor();
    }

    @Override
    public LinkedList<Square> getLegalMoves(Board board) {
        legalMoves.clear();
        Square[][] sq = board.getSquareArray();
        Square pos = this.getPosition();

        int x = this.getPosition().getxNum();
        int y = this.getPosition().getyNum();

        for (int i = x + 1; i <= Math.abs(x - 7); i++) {
            if (!sq[i][y].isOccupied()) {
                try {
                    legalMoves.add(sq[i][y]);
                } catch (ArrayIndexOutOfBoundsException ignored) {
                }
            }
            else {
                if (canMove(board, pos, sq[i][y])) {
                    try {
                        legalMoves.add(sq[i][y]);
                    } catch (ArrayIndexOutOfBoundsException ignored) {
                    }
                }
                else break;
            }
        }

        for (int i = x - 1; i >= 0; i--) {
            if (!sq[i][y].isOccupied()) {
                try {
                    legalMoves.add(sq[i][y]);
                } catch (ArrayIndexOutOfBoundsException ignored) {
                }
            }
            else {
                if (canMove(board, pos, sq[i][y])) {
                    try {
                        legalMoves.add(sq[i][y]);
                    } catch (ArrayIndexOutOfBoundsException ignored) {
                    }
                }
                else break;
            }
        }

        for (int i = y + 1; i <= Math.abs(y - 7); i++) {
            if (!sq[x][i].isOccupied()) {
                try {
                    legalMoves.add(sq[x][i]);
                } catch (ArrayIndexOutOfBoundsException ignored) {
                }
            }
            else {
                if (canMove(board, pos, sq[x][i])) {
                    try {
                        legalMoves.add(sq[x][i]);
                    } catch (ArrayIndexOutOfBoundsException ignored) {
                    }
                }
                else
                    break;
            }
        }

        for (int i = y - 1; i >= 0; i--) {
            if (!sq[x][i].isOccupied()) {
                try {
                    legalMoves.add(sq[x][i]);
                } catch (ArrayIndexOutOfBoundsException ignored) {
                }
            }
            else {
                if (canMove(board, pos, sq[x][i])) {
                    try {
                        legalMoves.add(sq[x][i]);
                    } catch (ArrayIndexOutOfBoundsException ignored) {
                    }
                }
                else
                    break;
            }
        }

        return legalMoves;
    }
}
