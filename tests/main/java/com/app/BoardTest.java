package main.java.com.app;

import main.java.com.app.adventurer.Adventurer;
import main.java.com.app.adventurer.AdventurerSequence;
import main.java.com.app.adventurer.Orientation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;


class BoardTest {

    @Test
    public void creation_withCorrectData_shouldSucceed() {
        int width = 5;
        int height = 5;
        Board board = new Board(width, height);
        assertEquals(board.getHeight(), height);
        assertEquals(board.getWidth(), width);
    }

    @Test
    public void creation_withNegativeDimension_shouldFail() {
        int width = -5;
        int height = -5;
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Board(width, height));
        assertEquals("Width and height should be positive values", exception.getMessage());
    }

    @Test
    public void creation_withSizeGreatherThan85182_shouldFail() {
        int width = 25000;
        int height = 25000;
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> { new Board(width, height); });
        assertEquals(String.format("Area is too big max is {0}", Board.MAX_AREA), exception.getMessage());
    }

    @Test
    public void createMountains_withCorrectCoords_shouldSucceed() {
        int width = 5;
        int height = 5;
        int mountainI = 2;
        int mountainJ = 2;

        Board board = new Board(width, height);
        board.createMountain(mountainI, mountainJ);
        CellValue cellValue = board.getValueAt(mountainI, mountainJ);

        assertNotNull(cellValue);
        assertInstanceOf(Mountain.class,cellValue);
    }

    @ParameterizedTest
    @CsvSource({
            "6, 2",
            "2, 6",
            "6, 6"
    })
    public void createMountains_withMountainWithInvalidCords_shouldFail(int mountainI, int mountainJ) {
        int width = 5;
        int height = 5;

        Board board = new Board(width, height);
        IndexOutOfBoundsException exception = assertThrows(IndexOutOfBoundsException.class, () -> { board.createMountain(mountainI, mountainJ); });
        CellValue cellValue = board.getValueAt(mountainI, mountainJ);

        assertNotNull(exception);
        assertNull(cellValue);
    }

    @Test
    public void createTreasures_withTreasuresWithCorrectCoordsAndCorrectAmount_shouldSucceed() {
        int width = 5;
        int height = 5;
        int amount = 1;
        int treasureI = 2;
        int treasureJ = 2;

        Board board = new Board(width, height);
        board.createTreasures(amount, treasureI, treasureJ);
        CellValue cellValue = board.getValueAt(treasureI, treasureJ);

        assertNotNull(cellValue);
        assertInstanceOf(Treasure.class,cellValue);

        Treasure treasure = (Treasure) cellValue;
        assertEquals(treasure.amount(), amount);

    }

    @Test
    public void createTreasures_withCorrectCoordsAndCorrectAmountSameSpot_shouldSucceed() {
        int width = 5;
        int height = 5;
        int amount = 1;
        int treasureI = 2;
        int treasureJ = 2;

        Board board = new Board(width, height);
        board.createTreasures(amount, treasureI, treasureJ);
        board.createTreasures(amount, treasureI, treasureJ);
        CellValue cellValue = board.getValueAt(treasureI, treasureJ);

        assertNotNull(cellValue);
        assertInstanceOf(Treasure.class,cellValue);

        Treasure treasure = (Treasure) cellValue;
        assertEquals(treasure.amount(), amount);
    }

    @ParameterizedTest
    @CsvSource({
            "6, 2",
            "2, 6",
            "6, 6"
    })
    public void createTreasures_withInvalidCoords_shouldFail(int treasureI, int treasureJ) {
        int width = 5;
        int height = 5;
        int amount = 1;

        Board board = new Board(width, height);
        IndexOutOfBoundsException exception = assertThrows(IndexOutOfBoundsException.class, () -> { board.createTreasures(amount, treasureI, treasureJ); });
        CellValue cellValue = board.getValueAt(treasureI, treasureJ);

        assertNotNull(exception);
        assertNull(cellValue);
    }

    @Test
    public void createTreasures_withWithNegativeAmount_shouldFail() {
        int width = 5;
        int height = 5;
        int amount = -1;
        int treasureI = 2;
        int treasureJ = 2;

        Board board = new Board(width, height);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> { board.createTreasures(amount, treasureI, treasureJ); });
        CellValue cellValue = board.getValueAt(treasureI, treasureJ);

        assertNotNull(exception);
        assertEquals(Board.NEGATIVE_AMOUNT_ERROR, exception.getMessage());
        assertNull(cellValue);
    }


    @Test
    public void createAdventurer_withCorrectValues_shouldSucceed(){
        int width = 5;
        int height = 5;
        int j = 0;
        int i = 0;

        String name = "Luffy";
        int adventurerI = 0;
        int adventurerJ = 0;
        Orientation orientation = Orientation.E;
        AdventurerSequence[] sequence = { AdventurerSequence.A, AdventurerSequence.A, AdventurerSequence.A};


        Board board = new Board(width, height);
        board.createMountain(1,1);
        board.createMountain(2,1);
        board.createAdventurer(i,j, new Adventurer(name , adventurerI,adventurerJ, orientation, sequence));

        CellValue cellValue = board.getValueAt(i, j);
        assertInstanceOf(Adventurer.class,cellValue);

        Adventurer adventurer = (Adventurer) cellValue;
        assertNotNull(adventurer);
        assertEquals(name, adventurer.getName());
        assertEquals(i, adventurer.getI());
        assertEquals(j, adventurer.getJ());
        assertEquals(orientation, adventurer.getOrientation());
        assertArrayEquals(sequence, adventurer.getSequence());
    }

    @Test
    public void createAdventurer_withOutOfBoundCoords_shouldFail(){
        int width = 5;
        int height = 5;
        int j = 0;
        int i = 0;

        String name = "Luffy";
        int adventurerI = 6;
        int adventurerJ = 6;
        Orientation orientation = Orientation.E;
        AdventurerSequence[] sequence = { AdventurerSequence.A, AdventurerSequence.A, AdventurerSequence.A};


        Board board = new Board(width, height);
        board.createMountain(1,1);
        board.createMountain(2,1);
        IndexOutOfBoundsException exception = assertThrows(IndexOutOfBoundsException.class , () -> board.createAdventurer(adventurerI,adventurerJ, new Adventurer(name , adventurerI,adventurerJ, orientation, sequence)));
        assertNotNull(exception);
    }

    @Test
    public void createAdventurer_withNotEmptyCoords_shouldFail(){
        int width = 5;
        int height = 5;
        int j = 0;
        int i = 0;

        String name = "Luffy";
        int adventurerI = 1;
        int adventurerJ = 1;
        Orientation orientation = Orientation.E;
        AdventurerSequence[] sequence = { AdventurerSequence.A, AdventurerSequence.A, AdventurerSequence.A};


        Board board = new Board(width, height);
        board.createMountain(1,1);
        board.createMountain(2,1);
        board.createMountain(adventurerI,adventurerJ);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class , () -> board.createAdventurer(adventurerI,adventurerJ, new Adventurer(name , adventurerI,adventurerJ, orientation, sequence)));
        assertNotNull(exception);
        assertEquals("Coords 1 1 is not empty", exception.getMessage());
    }

    @Test
    public void createAdventurer_withNotInSyncCoords_shouldFail(){
        int width = 5;
        int height = 5;
        int j = 0;
        int i = 0;

        String name = "Luffy";
        int adventurerI = 1;
        int adventurerJ = 1;
        Orientation orientation = Orientation.E;
        AdventurerSequence[] sequence = { AdventurerSequence.A, AdventurerSequence.A, AdventurerSequence.A};


        Board board = new Board(width, height);
        board.createMountain(1,1);
        board.createMountain(2,1);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class , () -> board.createAdventurer(adventurerI,adventurerJ, new Adventurer(name , 0,0, orientation, sequence)));
        assertNotNull(exception);
        assertEquals("Invalid adventurer position", exception.getMessage());
    }

    @Test
    public void toString_withCorrectMountainsAndTreasures_shouldSucceed() {
        int width = 5;
        int height = 5;

        Board board = new Board(width, height);
        board.createMountain(0,0);
        board.createMountain(1,1);
        board.createMountain(2,1);
        board.createMountain(0,0);
        board.createTreasures(2, 1, 3);
        board.createTreasures(5, 2, 2);

        StringBuilder sb = new StringBuilder();

        sb.append(String.format("C - %d - %d\n", width, height))
                .append(String.format("M - %d - %d\n", 0, 0))
                .append(String.format("M - %d - %d\n", 1, 1))
                .append(String.format("M - %d - %d\n", 2, 1))
                .append(String.format("T - %d - %d - %d\n", 2, 1, 3))
                .append(String.format("T - %d - %d - %d\n", 5, 2, 2));

        String out = board.toString();
        assertEquals(sb.toString(), out);
    }
    
}