package main.java.com.app;

import main.java.com.app.exception.BoardParserNegativeDimensionException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class BoardParserTest {

    @Test
    public void getBoardFromString_withNegativeDimension_shouldFail() {
        int width = -5;
        int height = -5;

        StringBuilder sb = new StringBuilder();
        sb.append(String.format("C - %d - %d\n", width, height));

        String content = sb.toString();
        BoardParserNegativeDimensionException exception = assertThrows(BoardParserNegativeDimensionException.class,() -> BoardParser.getBoardFromString(content));

        assertNotNull(exception);
    }

    @Test
    public void getBoardFromString_fromCorrectFileWithOnlyMountain_shouldSucceed() {
        int width = 5;
        int height = 5;
        Mountain[] mountainsInput = {new Mountain(0,0), new Mountain(1,1), new Mountain(1,2)};

        StringBuilder sb = new StringBuilder();
        sb.append(String.format("C - %d - %d\n", width, height))
                .append(createMountainInputString(mountainsInput));

        String content = sb.toString();
        Board board = BoardParser.getBoardFromString(content);

        CellValue[] mountains = {
                board.getValueAt(mountainsInput[0].i(),mountainsInput[0].j()),
                board.getValueAt(mountainsInput[1].i(),mountainsInput[1].j()),
                board.getValueAt(mountainsInput[2].i(),mountainsInput[2].j()),
        };

        for (int i = 0; i < mountains.length ; i++) {
            CellValue value = mountains[i];
            assertTrue(value instanceof Mountain);
            Mountain mountain = (Mountain) mountains[i];
            assertTrue(mountain.equals(mountainsInput[i]));
        }
    }


    @Test
    public void getBoardFromString_fromCorrectFileWithOnlyThreasure_shouldSucceed() {
        int width = 10;
        int height = 10;

        Treasure[] treasuresInput = {new Treasure(3,2,1),new Treasure(2,5,2)};

        StringBuilder sb = new StringBuilder();
        sb.append(String.format("C - %d - %d\n", width, height));
        sb.append(createTreasureInputString(treasuresInput));

        String content = sb.toString();
        Board board = BoardParser.getBoardFromString(content);

        CellValue[] treasures = {board.getValueAt(treasuresInput[0].i(), treasuresInput[0].j()), board.getValueAt(treasuresInput[1].i(), treasuresInput[1].j())};


        for (int i = 0; i < treasures.length ; i++) {
            CellValue value = treasures[i];
            assertTrue(value instanceof Treasure);
            Treasure treasure = (Treasure) treasures[i];
            assertTrue(treasure.equals(treasuresInput[i]));
        }
    }

    @Test
    public void getBoardFromString_fromCorrectFileWithAllKindOfItem_shouldSucceed() {
        int width = 10;
        int height = 10;

        Mountain[] mountainsInput = {new Mountain(0,0), new Mountain(1,1), new Mountain(2,2)};
        Treasure[] treasuresInput = {new Treasure(3,2,1),new Treasure(2,5,2)};

        StringBuilder sb = new StringBuilder();
        sb.append(String.format("C - %d - %d\n", width, height));

        sb.append(createMountainInputString(mountainsInput));
        sb.append(createTreasureInputString(treasuresInput));

        String content = sb.toString();
        Board board = BoardParser.getBoardFromString(content);

        CellValue[] treasures = {board.getValueAt(treasuresInput[0].i(), treasuresInput[0].j()), board.getValueAt(treasuresInput[1].i(), treasuresInput[1].j())};
        CellValue[] mountains = {
                board.getValueAt(mountainsInput[0].i(),mountainsInput[0].j()),
                board.getValueAt(mountainsInput[1].i(),mountainsInput[1].j()),
                board.getValueAt(mountainsInput[2].i(),mountainsInput[2].j()),
        };

        for (int i = 0; i < mountains.length ; i++) {
            CellValue value = mountains[i];
            assertTrue(value instanceof Mountain);
            Mountain mountain = (Mountain) mountains[i];
            assertTrue(mountain.equals(mountainsInput[i]));
        }

        for (int i = 0; i < treasures.length ; i++) {
            CellValue value = treasures[i];
            assertTrue(value instanceof Treasure);
            Treasure treasure = (Treasure) treasures[i];
            assertTrue(treasure.equals(treasuresInput[i]));
        }
    }


    @ParameterizedTest
    @CsvSource({
            "'C - 5 - 5\nX - 0 - 0\nM - 1 - 1\nM - 2 - 1\nT - 2 - 1 - 3\nT - 5 - 2 - 2', 'Invalid identifier: X'",
            "'W - 5 - 5\nM - 0 - 0\nM - 1 - 1\nM - 2 - 1\nT - 2 - 1 - 3\nT - 5 - 2 - 2', 'Invalid identifier: W'",
            "'C - 5 - 5\nM - 0 - 0\nM - 1 - 1\nM - 2 - 1\nT - 2 - 1 - 3\nZ - 5 - 2 - 2', 'Invalid identifier: Z'",
            "'C - 5 - 5\nM - 0\nM - 1 - 1\nM - 2 - 1\nT - 2 - 1 - 3\nT - 5 - 2 - 2', 'Invalid argument at line 2, expected length is 3'",
            "'C - 5 - 5\nM - 0 - 0\nM - 1 - 1\nM - 2 - 1\nT - 2 - 1\nT - 5 - 2 - 2', 'Invalid argument at line 5, expected length is 4'",
    })
    public void getBoardFromString_withInvalidRegexPattern_shouldFail(String input, String cause) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->  BoardParser.getBoardFromString(input));
        assertEquals(cause,exception.getMessage());
    }

    @Test
    public void getBoardFromString_withMultipleDimensionLine_shouldFail() {
        fail();
    }



    @Test
    public void getBoardFromFile_withFileNotFound_shouldRaiseAnException() {
        fail();
    }

    @Test
    public void getBoardFromFile_withEmptyFile_shouldReturnEmptyBoardWithCorrectDimension() {
        fail();
    }


    private String createMountainInputString(Mountain[] mountains) {
        return Arrays.stream(mountains)
                .map(m -> String.format("M - %d - %d\n", m.j(), m.i()))
                .reduce("", String::concat);
    }

    private String createTreasureInputString(Treasure[] treasures) {
        return Arrays.stream(treasures)
                .map(t -> String.format("T - %d - %d - %d\n", t.j(), t.i(), t.amount()))
                .reduce("", String::concat);
    }







}