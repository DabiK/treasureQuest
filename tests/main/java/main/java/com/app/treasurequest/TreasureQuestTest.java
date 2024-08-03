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
                .append(String.format("M - %d - %d\n", 1, 2))
                .append(String.format("T - %d - %d - %d\n", 3, 1, 2))
                .append(String.format("T - %d - %d - %d\n", 2, 2, 5))
                .append("A - Luffy - 7 - 7 - E - 0\n")
                .append("A - Zoro - 8 - 8 - N - 0\n")
                .append("A - Nami - 9 - 9 - S - 0");

        TreasureQuest treasureQuest = new TreasureQuest(board, Arrays.asList(adventurer1, adventurer2, adventurer3));
        String out = treasureQuest.toString();

        assertNotNull(treasureQuest);
        assertEquals(sb.toString(), out);
    }


    @Test
    void runSimulation_with2Player_shouldCollectTreasure(){

        Board board = new Board(5, 5);
        board.createTreasures(1, 1, 1);
        board.createTreasures(2, 2, 1);
        board.createTreasures(3, 3, 2);

        Adventurer adventurer1 = new Adventurer("Luffy", 0, 0, Orientation.E,
                new AdventurerSequence[]{AdventurerSequence.A,AdventurerSequence.D, AdventurerSequence.A,  AdventurerSequence.A}, 0, board);
        Adventurer adventurer2 = new Adventurer("Zoro", 4, 4, Orientation.W,
                new AdventurerSequence[]{AdventurerSequence.A, AdventurerSequence.A, AdventurerSequence.D, AdventurerSequence.A}, 0, board);

        TreasureQuest treasureQuest = new TreasureQuest(board, Arrays.asList(adventurer1, adventurer2));
        treasureQuest.runSimulation();

        assertEquals(2, adventurer1.getTreasure());
        assertEquals(2, adventurer1.getI());
        assertEquals(1, adventurer1.getJ());

        assertEquals(1, adventurer2.getTreasure());
        assertEquals(3, adventurer2.getI());
        assertEquals(2, adventurer2.getJ());

        // Check if the treasures on the board have been collected correctly
        assertFalse(board.isTreasureAt(1, 1)); // Treasure at (1, 1) should be collected
        assertFalse(board.isTreasureAt(1, 1)); // Treasure at (2, 1) should be collected
        assertFalse(board.isTreasureAt(2, 2)); // Treasure at (2, 2) should be collected
    }


    @Test
    void runSimulation_with2PlayerWithConflifOfTreasure_1PlayerShouldCollect(){

        Board board = new Board(5, 5);
        board.createTreasures(1, 1, 1);

        Adventurer adventurer1 = new Adventurer("Luffy", 1, 0, Orientation.E,
                new AdventurerSequence[]{AdventurerSequence.A,}, 0, board);
        Adventurer adventurer2 = new Adventurer("Zoro", 1, 2, Orientation.W,
                new AdventurerSequence[]{AdventurerSequence.A,}, 0, board); // should not move cause will bump into player 1

        TreasureQuest treasureQuest = new TreasureQuest(board, Arrays.asList(adventurer1, adventurer2));
        treasureQuest.runSimulation();

        assertEquals(1, adventurer1.getTreasure());
        assertEquals(1, adventurer1.getI());
        assertEquals(1, adventurer1.getJ());

        assertEquals(0, adventurer2.getTreasure());
        assertEquals(1, adventurer2.getI());
        assertEquals(2, adventurer2.getJ());

        // Check if the treasures on the board have been collected correctly
        assertFalse(board.isTreasureAt(1, 1)); // Treasure at (1, 1) should be collected
    }

    @Test
    public void runSimulation_withBaseCase_shouldSucceed(){
        String input =
                "C - 3 - 4\n" +
                "M - 1 - 0\n" +
                "M - 2 - 1\n" +
                "T - 0 - 3 - 2\n" +
                "T - 1 - 3 - 3\n" +
                "A - Lara - 1 - 1 - S - AADADAGGA";

        String expectedOutput = "C - 3 - 4\n" +
                "M - 1 - 0\n" +
                "M - 2 - 1\n" +
                "T - 1 - 3 - 2\n"+
                "A - Lara - 0 - 3 - S - 3";

        TreasureQuest treasureQuest = TreasureQuestParser.getTreasureQuestFromString(input);
        treasureQuest.runSimulation();

        String output = treasureQuest.toString();
        assertEquals(expectedOutput, output);
    }

    @Test
    public void runSimulation_withBaseCaseWithMoutain_shouldRemainStuck(){
        String input =
                "C - 3 - 4\n" +
                        "M - 1 - 0\n" +
                        "M - 2 - 1\n" +
                        "T - 0 - 3 - 2\n" +
                        "T - 1 - 3 - 3\n" +
                        "A - Lara - 2 - 0 - S - AADADAGGA";

        String expectedOutput = "C - 3 - 4\n" +
                "M - 1 - 0\n" +
                "M - 2 - 1\n" +
                "T - 0 - 3 - 2\n" +
                "T - 1 - 3 - 3\n"+
                "A - Lara - 2 - 0 - S - 0";

        TreasureQuest treasureQuest = TreasureQuestParser.getTreasureQuestFromString(input);
        treasureQuest.runSimulation();

        String output = treasureQuest.toString();
        assertEquals(expectedOutput, output);
    }


    @Test
    public void runSimulation_withOnlyPlayer_shouldRemainStuck(){
        String input =
                "C - 2 - 2\n" +
                        "A - Luffy - 0 - 0 - W - ADAGGA\n"+
                        "A - Nami - 1 - 0 - S - AADADAGGA\n"+
                        "A - Zoro - 1 - 1 - S - AADADAGGA\n"+
                        "A - Robin - 0 - 1 - S - AADADAGGA\n";

        String expectedOutput =
                "C - 2 - 2\n" +
                "A - Luffy - 0 - 0 - S - 0\n"+
                "A - Nami - 1 - 0 - N - 0\n"+
                "A - Zoro - 1 - 1 - N - 0\n"+
                "A - Robin - 0 - 1 - N - 0";

        TreasureQuest treasureQuest = TreasureQuestParser.getTreasureQuestFromString(input);
        treasureQuest.runSimulation();

        String output = treasureQuest.toString();
        assertEquals(expectedOutput, output);
    }



}