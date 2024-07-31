package main.java.com.app;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
    public void creation_withSizeGreatherThan85182_shouldFail() {
        int width = 25000;
        int height = 25000;
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> { new Board(width, height); });
        assertEquals(String.format("Area is too big max is {0}", Board.MAX_AREA), exception.getMessage());
    }

    @Test
    public void createMountains_withCorrectCoords_shouldSucceed() {
        // Board board = new Board();
        fail();
    }

    @Test
    public void createMountains_withMountainWithInvalidCords_shouldFail() {
// Board board = new Board();
        fail();
    }

    @Test
    public void createTreasures_withTreasuresWithCorrectCoordsAndCorrectAmount_shouldSucceed() {
        // Board board = new Board();
        fail();
    }

    @Test
    public void createTreasures_withCorrectCoordsAndCorrectAmountSameSpot_shouldSucceed() {
        // Board board = new Board();
        fail();
    }

    @Test
    public void createTreasures_withInvalidCoords_shouldFail() {
        // Board board = new Board();
        fail();
    }

    @Test
    public void createTreasures_withWithNegativeAmount_shouldFail() {
        // Board board = new Board();
        fail();
    }

    @Test
    public void toString_withCorrectMountainsAndTreasures_shouldSucceed() {
        // Board board = new Board();
        fail();
    }

}