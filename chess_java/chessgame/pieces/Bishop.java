package hu.ppke.itk.java2020.maldo.hf03.chessgame.pieces;

import hu.ppke.itk.java2020.maldo.hf03.chessgame.exceptions.InvalidMoveException;
import hu.ppke.itk.java2020.maldo.hf03.chessgame.ChessTable;
import hu.ppke.itk.java2020.maldo.hf03.chessgame.misc.Color;
import hu.ppke.itk.java2020.maldo.hf03.chessgame.misc.Coord;
import hu.ppke.itk.java2020.maldo.hf03.chessgame.Player;

/**
 * Bishop (futo) babu
 */
public class Bishop extends Piece{
    public Bishop(Player owner, ChessTable table, Coord location, Color color){
        super(owner,table,location,color);
    }

    /**
     * Feluldefinialt move fuggveny, a sajat mozgasanak megfelelolen mozog a tablan
     * @param to az a koordinata ahova lepni szeretne a jatekos
     * @throws InvalidMoveException akkor lep fel, ha olyan helyre lep a jatekos amin sajat babu all, vagy ha a lepes utjaban
     * blokkolo babu van
     */
    public void move(Coord to) throws InvalidMoveException {
        int x = location.x;
        int y = location.y;
        if(to.x < location.x && to.y > location.y) {
            while (x != 0 || y != 7) {
                x--;
                y++;
                if (x == to.x && y == to.y && table.getPiece(new Coord(x, y)) == null) {
                    location.x = to.x;
                    location.y = to.y;
                    return;
                }
                if (x == to.x && y == to.y && table.getPiece(new Coord(x, y)) != null) {
                    if(table.getPiece(to).color!=this.color) {
                        table.deletePiece(to);
                        location.x = to.x;
                        location.y = to.y;
                        return;
                    } else throw new InvalidMoveException("Cannot attack friendly piece.");
                }
                if (!(x == to.x && y == to.y) && table.getPiece(new Coord(x, y)) != null) {
                    throw new InvalidMoveException("Blocking piece.");
                }
            }
        } else if(to.x > location.x && to.y > location.y) {
            while (x != 7 || y != 7) {
                x++;
                y++;
                if (x == to.x && y == to.y && table.getPiece(new Coord(x, y)) == null) {
                    location.x = to.x;
                    location.y = to.y;
                    return;
                }
                if (x == to.x && y == to.y && table.getPiece(new Coord(x, y)) != null) {
                    if(table.getPiece(to).color!=this.color) {
                        table.deletePiece(to);
                        location.x = to.x;
                        location.y = to.y;
                        return;
                    } else throw new InvalidMoveException("Cannot attack friendly piece.");
                }
                if (!(x == to.x && y == to.y) && table.getPiece(new Coord(x, y)) != null) {
                    throw new InvalidMoveException("Blocking piece.");
                }
            }
        } else if(to.x < location.x && to.y < location.y) {
            while (x != 0 || y != 0) {
                x--;
                y--;
                if (x == to.x && y == to.y && table.getPiece(new Coord(x, y)) == null) {
                    location.x = to.x;
                    location.y = to.y;
                    return;
                }
                if (x == to.x && y == to.y && table.getPiece(new Coord(x, y)) != null) {
                    if(table.getPiece(to).color!=this.color) {
                        table.deletePiece(to);
                        location.x = to.x;
                        location.y = to.y;
                        return;
                    } else throw new InvalidMoveException("Cannot attack friendly piece.");
                }
                if (!(x == to.x && y == to.y) && table.getPiece(new Coord(x, y)) != null) {
                    throw new InvalidMoveException("Blocking piece.");
                }
            }
        } else if (to.x > location.x && to.y < location.y) {
            while (x != 7 || y != 0) {
                x++;
                y--;
                if (x == to.x && y == to.y && table.getPiece(new Coord(x, y)) == null) {
                    location.x = to.x;
                    location.y = to.y;
                    return;
                }
                if (x == to.x && y == to.y && table.getPiece(new Coord(x, y)) != null) {
                    if(table.getPiece(to).color!=this.color) {
                        table.deletePiece(to);
                        location.x = to.x;
                        location.y = to.y;
                        return;
                    } else throw new InvalidMoveException("Cannot attack friendly piece.");
                }
                if (!(x == to.x && y == to.y) && table.getPiece(new Coord(x, y)) != null) {
                    throw new InvalidMoveException("Blocking piece.");
                }
            }
        }
    }

    /**
     * Feluldefinialt fuggveny,visszaadja a babu jelet es szinet
     * @return String-gel ter vissza melyben benne vannak adatai
     */
    @Override
    public String toString() {
        return "B " + color;
    }

    /**
     * Visszaadja a babu jelet
     * @return babu jelevel ter vissza Stringben
     */
    @Override
    public String nameToString() {
        return "B";
    }
}
