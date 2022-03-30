package hu.ppke.itk.java2020.maldo.hf03.chessgame.pieces;

import hu.ppke.itk.java2020.maldo.hf03.chessgame.exceptions.InvalidMoveException;
import hu.ppke.itk.java2020.maldo.hf03.chessgame.ChessTable;
import hu.ppke.itk.java2020.maldo.hf03.chessgame.misc.Color;
import hu.ppke.itk.java2020.maldo.hf03.chessgame.misc.Coord;
import hu.ppke.itk.java2020.maldo.hf03.chessgame.Player;

/**
 * Pawn (paraszt) babu
 */
public class Pawn extends Piece{
    protected final Coord startingLoc;

    public Pawn(Player owner, ChessTable table, Coord location, Color color, Coord startLoc){
        super(owner,table,location,color);
        this.startingLoc = startLoc;
    }

    /**
     * Tud egyet lepni, kettot ha a kezdo pozicion van, illetve atlosan ha tud leutni babut amennyiben az mas szinu
     * @param to a koordinata ahova lepni akar
     * @throws InvalidMoveException akkor lep fel ha a megengedett lepesek kozul egyik sem megvalosithato
     */
    public void move(Coord to) throws InvalidMoveException {
        if(this.color == Color.WHITE) {
            if (to.y == location.y + 1 && to.x == location.x && table.getPiece(to) == null) {
                location.y = to.y;
            } else if (location.equals(startingLoc) && to.y == location.y + 2 && to.x == location.x && table.getPiece(to) == null) {
                location.y = to.y;
            } else if (to.y == location.y + 1 && to.x == location.x + 1 && table.getPiece(to) != null && table.getPiece(to).color!=this.color) {
                table.deletePiece(to);
                location.x = to.x;
                location.y = to.y;
            } else if (to.y == location.y + 1 && to.x == location.x - 1 && table.getPiece(to) != null && table.getPiece(to).color!=this.color) {
                table.deletePiece(to);
                location.x = to.x;
                location.y = to.y;
            } else {
                throw new InvalidMoveException("Invalid move with pawn.");
            }
        }else if(this.color == Color.BlACK){
            if (to.y == location.y - 1 && to.x == location.x && table.getPiece(to) == null) {
                location.y = to.y;
            } else if (location.equals(startingLoc) && to.y == location.y - 2 && to.x == location.x && table.getPiece(to) == null) {
                location.y = to.y;
            } else if (to.y == location.y - 1 && to.x == location.x + 1 && table.getPiece(to) != null && table.getPiece(to).color!=this.color) {
                table.deletePiece(to);
                location.x = to.x;
                location.y = to.y;
            } else if (to.y == location.y - 1  && to.x == location.x - 1 && table.getPiece(to) != null && table.getPiece(to).color!=this.color) {
                table.deletePiece(to);
                location.x = to.x;
                location.y = to.y;
            } else {
                throw new InvalidMoveException("Invalid move with pawn.");
            }
        }
    }
    public Coord getStartingLoc(){
        return startingLoc;
    }
    /**
     * Feluldefinialt fuggveny,visszaadja a babu jelet es szinet
     * @return String-gel ter vissza melyben benne vannak adatai
     */
    @Override
    public String toString() {
        return "P " + color;
    }
    /**
     * Visszaadja a babu jelet
     * @return babu jelevel ter vissza Stringben
     */
    @Override
    public String nameToString() {
        return "P";
    }
}
