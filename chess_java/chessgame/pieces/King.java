package hu.ppke.itk.java2020.maldo.hf03.chessgame.pieces;

import hu.ppke.itk.java2020.maldo.hf03.chessgame.exceptions.InvalidMoveException;
import hu.ppke.itk.java2020.maldo.hf03.chessgame.ChessTable;
import hu.ppke.itk.java2020.maldo.hf03.chessgame.misc.Color;
import hu.ppke.itk.java2020.maldo.hf03.chessgame.misc.Coord;
import hu.ppke.itk.java2020.maldo.hf03.chessgame.Player;

/**
 * King (kiraly) babu
 */
public class King extends Piece{
    public King(Player owner, ChessTable table, Coord location, Color color){
        super(owner,table,location,color);
    }
    /**
     * Feluldefinialt move fuggveny, a sajat mozgasanak megfelelolen mozog a tablan
     * @param to az a koordinata ahova lepni szeretne a jatekos
     * @throws InvalidMoveException akkor lep fel, ha olyan helyre lep a jatekos amin sajat babu all, vagy ha a lepes utjaban
     * blokkolo babu van
     */
    public void move(Coord to) throws InvalidMoveException{
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if(to.equals(new Coord(location.x + i , location.y + j))) {
                    if(location.x == location.x + i && location.y == location.y + j) continue;
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
            }
        }
        throw new InvalidMoveException("Invalid move with king.");
    }

    /**
     * Feluldefinialt fuggveny,visszaadja a babu jelet es szinet
     * @return String-gel ter vissza melyben benne vannak adatai
     */
    @Override
    public String toString() {
        return "K " + color;
    }
    /**
     * Visszaadja a babu jelet
     * @return babu jelevel ter vissza Stringben
     */
    @Override
    public String nameToString() {
        return "K";
    }
}
