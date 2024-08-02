package main.java.com.app;

public enum CellType {

    EMPTY(0, "empty"),
    MOUNTAIN(1, "mountain"),
    TREASURE(2, "treasure");

    private final int order;
    private final String type;

    CellType(int order, String type) {
        this.order = order;
        this.type = type;
    }

    public int getOrder() {
        return order;
    }

    public String getType() {
        return type;
    }
}