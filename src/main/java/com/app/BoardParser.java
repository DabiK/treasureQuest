package main.java.com.app;

import main.java.com.app.exception.BoardParserNegativeDimensionException;
import main.java.com.app.exception.InvalidArgumentLength;
import main.java.com.app.exception.InvalidIdentifierException;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

public class BoardParser {

    public static final String DIMENSION_KEY = "C";

    public static Board getBoardFromString(String content){
        String[] lines = content.split("\n");
        // process first line
        String dimensionLine = lines[0];
        String[] parts = dimensionLine.split(" - ");
        if(!parts[0].equals(BoardParser.DIMENSION_KEY)){
            throw new InvalidIdentifierException(parts[0].charAt(0));
        }
        int width = Integer.parseInt(parts[1]);
        int height = Integer.parseInt(parts[2]);
        if(width < 0 || height < 0){
            throw new BoardParserNegativeDimensionException();
        }
        Board board = new Board(width,height);
        AtomicInteger lineIndex =new AtomicInteger(2);
        Arrays.stream(lines)
                .skip(1)
                .forEach(line -> {
                    String[] chunks = line.split(" - ");
                    char itemType = chunks[0].charAt(0);

                    if (CellType.MOUNTAIN.getKey() == itemType) {
                        checkChunksLength(chunks, 3,lineIndex.get());
                        int mountainJ = Integer.parseInt(chunks[1]);
                        int mountainI = Integer.parseInt(chunks[2]);
                        board.createMountain(mountainI, mountainJ);
                    }
                    else if (CellType.TREASURE.getKey() == itemType) {
                        checkChunksLength(chunks, 4,lineIndex.get());
                        int treasureJ = Integer.parseInt(chunks[1]);
                        int treasureI = Integer.parseInt(chunks[2]);
                        int treasureAmount = Integer.parseInt(chunks[3]);
                        board.createTreasures(treasureAmount, treasureI, treasureJ);
                    }else{
                        throw new InvalidIdentifierException(itemType);
                    }
                    lineIndex.incrementAndGet();
        });
        return board;
    }

    private static void checkChunksLength(String[] chunks, int expectedLength, int line) {
        if (chunks.length != expectedLength){
            throw new InvalidArgumentLength(expectedLength, line);
        }
    }
}
