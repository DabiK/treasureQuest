package main.java.com.app.exception;

public class BoardParserNegativeDimensionException extends IllegalArgumentException{

    public BoardParserNegativeDimensionException(){
        super("You cannot use negative dimension");
    }
}
