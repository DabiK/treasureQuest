package main.java.com.app.adventurer;

public enum Orientation {
    N("N"),
    S("S"),
    W("O"),
    E("E");


    private String key;

    Orientation(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
