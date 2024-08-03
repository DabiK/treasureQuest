package main.java.com.app.adventurer;

public enum Orientation {
    N("N"),
    S("S"),
    W("W"),
    E("E");


    private String key;

    Orientation(String key) {
        this.key = key;
    }
}
