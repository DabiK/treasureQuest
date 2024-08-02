package main.java.com.app;

public class Cell implements Comparable<Cell>{
    private CellValue value;

    public void setValue(CellValue value) {
        this.value = value;
    }

    public CellValue getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value == null ? "" : value.toString();
    }

    public int getOrder(){
        return value == null ? CellType.EMPTY.getOrder() : value.getOrder();
    }

    @Override
    public int compareTo(Cell other) {
        return Integer.compare(this.getOrder(), other.getOrder());
    }
}
