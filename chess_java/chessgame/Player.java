package hu.ppke.itk.java2020.maldo.hf03.chessgame;

import hu.ppke.itk.java2020.maldo.hf03.chessgame.exceptions.InvalidCoordinateException;
import hu.ppke.itk.java2020.maldo.hf03.chessgame.exceptions.InvalidMoveException;
import hu.ppke.itk.java2020.maldo.hf03.chessgame.misc.Coord;
import hu.ppke.itk.java2020.maldo.hf03.chessgame.pieces.Piece;

public final class Player {
    static Integer instanceCounter;
    int id = nextID();
    String name;

    /**
     * Minden playert egy egyedi IDvel azonosit
     * @return
     */
    private static int nextID(){
        if(instanceCounter == null) {
            instanceCounter = 1;
        } else{
            instanceCounter++;
        }
        return instanceCounter;
    }

    /**
     * Ha nem kap meg nevet az IDbol csinal egyet
     */
    public Player(){
        this.name = "Anonymous" + id;
    }

    public Player(String name){
        this.name = name;
    }

    /**
     * Player altal lehet a tablan mozgasokat vegezni, egy player akar tobb tablan is jatszhat
     * @param table az a tabla amelyiken a player jatszani akar
     * @param from a koordinata ahonnan lepni akar
     * @param to az a koordinata ahova lepni akar
     * @throws InvalidCoordinateException ez akkor lep fel ha a koordinata kilog a tablarol
     * @throws InvalidMoveException ez itt akkor lep fel ha a koordinatan nem all babu vagy ha all akkor ha ez a babu nem a jatekose
     */
    void move(ChessTable table, Coord from, Coord to) throws InvalidCoordinateException, InvalidMoveException {
        if(from.x<0 || from.x>7 || from.y<0 || from.y>7 || to.x<0 || to.x>7 || to.y<0 || to.y>7) throw new InvalidCoordinateException();

        Piece curr = table.getPiece(from);
        if(curr == null) throw new InvalidMoveException("A piece must be targeted.");
        if(curr.getOwner()!=this) throw new InvalidMoveException("Must only move owned piece.");

        curr.move(to);
        table.updatePiece(from,to);
    }
    public String getName(){
        return this.name;
    }
}
