package main.java.com.app;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    @Test
    public void creation_withCorrectData_shouldSucceed() {
        Board board = new Board();
        fail();
    }

    @Test
    public void creation_withSizeGreatherThan85182_shouldFail() {
        Board board = new Board();
        fail();
    }

    @Test
    public void createMountains_withCorrectCoords_shouldSucceed() {
        Board board = new Board();
        fail();
    }

    @Test
    public void createMountains_withMountainWithInvalidCords_shouldFail() {
        Board board = new Board();
        fail();
    }

    @Test
    public void createTreasures_withTreasuresWithCorrectCoordsAndCorrectAmount_shouldSucceed() {
        Board board = new Board();
        fail();
    }

    @Test
    public void createTreasures_withCorrectCoordsAndCorrectAmountSameSpot_shouldSucceed() {
        Board board = new Board();
        fail();
    }

    @Test
    public void createTreasures_withInvalidCoords_shouldFail() {
        Board board = new Board();
        fail();
    }

    @Test
    public void createTreasures_withWithNegativeAmount_shouldFail() {
        Board board = new Board();
        fail();
    }

    @Test
    public void toString_withCorrectMountainsAndTreasures_shouldSucceed() {
        Board board = new Board();
        fail();
    }

}