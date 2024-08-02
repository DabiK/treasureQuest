package main.java.com.app;

import main.java.com.app.exception.BoardParserNegativeDimensionException;

public class BoardParser {

    public static final String DIMENSION_KEY = "C";

    public static Board getBoardFromString(String content){
        String[] lines = content.split("\n");
        // process first line
        String dimensionLine = lines[0];
        String[] parts = dimensionLine.split(" - ");
        if(!parts[0].equals(BoardParser.DIMENSION_KEY)){
            throw new IllegalArgumentException();
        }
        int width = Integer.parseInt(parts[1]);
        int height = Integer.parseInt(parts[2]);
        if(width < 0 || height < 0){
            throw new BoardParserNegativeDimensionException();
        }
        Board board = new Board(width,height);
        return board;
    }
}
