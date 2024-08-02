package main.java.com.app;

public record Treasure(int amount, int i, int j) implements CellValue {
    private static CellType type = CellType.TREASURE;

    @Override
    public String toString() {
        return String.format("T - %d - %d - %d\n", amount, i, j);
    }

    @Override
    public int getOrder() {
        return type.getOrder();
    }
}
