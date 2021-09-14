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
        Square spot_end = (Square) board.getComponentAt(new Point(e.getX(), e.getY()));
        if (spot_end.isOccupied()) {
            currPiece = spot_end.getOccupyPiece();
//            System.out.println(currPiece.getLegalMoves(this).size());
            spot_start = spot_end;
        }

        else if (currPiece != null) {
            currPiece.setPosition(spot_end);
            spot_end.put(currPiece);
            currPiece.setFirstMoveDone(true);
            spot_start.removePiece();
            currPiece = null;
            board.repaint();
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
