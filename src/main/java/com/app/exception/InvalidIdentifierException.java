package main.java.com.app.exception;

public class InvalidIdentifierException extends IllegalArgumentException {
    public InvalidIdentifierException(char itemType) {
        super("Invalid identifier: "+ itemType);
    }
}
