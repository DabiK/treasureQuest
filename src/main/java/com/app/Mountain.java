package main.java.com.app;

public record Mountain(int i, int j) implements CellValue {
    private static CellType type = CellType.MOUNTAIN;

    @Override
    public String toString() {
        return String.format("M - %d - %d\n", i, j);
    }

    @Override
    public int getOrder() {
        return type.getOrder();
    }
}
