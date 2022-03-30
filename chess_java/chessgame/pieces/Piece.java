package hu.ppke.itk.java2020.maldo.hf03.chessgame.pieces;

import hu.ppke.itk.java2020.maldo.hf03.chessgame.exceptions.InvalidMoveException;
import hu.ppke.itk.java2020.maldo.hf03.chessgame.ChessTable;
import hu.ppke.itk.java2020.maldo.hf03.chessgame.Player;
import hu.ppke.itk.java2020.maldo.hf03.chessgame.misc.Color;
import hu.ppke.itk.java2020.maldo.hf03.chessgame.misc.Coord;

/**
 * Abstract Piece (babu) osztaly, minden babunak van szine, egy koordinataja egy adott tablan, es babukhoz tartozo
 * jatekos is
 */
public abstract class Piece {
    protected Coord location;
    private Player owner;
    protected ChessTable table;
    protected Color color;

    /**
     * Minden babu letrehozasanal szukseg van egy jatekosra aki birtokolja a babut, egy tablara amin a babuval jatek fog folyni,
     * a tablan megadott kezdo poziciora, illetve a babu szinere
     * @param owner a birtoklo jatekos
     * @param table tabla melyen jatek zajlik
     * @param location az adott tablan kezdo pozicio
     * @param color a babu szine
     */
    public Piece(Player owner, ChessTable table, Coord location, Color color){
        this.owner = owner;
        this.table = table;
        this.location = location;
        this.color = color;
    }

    public abstract void move(Coord to) throws InvalidMoveException;

    /**
     * Getter fuggveny
     * @return visszater a birtoklo jatekossal
     */
    public Player getOwner(){
        return owner;
    }

    /**
     * Getter fuggveny
     * @return visszater a babu szinevel
     */
    public Color getColor(){
        return this.color;
    }

    /**
     * Abstract fuggveny mely a specifikus babuknal visszaadja a jeluket
     * @return String-gel ter vissza melyben a jeluk van
     */
    public abstract String nameToString();

}
