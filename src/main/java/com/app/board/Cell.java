package main.java.com.app.board;

public class Cell implements Comparable<Cell>{
    private CellValue value;

    public void setValue(CellValue value) {
        this.value = value;
    }

    public CellValue getValue() {
        return value;
    }


    public int getOrder(){
        return value == null ? CellType.EMPTY.getOrder() : value.getOrder();
    }

    public boolean isStepable(){
        return value == null ? true : value.isStepable();
    }

    @Override
    public int compareTo(Cell other) {
        return Integer.compare(this.getOrder(), other.getOrder());
    }


    @Override
    public String toString() {
        return value == null ? "" : value.toString();
    }

}
