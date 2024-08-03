package main.java.com.app.exception;

public class InvalidArgumentLength extends IllegalArgumentException {

    public InvalidArgumentLength(int expectedLength, int line) {
        super("Invalid argument at line " + line + ", expected length is " + expectedLength);
    }
}
