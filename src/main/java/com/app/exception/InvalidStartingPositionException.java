package main.java.com.app.exception;

public class InvalidStartingPositionException extends IllegalArgumentException {
    public InvalidStartingPositionException(String s) {
        super(s);
    }
}
