package main.java.com.app;

import java.util.stream.IntStream;

public class Board {

    private int width;
    private int height;
    private Cell[][] grid;

    public static final int MAX_AREA = 85192;

    public Board(int width, int height) {
        if(width * height > Board.MAX_AREA){
            throw new IllegalArgumentException(String.format("Area is too big max is {0}", Board.MAX_AREA));
        }
        this.width = width;
        this.height = height;
        this.grid = IntStream.range(0, this.height)
                .mapToObj(i -> IntStream.range(0, this.width).mapToObj(j -> new Cell()).toArray(Cell[]::new))
                .toArray(Cell[][]::new);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void createMountain(int mountainI, int mountainJ) {
        this.grid[mountainI][mountainJ].setValue(new Mountain());
    }

    public CellValue getValueAt(int mountainI, int mountainJ) {
        Cell cell = this.grid[mountainI][mountainJ];
        return cell == null ? null : cell.getValue();
    }
}
