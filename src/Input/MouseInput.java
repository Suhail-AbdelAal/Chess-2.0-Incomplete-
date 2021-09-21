package Input;

import Pieces.Piece;
import Tiles.Board;
import Tiles.Square;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import static Tiles.Board.blackKing;
import static Tiles.Board.whiteKing;

public class MouseInput implements MouseListener {

    private Board board;
    public static Piece currPiece;
    private Square spot_start;

    // Constructors
    public MouseInput(Board board, Square spot_start) {
        this.board = board;
        this.spot_start = spot_start;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        int currX = e.getX();
        int currY = e.getY();
        Square spot_end = (Square) board.getComponentAt(new Point(currX, currY));

        // WHITE
        if (board.isWhiteTurn()) {
            if (spot_end.isOccupied() && spot_end.getPiece().getColor() == 1) {
                currPiece = spot_end.getPiece();
                board.setCurrPiece(currPiece);
                spot_start = spot_end;
            }
            else if (currPiece != null) {
                if (currPiece.getLegalMoves(board).contains(spot_end)) {
                    if (!spot_end.isOccupied()) {
                        spot_end.put(currPiece);
                    } else {
                        spot_end.capture(currPiece);
                    }
                    spot_start.removePiece();
                    currPiece.setFirstMoveDone(true);
                    board.setWhiteTurn(false);
                    board.setLastPlay(spot_start, spot_end);
                    currPiece = null;
                    board.setCurrPiece(null);
                }
            }
        }

        //  BLACK
        else {
            if (spot_end.isOccupied() && spot_end.getPiece().getColor() == 0) {
                currPiece = spot_end.getPiece();
                board.setCurrPiece(currPiece);
                spot_start = spot_end;
            }
            else if (currPiece != null) {

                if (currPiece.getLegalMoves(board).contains(spot_end)) {
                    if (!spot_end.isOccupied()) {
                        spot_end.put(currPiece);
                    } else {
                        spot_end.capture(currPiece);
                    }
                    spot_start.removePiece();
                    currPiece.setFirstMoveDone(true);
                    board.setWhiteTurn(true);
                    board.setLastPlay(spot_start, spot_end);
                    currPiece = null;
                    board.setCurrPiece(null);
                }
            }
        }
        whiteKing.checkDetector(board);
        blackKing.checkDetector(board);
        board.repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public Piece getCurrPiece() {
        return  currPiece;
    }


}
