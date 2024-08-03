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

        for (String line : lines) {
            parts = line.split(" - ");
            char itemType = parts[0].charAt(0);

            if (CellType.MOUNTAIN.getKey() == itemType) {
                int mountainJ = Integer.parseInt(parts[1]);
                int mountainI = Integer.parseInt(parts[2]);
                board.createMountain(mountainI, mountainJ);
            }
            if (CellType.TREASURE.getKey() == itemType) {
                int treasureJ = Integer.parseInt(parts[1]);
                int treasureI = Integer.parseInt(parts[2]);
                int treasureAmount = Integer.parseInt(parts[3]);
                board.createTreasures(treasureAmount, treasureI, treasureJ);
            }

        }
        return board;
    }
}
