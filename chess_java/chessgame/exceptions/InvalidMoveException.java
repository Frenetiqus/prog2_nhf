package hu.ppke.itk.java2020.maldo.hf03.chessgame.exceptions;

public class InvalidMoveException extends Exception {
    public InvalidMoveException(String type){
        super(type);
    }
}
