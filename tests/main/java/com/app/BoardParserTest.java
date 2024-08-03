package main.java.com.app;

import main.java.com.app.exception.BoardParserNegativeDimensionException;
import org.junit.jupiter.api.Test;

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

        StringBuilder sb = new StringBuilder();
        sb.append(String.format("C - %d - %d\n", width, height))
                .append(String.format("M - %d - %d\n", 0, 0))
                .append(String.format("M - %d - %d\n", 1, 1))
                .append(String.format("M - %d - %d\n", 2, 1));

        String content = sb.toString();
        Board board = BoardParser.getBoardFromString(content);

       CellValue value1 = board.getValueAt(0,0);
       CellValue value2 = board.getValueAt(1,1);
       CellValue value3 = board.getValueAt(2,1);

       assertNotNull(value1);
       assertNotNull(value2);
       assertNotNull(value3);

       assertTrue(value1 instanceof Mountain);
       assertTrue(value2 instanceof Mountain);
       assertTrue(value3 instanceof Mountain);
    }


    @Test
    public void getBoardFromString_fromCorrectFileWithOnlyThreasure_shouldSucceed() {
        int width = 10;
        int height = 10;

        StringBuilder sb = new StringBuilder();
        sb.append(String.format("C - %d - %d\n", width, height))
                .append(String.format("T - %d - %d - %d\n", 2, 1, 3))
                .append(String.format("T - %d - %d - %d\n", 5, 2, 2));

        String content = sb.toString();
        Board board = BoardParser.getBoardFromString(content);

        CellValue value1 = board.getValueAt(2, 1);
        CellValue value2 = board.getValueAt(5, 2);

        assertNotNull(value1);
        assertNotNull(value2);

        assertTrue(value1 instanceof Treasure);
        assertTrue(value2 instanceof Treasure);

        assertEquals(((Treasure) value1).amount(), 3);
        assertEquals(((Treasure) value2).amount(), 2);
    }

    @Test
    public void getBoardFromString_fromCorrectFileWithAllKindOfItem_shouldSucceed() {
        fail();
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