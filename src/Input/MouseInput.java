package Input;

import Pieces.Piece;
import Tiles.Board;
import Tiles.Square;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener {

    private Board board;
    private Piece currPiece;
    private Square spot_start;

    // Constructors
    public MouseInput(Board board, Piece currPiece, Square spot_start) {
        this.board = board;
        this.currPiece = currPiece;
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

        if (board.isWhiteTurn()) {
            System.out.println("White Turn!");
            if (spot_end.isOccupied() && spot_end.getOccupyPiece().getColor() == 1) {
                currPiece = spot_end.getOccupyPiece();
//                System.out.println(currPiece.getLegalMoves(board).size());
                spot_start = spot_end;
            }

            else if (currPiece != null) {
                if (!spot_end.isOccupied()) {
                    spot_end.put(currPiece);
                    spot_start.removePiece();
                    currPiece.setFirstMoveDone(true);
                    board.setWhiteTurn(false);
                }
                else {
                    spot_end.capture(currPiece);
                    spot_start.removePiece();
                    System.out.println("Black: " + board.bPieces.size());
                    currPiece.setFirstMoveDone(true);
                    board.setWhiteTurn(false);
                }

                currPiece = null;
                board.repaint();
            }
        }
        else {
            System.out.println("Black Turn!");
            if (spot_end.isOccupied() && spot_end.getOccupyPiece().getColor() == 0) {
                currPiece = spot_end.getOccupyPiece();
//                System.out.println(currPiece.getLegalMoves(board).size());
                spot_start = spot_end;
            }

            else if (currPiece != null) {
                if (!spot_end.isOccupied()) {
                    spot_end.put(currPiece);
                    spot_start.removePiece();
                    currPiece.setFirstMoveDone(true);
                    board.setWhiteTurn(true);
                }
                else {
                    spot_end.capture(currPiece);
                    spot_start.removePiece();
                    System.out.println("White: " + board.wPieces.size());
                    currPiece.setFirstMoveDone(true);
                    board.setWhiteTurn(true);
                }

                currPiece = null;
                board.repaint();
            }
        }
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


}
