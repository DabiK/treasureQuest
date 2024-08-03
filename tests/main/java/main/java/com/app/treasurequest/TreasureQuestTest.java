package main.java.com.app.treasurequest;

import main.java.com.app.Board;
import main.java.com.app.adventurer.Adventurer;
import main.java.com.app.adventurer.AdventurerSequence;
import main.java.com.app.adventurer.Orientation;
import main.java.com.app.exception.InvalidStartingPositionException;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class TreasureQuestTest {

    @Test
    void createTreasureQuest_withAdventurerOnNonSteppableCell_shouldFail() {
        Board board = new Board(5, 5);
        board.createMountain(0, 0); // Non-steppable cell

        Adventurer adventurer = new Adventurer("Luffy", 0, 0, Orientation.E, new AdventurerSequence[]{AdventurerSequence.A});
        assertThrows(InvalidStartingPositionException.class, () -> new TreasureQuest(board, Arrays.asList(adventurer)));
    }

    @Test
    void createTreasureQuest_withAdventurersOverlap_shouldFail() {
        Board board = new Board(5, 5);
        board.createMountain(0, 0); // Non-steppable cell
        board.createMountain(1, 1); // Non-steppable cell

        Adventurer adventurer1 = new Adventurer("Luffy", 0, 0, Orientation.E, new AdventurerSequence[]{AdventurerSequence.A});
        Adventurer adventurer2 = new Adventurer("Zoro", 0, 0, Orientation.N, new AdventurerSequence[]{AdventurerSequence.D});

        assertThrows(InvalidStartingPositionException.class, () -> new TreasureQuest(board, Arrays.asList(adventurer1, adventurer2)));
    }

    @Test
    void  createTreasureQuest_withAdventurersOnSteppableCellsAndNoOverlap_shouldSucceed() {
        Board board = new Board(5, 5);

        Adventurer adventurer1 = new Adventurer("Luffy", 0, 0, Orientation.E, new AdventurerSequence[]{AdventurerSequence.A});
        Adventurer adventurer2 = new Adventurer("Zoro", 1, 1, Orientation.N, new AdventurerSequence[]{AdventurerSequence.D});
        Adventurer adventurer3 = new Adventurer("Nami", 2, 2, Orientation.S, new AdventurerSequence[]{AdventurerSequence.G});

        TreasureQuest treasureQuest = new TreasureQuest(board, Arrays.asList(adventurer1, adventurer2, adventurer3));

        assertNotNull(treasureQuest);
        assertEquals(3, treasureQuest.getAdventurers().size());
        assertEquals(board, treasureQuest.getBoard());
    }

    @Test
    void treasureQuestToString_withAdventuredMountainsAndTreasure_shouldSucceed(){
        Board board = new Board(10, 10);

        board.createMountain(0,0);
        board.createMountain(1,1);
        board.createMountain(2,1);
        board.createMountain(0,0);
        board.createTreasures(2, 1, 3);
        board.createTreasures(5, 2, 2);

        Adventurer adventurer1 = new Adventurer("Luffy", 7, 7, Orientation.E, new AdventurerSequence[]{AdventurerSequence.A});
        Adventurer adventurer2 = new Adventurer("Zoro", 8, 8, Orientation.N, new AdventurerSequence[]{AdventurerSequence.D});
        Adventurer adventurer3 = new Adventurer("Nami", 9, 9, Orientation.S, new AdventurerSequence[]{AdventurerSequence.G});

        StringBuilder sb = new StringBuilder();

        sb.append(String.format("C - %d - %d\n", 10, 10))
                .append(String.format("M - %d - %d\n", 0, 0))
                .append(String.format("M - %d - %d\n", 1, 1))
                .append(String.format("M - %d - %d\n", 2, 1))
                .append(String.format("T - %d - %d - %d\n", 2, 1, 3))
                .append(String.format("T - %d - %d - %d\n", 5, 2, 2))
                .append("Luffy - 7 - 7 - E - 0\n")
                .append("Zoro - 8 - 8 - N - 0\n")
                .append("Nami - 9 - 9 - S - 0");

        TreasureQuest treasureQuest = new TreasureQuest(board, Arrays.asList(adventurer1, adventurer2, adventurer3));
        String out = treasureQuest.toString();

        assertNotNull(treasureQuest);
        assertEquals(sb.toString(), out);

    }



}