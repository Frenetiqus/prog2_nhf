package hu.ppke.itk.java2020.maldo.hf03;

import hu.ppke.itk.java2020.maldo.hf03.chessgame.ChessTable;
import hu.ppke.itk.java2020.maldo.hf03.chessgame.exceptions.InvalidCoordinateException;
import hu.ppke.itk.java2020.maldo.hf03.chessgame.exceptions.InvalidMoveException;
import hu.ppke.itk.java2020.maldo.hf03.chessgame.Player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {

        try {
            BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Welcome: 'one' or 'two' players?");

            String command;

            if(bfr.readLine().equals("one")) {
                System.out.println("Give player name (optional).");
                command = bfr.readLine();

                Player plr;
                if(command.equals("")) plr = new Player();
                else plr = new Player(command);
                System.out.println("Your name is: " + plr.getName() + '.');
                System.out.println("If you want to exit game type 'exit'. Or type a move" + '\n');

                ChessTable table = new ChessTable();

                table.runGame(plr);

            }
            else{
                System.out.println("Give the first player's name (optional).");
                command = bfr.readLine();
                Player plr1;
                if(command.equals("")) plr1 = new Player();
                else plr1 = new Player(command);

                System.out.println("Give the second player's name (optional).");
                command = bfr.readLine();
                Player plr2;
                if(command.equals("")) plr2 = new Player();
                else plr2 = new Player(command);

                System.out.println("First player's name is: " + plr1.getName() + ", second player's name is: " + plr2.getName());
                System.out.println("If you want to exit game type 'exit'. Or type a move." + '\n');

                ChessTable table = new ChessTable();
                System.out.println("WHITE (bottom) player is: " + plr1.getName() + ", BLACK (top) player is: " + plr2.getName());
                System.out.println("The WHITE player starts first. Type command!" + '\n');

                table.runGame(plr1, plr2);
            }

        } catch (IOException | InvalidCoordinateException | InvalidMoveException e) {
            e.printStackTrace();
        }

    }
}
