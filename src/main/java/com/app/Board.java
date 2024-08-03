package main.java.com.app;

import main.java.com.app.adventurer.Adventurer;
import main.java.com.app.exception.TreasureNotCollectible;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Board {

    private int width;
    private int height;
    private Cell[][] grid;

    public static final int MAX_AREA = 85192;

    public static final String NEGATIVE_AMOUNT_ERROR = "Amount should not be negative";

    public Board(int width, int height) {
        if(width * height > Board.MAX_AREA){
            throw new IllegalArgumentException(String.format("Area is too big max is {0}", Board.MAX_AREA));
        }
        if(width < 0 || height < 0){
            throw new IllegalArgumentException("Width and height should be positive values");
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
        this.grid[mountainI][mountainJ].setValue(new Mountain(mountainI, mountainJ));
    }

    public void createTreasures(int amount, int treasureI, int treasureJ) {
        this.checkCoords(treasureI, treasureJ);
        if(amount < 0) {
            throw new IllegalArgumentException(Board.NEGATIVE_AMOUNT_ERROR);
        }
        this.grid[treasureI][treasureJ].setValue(new Treasure(amount, treasureI, treasureJ));
    }

    public Board withMountain(int mountainI, int mountainJ){
        this.createMountain(mountainI, mountainJ);
        return this;
    }

    public Board withTreasure(int amount, int treasureI, int treasureJ){
        this.createTreasures(amount, treasureI, treasureJ);
        return this;
    }




    public void collectTreasure(int i, int j) throws TreasureNotCollectible {
        if(!this.isTreasureAt(i,j)){
            throw new TreasureNotCollectible();
        }
        Treasure treasure = (Treasure) this.getValueAt(i,j);
        grid[i][j].setValue(new Treasure(Math.max(0, treasure.amount() - 1)  ,i,j));
    }


    public CellValue getValueAt(int i, int j) {
        Cell cell = this.isValidCoords(i, j) ? this.grid[i][j] : null;
        return cell == null ? null : cell.getValue();
    }

    public boolean isValidCoords(int i, int j){
        return i >= 0 && i < this.height && j >=0 && j < this.width;
    }

    public boolean isTreasureAt(int i, int j) {
        return isValidCoords(i,j) && this.grid[i][j].getValue() instanceof Treasure && ((Treasure) this.grid[i][j].getValue()).amount() > 0;
    }

    public boolean isStepable(int i, int j) {
        if(!this.isValidCoords(i,j)){
            return false;
        }
        return this.grid[i][j].isStepable();
    }

    public boolean isEmpty(int i , int j ){
        return this.grid[i][j].getValue() == null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        List<Cell> cellList = new ArrayList<>();
        sb.append(String.format("C - %d - %d\n", width, height));

        // flaten the 2D matrix
        for (Cell[] row : grid) {
            cellList.addAll(Arrays.asList(row));
        }

        // Sort the cell list based on their order values
        cellList.sort(null);

        for (Cell cell : cellList) {
            String stringValue = cell.toString();
            sb.append(stringValue.isEmpty() ? "" : String.format("%s\n", stringValue));
        }

        return sb.toString();
    }

    private void checkCoords(int i, int j){
        if( !this.isValidCoords(i,j)){
            throw new IndexOutOfBoundsException();
        }
    }
}
