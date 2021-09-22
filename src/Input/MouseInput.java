package Input;

import Pieces.King;
import Pieces.Piece;
import Tiles.Game;
import Tiles.Square;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import static Tiles.Game.blackKing;
import static Tiles.Game.whiteKing;

public class MouseInput implements MouseListener {

    private Game game;
    public static Piece currPiece;
    private Square spot_start;

    // Constructors
    public MouseInput(Game game, Square spot_start) {
        this.game = game;
        this.spot_start = spot_start;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        int currX = e.getX();
        int currY = e.getY();
        Square spot_end = (Square) game.getComponentAt(new Point(currX, currY));

        // WHITE
        if (game.isWhiteTurn()) {
            if (spot_end.isOccupied() && spot_end.getPiece().getColor() == 1) {
                currPiece = spot_end.getPiece();
                game.setCurrPiece(currPiece);
                spot_start = spot_end;
            }
            else if (currPiece != null) {
                if (whiteKing.isKingChecked() && !(currPiece instanceof King)) {
                    if (currPiece.getLegalMoves(game).contains(spot_end) && whiteKing.getBlockMoves(game).contains(spot_end))
                        whiteMove(spot_end);
                } else {
                    if (currPiece.getLegalMoves(game).contains(spot_end))
                        whiteMove(spot_end);
                }
            }
        }

        // BLACK
        else {
            if (spot_end.isOccupied() && spot_end.getPiece().getColor() == 0) {
                currPiece = spot_end.getPiece();
                game.setCurrPiece(currPiece);
                spot_start = spot_end;
            }
            else if (currPiece != null) {
                if (blackKing.isKingChecked() && !(currPiece instanceof King)) {
                    if (currPiece.getLegalMoves(game).contains(spot_end) && blackKing.getBlockMoves(game).contains(spot_end))
                        blackMove(spot_end);
                } else {
                    if (currPiece.getLegalMoves(game).contains(spot_end))
                        blackMove(spot_end);
                }
            }
        }
        whiteKing.kingCheckDetector(game);
        blackKing.kingCheckDetector(game);
        game.repaint();
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

    private void whiteMove(Square spot_end) {
        if (!spot_end.isOccupied()) {
            spot_end.put(currPiece);
        } else {
            spot_end.capture(currPiece);
        }
        spot_start.removePiece();
        currPiece.setFirstMoveDone(true);
        game.setWhiteTurn(false);
        game.setLastPlay(spot_start, spot_end);
        currPiece = null;
        game.setCurrPiece(null);
    }
    private void blackMove(Square spot_end) {
        if (!spot_end.isOccupied()) {
            spot_end.put(currPiece);
        } else {
            spot_end.capture(currPiece);
        }
        spot_start.removePiece();
        currPiece.setFirstMoveDone(true);
        game.setWhiteTurn(true);
        game.setLastPlay(spot_start, spot_end);
        currPiece = null;
        game.setCurrPiece(null);
    }


}
