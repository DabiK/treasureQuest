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
    public void getBoardFromString_withInvalidRegexPattern_shouldFail() {
        fail();
    }

    @Test
    public void getBoardFromString_withMultipleDimensionLine_shouldFail() {
        fail();
    }

    @Test
    public void getBoardFromString_fromCorrectFileWithOnlyMountain_shouldSucceed() {
        fail();
    }

    @Test
    public void getBoardFromString_fromCorrectFileWithOnlyThreasure_shouldSucceed() {
        fail();
    }

    @Test
    public void getBoardFromString_fromCorrectFileWithAllKindOfItem_shouldSucceed() {
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