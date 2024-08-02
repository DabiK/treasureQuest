package main.java.com.app;

public enum CellType {

    EMPTY(0, "empty", ""),
    MOUNTAIN(1, "mountain", "M"),
    TREASURE(2, "treasure","T");

    private final int order;
    private final String type;
    private final String key;

    CellType(int order, String type, String key) {
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
}