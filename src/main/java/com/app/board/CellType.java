package main.java.com.app.board;

public enum CellType {

    EMPTY(0, "empty", 'X'),
    MOUNTAIN(1, "mountain", 'M'),
    TREASURE(2, "treasure",'T');

    private final int order;
    private final String type;
    private final char key;

    CellType(int order, String type, char key) {
        this.order = order;
        this.type = type;
        this.key = key;
    }

    public int getOrder() {
        return order;
    }

    public String getType() {
        return type;
    }

    public char getKey() {
        return key;
    }
}