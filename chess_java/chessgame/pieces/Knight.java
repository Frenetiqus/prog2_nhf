package hu.ppke.itk.java2020.maldo.hf03.chessgame.pieces;

import hu.ppke.itk.java2020.maldo.hf03.chessgame.exceptions.InvalidMoveException;
import hu.ppke.itk.java2020.maldo.hf03.chessgame.ChessTable;
import hu.ppke.itk.java2020.maldo.hf03.chessgame.misc.Color;
import hu.ppke.itk.java2020.maldo.hf03.chessgame.misc.Coord;
import hu.ppke.itk.java2020.maldo.hf03.chessgame.Player;

/**
 * Knight (lo) babu
 */
public class Knight extends Piece{
    public Knight(Player owner, ChessTable table, Coord location, Color color){
        super(owner,table,location,color);
    }
    /**
     * Feluldefinialt move fuggveny, a sajat mozgasanak megfelelolen mozog a tablan. Megnezi, hogy a megengedheto lepesek
     * kozott van e a megadott 'to' koordinata, ha nincs akkor exceptiont dob, egyebkent odalep, vagy leuti a babut ha mas
     * szinu
     * @param to az a koordinata ahova lepni szeretne a jatekos
     * @throws InvalidMoveException akkor lep fel, ha olyan helyre lep a jatekos amin sajat babu all, vagy ha a lepes utjaban
     * blokkolo babu van
     */
    public void move(Coord to) throws InvalidMoveException {

        if(to.x > location.x && to.y > location.y) {
            if(to.equals(new Coord(location.x + 2, location.y + 1))) {
                if (table.getPiece(to) == null) {
                    location.x = to.x;
                    location.y = to.y;
                    return;
                }
                if (table.getPiece(to) != null) {
                    if (table.getPiece(to).color != this.color) {
                        table.deletePiece(to);
                        location.x = to.x;
                        location.y = to.y;
                        return;
                    } else throw new InvalidMoveException("Cannot attack friendly piece.");
                }
            } else if(to.equals(new Coord(location.x + 1, location.y + 2))){
                if (table.getPiece(to) == null) {
                    location.x = to.x;
                    location.y = to.y;
                    return;
                }
                if (table.getPiece(to) != null) {
                    if (table.getPiece(to).color != this.color) {
                        table.deletePiece(to);
                        location.x = to.x;
                        location.y = to.y;
                        return;
                    } else throw new InvalidMoveException("Cannot attack friendly piece.");
                }
            }
        } else if(to.x > location.x && to.y < location.y) {
            if(to.equals(new Coord(location.x + 2, location.y - 1))) {
                if (table.getPiece(to) == null) {
                    location.x = to.x;
                    location.y = to.y;
                    return;
                }
                if (table.getPiece(to) != null) {
                    if (table.getPiece(to).color != this.color) {
                        table.deletePiece(to);
                        location.x = to.x;
                        location.y = to.y;
                        return;
                    } else throw new InvalidMoveException("Cannot attack friendly piece.");
                }
            } else if(to.equals(new Coord(location.x + 1, location.y - 2))){
                if (table.getPiece(to) == null) {
                    location.x = to.x;
                    location.y = to.y;
                    return;
                }
                if (table.getPiece(to) != null) {
                    if (table.getPiece(to).color != this.color) {
                        table.deletePiece(to);
                        location.x = to.x;
                        location.y = to.y;
                        return;
                    } else throw new InvalidMoveException("Cannot attack friendly piece.");
                }
            }
        } else if(to.x < location.x && to.y < location.y) {
            if(to.equals(new Coord(location.x - 2 , location.y - 1))) {
                if (table.getPiece(to) == null) {
                    location.x = to.x;
                    location.y = to.y;
                    return;
                }
                if (table.getPiece(to) != null) {
                    if (table.getPiece(to).color != this.color) {
                        table.deletePiece(to);
                        location.x = to.x;
                        location.y = to.y;
                        return;
                    } else throw new InvalidMoveException("Cannot attack friendly piece.");
                }
            } else if(to.equals(new Coord(location.x - 1, location.y - 2))){
                if (table.getPiece(to) == null) {
                    location.x = to.x;
                    location.y = to.y;
                    return;
                }
                if (table.getPiece(to) != null) {
                    if (table.getPiece(to).color != this.color) {
                        table.deletePiece(to);
                        location.x = to.x;
                        location.y = to.y;
                        return;
                    } else throw new InvalidMoveException("Cannot attack friendly piece.");
                }
            }
        } else if (to.x < location.x && to.y > location.y) {
            if(to.equals(new Coord(location.x - 2, location.y + 1))) {
                if (table.getPiece(to) == null) {
                    location.x = to.x;
                    location.y = to.y;
                    return;
                }
                if (table.getPiece(to) != null) {
                    if (table.getPiece(to).color != this.color) {
                        table.deletePiece(to);
                        location.x = to.x;
                        location.y = to.y;
                        return;
                    } else throw new InvalidMoveException("Cannot attack friendly piece.");
                }
            } else if(to.equals(new Coord(location.x - 1, location.y + 2))){
                if (table.getPiece(to) == null) {
                    location.x = to.x;
                    location.y = to.y;
                    return;
                }
                if (table.getPiece(to) != null) {
                    if (table.getPiece(to).color != this.color) {
                        table.deletePiece(to);
                        location.x = to.x;
                        location.y = to.y;
                    } else throw new InvalidMoveException("Cannot attack friendly piece.");
                }
            }
        }
        throw new InvalidMoveException("Invalid move with knight.");
    }
    /**
     * Feluldefinialt fuggveny,visszaadja a babu jelet es szinet
     * @return String-gel ter vissza melyben benne vannak adatai
     */
    @Override
    public String toString() {
        return "H " + color; // H as horse (it is better to print with this letter)
    }
    /**
     * Visszaadja a babu jelet
     * @return babu jelevel ter vissza Stringben
     */
    @Override
    public String nameToString() {
        return "H";
    }
}
