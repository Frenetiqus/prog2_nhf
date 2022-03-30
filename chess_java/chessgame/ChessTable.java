package hu.ppke.itk.java2020.maldo.hf03.chessgame;

import hu.ppke.itk.java2020.maldo.hf03.chessgame.exceptions.InvalidCoordinateException;
import hu.ppke.itk.java2020.maldo.hf03.chessgame.exceptions.InvalidMoveException;
import hu.ppke.itk.java2020.maldo.hf03.chessgame.misc.Color;
import hu.ppke.itk.java2020.maldo.hf03.chessgame.misc.Coord;
import hu.ppke.itk.java2020.maldo.hf03.chessgame.pieces.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class ChessTable {
    private Piece[][] table;
    {
        table = new Piece[8][8];
    }

    /**
     * A tablarol torli az elemeket
     */
    private void clearTable(){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                table[i][j] = null;
            }
        }
    }

    /**
     * Elindit egy jatekot ezen a tablan adott jatekosnak
     * @param plr az a jatekos akinek a babui a palyan fognak allni
     * @throws IOException
     * @throws InvalidCoordinateException
     * @throws InvalidMoveException
     */
    public void runGame(Player plr) throws IOException, InvalidCoordinateException, InvalidMoveException {
        BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
        String regex = new String("[(]?[-]?[0-9]+[,]?[-]?[0-9]+[)]?[-]?[(]?[-]?[0-9]+[,]?[-]?[0-9]+[)]?");
        Pattern p = Pattern.compile(regex);
        String command;

        this.setNewTable(plr);
        this.print();

        do {
            command = bfr.readLine();
            Matcher m = p.matcher(command);
            if(m.matches()){
                int from_x = Integer.parseInt(command.substring(1,command.indexOf(',')));
                int from_y = Integer.parseInt(command.substring(command.indexOf(',') + 1, command.indexOf(')')));
                int to_x = Integer.parseInt(
                        command.substring(
                                command.indexOf('(', command.indexOf(')')) + 1,
                                command.indexOf(',',  command.indexOf('(', command.indexOf(')')))
                        )
                );
                int to_y = Integer.parseInt(
                        command.substring(
                                command.indexOf(',', command.indexOf(')')) + 1,
                                command.indexOf(')',  command.indexOf('(', command.indexOf(')')))
                        )
                );
                Coord from = new Coord(from_x, from_y);
                Coord to = new Coord(to_x, to_y);
                plr.move(this, from, to);
                this.print();

            } else if(command.equals("exit")) break;
            else System.out.println("Type a valid command.");

        } while (true);
        this.clearTable();
    }

    /**
     * Elindit egy jatekot ezen a tablan 2 jatekosnak
     * @param plr1 az egyik jatekos akinek a babui WHITE (feherek) lesznek
     * @param plr2 a masik jatekos akinek a babui BLACK (fekete) lesznek
     * @throws IOException
     * @throws InvalidCoordinateException
     * @throws InvalidMoveException
     */
    public void runGame(Player plr1, Player plr2) throws IOException, InvalidCoordinateException, InvalidMoveException {
        BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
        String regex = new String("[(]?[-]?[0-9]+[,]?[-]?[0-9]+[)]?[-]?[(]?[-]?[0-9]+[,]?[-]?[0-9]+[)]?");
        Pattern p = Pattern.compile(regex);
        String command;

        this.setNewTable(plr1, plr2);
        this.print();

        int switcher = 1;
        do {
            command = bfr.readLine();
            Matcher m = p.matcher(command);

            if (m.matches()) {
                int from_x = Integer.parseInt(command.substring(1, command.indexOf(',')));
                int from_y = Integer.parseInt(command.substring(command.indexOf(',') + 1, command.indexOf(')')));
                int to_x = Integer.parseInt(
                        command.substring(
                                command.indexOf('(', command.indexOf(')')) + 1,
                                command.indexOf(',', command.indexOf('(', command.indexOf(')')))
                        )
                );
                int to_y = Integer.parseInt(
                        command.substring(
                                command.indexOf(',', command.indexOf(')')) + 1,
                                command.indexOf(')', command.indexOf('(', command.indexOf(')')))
                        )
                );
                Coord from = new Coord(from_x, from_y);
                Coord to = new Coord(to_x, to_y);
                if (switcher == 1) {
                    plr1.move(this, from, to);
                    switcher *= -1;
                } else {
                    plr2.move(this, from, to);
                    switcher *= -1;
                }
                this.print();

            } else if (command.equals("exit")) break;
            else System.out.println("Type a valid command.");

        } while (true);
        this.clearTable();
    }

    /**
     * Felallit egy tablat 1 jatekosnak, minden babu egy jatekose viszont a babuk kulon feketek es feherek,
     * feher babuk a tabla aljan, feketek a tetejen helyezkednek el
     * @param player az adott jatekos
     */
    private void setNewTable(Player player){
        for (int i = 0; i < 8; i++) {
            table[i][1] = new Pawn(player,this, new Coord(i, 1), Color.values()[0], new Coord(i, 1));
            table[i][6] = new Pawn(player,this, new Coord(i, 6), Color.values()[1], new Coord(i, 6));
        }

        table[0][0] = new Rook(player,this, new Coord(0,0), Color.values()[0]);
        table[1][0] = new Knight(player,this, new Coord(1,0), Color.values()[0]);
        table[2][0] = new Bishop(player,this, new Coord(2,0), Color.values()[0]);
        table[3][0] = new Queen(player,this, new Coord(3,0), Color.values()[0]);
        table[4][0] = new King(player,this, new Coord(4,0), Color.values()[0]);
        table[5][0] = new Bishop(player,this, new Coord(5,0), Color.values()[0]);
        table[6][0] = new Knight(player,this, new Coord(6,0), Color.values()[0]);
        table[7][0] = new Rook(player,this, new Coord(7,0), Color.values()[0]);

        table[0][7] = new Rook(player,this, new Coord(0,7), Color.values()[1]);
        table[1][7] = new Knight(player,this, new Coord(1,7), Color.values()[1]);
        table[2][7] = new Bishop(player,this, new Coord(2,7), Color.values()[1]);
        table[3][7] = new Queen(player,this, new Coord(3,7), Color.values()[1]);
        table[4][7] = new King(player,this, new Coord(4,7), Color.values()[1]);
        table[5][7] = new Bishop(player,this, new Coord(5,7), Color.values()[1]);
        table[6][7] = new Knight(player,this, new Coord(6,7), Color.values()[1]);
        table[7][7] = new Rook(player,this, new Coord(7,7), Color.values()[1]);
    }

    /**
     * Felallit egy tablat 2 jatekosnak, egy feher keszlet babu az egyiknek illetve egy fekete masik jatekosnak,
     * feher babuk a tabla aljan, feketek a tetejen helyezkednek el
     * @param playerA egyik jatekos WHITE
     * @param playerB masik jatekos BLACK
     */
    private void setNewTable(Player playerA, Player playerB){
        for (int i = 0; i < 8; i++) {
            table[i][1] = new Pawn(playerA,this, new Coord(i, 1), Color.values()[0], new Coord(i, 1));
            table[i][6] = new Pawn(playerB,this, new Coord(i, 6), Color.values()[1], new Coord(i, 6));
        }

        table[0][0] = new Rook(playerA,this, new Coord(0,0), Color.values()[0]);
        table[1][0] = new Knight(playerA,this, new Coord(1,0), Color.values()[0]);
        table[2][0] = new Bishop(playerA,this, new Coord(2,0), Color.values()[0]);
        table[3][0] = new Queen(playerA,this, new Coord(3,0), Color.values()[0]);
        table[4][0] = new King(playerA,this, new Coord(4,0), Color.values()[0]);
        table[5][0] = new Bishop(playerA,this, new Coord(5,0), Color.values()[0]);
        table[6][0] = new Knight(playerA,this, new Coord(6,0), Color.values()[0]);
        table[7][0] = new Rook(playerA,this, new Coord(7,0), Color.values()[0]);

        table[0][7] = new Rook(playerB,this, new Coord(0,7), Color.values()[1]);
        table[1][7] = new Knight(playerB,this, new Coord(1,7), Color.values()[1]);
        table[2][7] = new Bishop(playerB,this, new Coord(2,7), Color.values()[1]);
        table[3][7] = new Queen(playerB,this, new Coord(3,7), Color.values()[1]);
        table[4][7] = new King(playerB,this, new Coord(4,7), Color.values()[1]);
        table[5][7] = new Bishop(playerB,this, new Coord(5,7), Color.values()[1]);
        table[6][7] = new Knight(playerB,this, new Coord(6,7), Color.values()[1]);
        table[7][7] = new Rook(playerB,this, new Coord(7,7), Color.values()[1]);
    }

    /**
     * visszaadja az adott koordinatan levo babut
     * @param coord
     * @return egy babuval ter vissza az adott kordinatan
     */
    public Piece getPiece(Coord coord) {
        return table[coord.x][coord.y];
    }

    /**
     * Az old koordinatan levo babut a next koordinatara updateli a tablan
     * @param old az updatelendo babu koordinataja, ha ez null akkor visszater, nincs mit updatelni
     * @param next a koordinata amelyre a metodus atrakja a babut
     * @throws InvalidMoveException csak olyan helyre lehet updatelni ahol meg nincs babu
     */
    void updatePiece(Coord old, Coord next) throws InvalidMoveException {
        if(table[next.x][next.y]!=null) throw new InvalidMoveException("Field to step on is not blank.");
        if(table[old.x][old.y]==null) return;

        table[next.x][next.y] = table[old.x][old.y];
        table[old.x][old.y] = null;
    }

    /**
     * Torli a tablaban az adott koordinatan talalhato babut (piece)
     * @param loc a koordinata ahonnan torli a babut
     */
    public void deletePiece(Coord loc){
        if(table[loc.x][loc.y] instanceof King){
            if(table[loc.x][loc.y].getColor() == Color.WHITE){
                System.out.println("White king has been defeated. Black wins the game.");
            } else{
                System.out.println("Black king has been defeated. White wins the game.");
            }
        }
        table[loc.x][loc.y] = null;
    }

    /**
     * kiirja  a tablat consolera
     */
    public void print(){
        for (int i = 7; i >= 0 ; i--) {
            System.out.print(String.valueOf(i) + '|');
            for (int j = 0; j < 8; j++) {
                if(table[j][i] != null) {
                    System.out.print(table[j][i].nameToString() + ' ');
                } else System.out.print("X" + ' ');
            }
            System.out.println();
        }
        System.out.print(" |");
        for (int i = 0; i <= 7; i++) {
            System.out.print(String.valueOf(i) + ' ');
        }
        System.out.println();
        System.out.println();
    }

}
