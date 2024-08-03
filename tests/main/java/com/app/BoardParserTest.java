package main.java.com.app;

import main.java.com.app.exception.BoardParserNegativeDimensionException;
import org.junit.jupiter.api.Test;

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
                .append(Arrays.stream(mountainsInput).map(
                        (mountainInput) ->
                                String.format("M - %d - %d\n", mountainInput.j(), mountainInput.i())
                ).reduce("", String::concat)
        );

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

        sb.append(Arrays.stream(treasuresInput).map(
                        (treasureInput) ->
                                String.format("T - %d - %d - %d\n", treasureInput.j(), treasureInput.i(), treasureInput.amount()
                                )
                ).reduce("", String::concat)
        );

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

        sb.append(Arrays.stream(mountainsInput).map(
                        (mountainInput) ->
                                String.format("M - %d - %d\n", mountainInput.j(), mountainInput.i())
                ).reduce("", String::concat)
        );
        sb.append(Arrays.stream(treasuresInput).map(
                        (treasureInput) ->
                                String.format("T - %d - %d - %d\n", treasureInput.j(), treasureInput.i(), treasureInput.amount()
                                )
                ).reduce("", String::concat)
        );

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


    @Test
    public void getBoardFromString_withInvalidRegexPattern_shouldFail() {
        fail();
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







}