package hu.ppke.itk.java2020.maldo.hf03.chessgame.exceptions;

public class InvalidCoordinateException extends Exception{
    public InvalidCoordinateException(){
        super("Invalid coordinate was given.");
    }
}
