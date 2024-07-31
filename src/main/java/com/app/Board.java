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
        this.checkCoords(mountainI, mountainJ);
        this.grid[mountainI][mountainJ].setValue(new Mountain());
    }


    public void createTreasures(int amount, int treasureI, int treasureJ) {
        this.grid[treasureI][treasureJ].setValue(new Treasure(amount));
    }

    public CellValue getValueAt(int i, int j) {
        Cell cell = this.isValidCoords(i, j) ? this.grid[i][j] : null;
        return cell == null ? null : cell.getValue();
    }

    public boolean isValidCoords(int i, int j){
        return i >= 0 && i < this.height && j >=0 && j < this.width;
    }

    private void checkCoords(int i, int j){
        if( !this.isValidCoords(i,j)){
            throw new IndexOutOfBoundsException();
        }
    }
}
