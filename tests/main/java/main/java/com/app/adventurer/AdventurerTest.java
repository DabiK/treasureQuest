package main.java.com.app.adventurer;

import main.java.com.app.Board;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

class AdventurerTest {

    @Test
    public void instanciate_withCorrectParameter_shouldSucceed() {
        String name = "Luffy";
        int i = 0;
        int j = 0;
        Orientation orientation = Orientation.E;
        AdventurerSequence[] sequence = { AdventurerSequence.A, AdventurerSequence.A, AdventurerSequence.A, };
        Adventurer adventurer = new Adventurer(name, i, j, orientation, sequence);

        assertNotNull(adventurer);
        assertEquals(name, adventurer.getName());
        assertEquals(i, adventurer.getI());
        assertEquals(j, adventurer.getJ());
        assertEquals(orientation, adventurer.getOrientation());
        assertArrayEquals(sequence, adventurer.getSequence());
    }

    @ParameterizedTest
    @CsvSource({
            "E, N",
            "N, W",
            "W, S",
            "S, E"
    })
    public void turnLeft_shouldSucceed(Orientation orientation, Orientation expectedOrientation){
        String name = "Luffy";
        int i = 0;
        int j = 0;
        AdventurerSequence[] sequence = { AdventurerSequence.A, AdventurerSequence.A, AdventurerSequence.A, };
        Adventurer adventurer = new Adventurer(name, i, j, orientation, sequence);
        adventurer.turnLeft();
        assertEquals(expectedOrientation, adventurer.getOrientation());
    }

    @ParameterizedTest
    @CsvSource({
            "E, S",
            "S, W",
            "W, N",
            "N, E"
    })
    public void turnRight_shouldSucceed(Orientation orientation, Orientation expectedOrientation){
        String name = "Luffy";
        int i = 0;
        int j = 0;
        AdventurerSequence[] sequence = { AdventurerSequence.A, AdventurerSequence.A, AdventurerSequence.A, };
        Adventurer adventurer = new Adventurer(name, i, j, orientation, sequence);
        adventurer.turnRight();
        assertEquals(expectedOrientation, adventurer.getOrientation());
    }

    @ParameterizedTest
    @CsvSource({
            "E, 0, 0, 0, 1",
            "S, 0, 0, 1, 0",
            "N, 1, 1, 0, 1",
            "W, 1, 1, 1, 0",
            "W, 0, 0, 0, 0",
            "N, 0, 0, 0, 0",
    })
    public void moveForward_shouldSucceed(Orientation orientation, int i, int j, int expectedI, int expectedJ){
        String name = "Luffy";
        AdventurerSequence[] sequence = { AdventurerSequence.A, AdventurerSequence.A, AdventurerSequence.A, };
        Adventurer adventurer = new Adventurer(name, i, j, orientation, sequence);

        adventurer.moveForward();

        assertEquals(expectedI,adventurer.getI());
        assertEquals(expectedJ,adventurer.getJ());
    }

    @ParameterizedTest
    @MethodSource("provideSequences")
    public void runSequence_shouldSucceed(SequenceTestsData sequenceTestData){

        String name = "Luffy";
        Adventurer adventurer = new Adventurer(name, sequenceTestData.startI(), sequenceTestData.startJ(), sequenceTestData.startOrientation(), sequenceTestData.sequence());
        adventurer.runSequence();

        assertEquals(sequenceTestData.expectedI(),adventurer.getI());
        assertEquals(sequenceTestData.expectedJ(), adventurer.getJ());
    }


    @ParameterizedTest
    @CsvSource({
            "'Luffy', 0, 0, N, 0, 'Luffy - 0 - 0 - N - 0'",
            "'Zoro', 12342, 4949, S, 12, 'Zoro - 12342 - 4949 - S - 12'",
            "'Nami', 1, 0, W, 100000, 'Nami - 1 - 0 - W - 100000'",
            "'Sanji', 1, 0, E, 0, 'Sanji - 1 - 0 - E - 0'",
    })
    public void adventuredToString_shouldSucced(String name,int j,  int i ,  Orientation orientation, int treasure, String expectedOut){
        Adventurer adventurer = new Adventurer(name,i,j, orientation, new AdventurerSequence[]{}, treasure);
        String out = adventurer.toString();
        assertEquals(expectedOut,out);
    }

    @Test
    public void createAdventurer_withBoard_shouldSucceed(){
        String name = "Luffy";
        int i = 0;
        int j = 0;
        Orientation orientation = Orientation.E;
        AdventurerSequence[] sequence = { AdventurerSequence.A, AdventurerSequence.A, AdventurerSequence.A, };
        Board board = new Board(10,10);
        board
                .withMountain(2,2)
                .withMountain(1,1)
                .withMountain(3,3)
                .withTreasure(3,1, 1);
        Adventurer adventurer = new Adventurer(name, i, j, orientation, sequence, 0, board);

        assertEquals(adventurer.getBoard(), board); // same reference
    }

    @Test
    public void createAdventurer_withBoardAndPositionOutOfBoundCoords_shouldFail(){
        String name = "Luffy";
        int i = 11;
        int j = 11;
        Orientation orientation = Orientation.E;
        AdventurerSequence[] sequence = { AdventurerSequence.A, AdventurerSequence.A, AdventurerSequence.A, };
        Board board = new Board(10,10);
        board
                .withMountain(2,2)
                .withMountain(1,1)
                .withMountain(3,3)
                .withTreasure(3,1, 1);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, ()  -> new Adventurer(name, i, j, orientation, sequence, 0, board));

        assertNotNull(exception);
        assertEquals("Initial position invalid", exception.getMessage());
    }

    @Test
    public void createAdventurer_withBoardButAlreadyOccupiedByStepableEntity_shouldSucced(){
        String name = "Luffy";
        int i = 0;
        int j = 0;
        Orientation orientation = Orientation.E;
        AdventurerSequence[] sequence = { AdventurerSequence.A, AdventurerSequence.A, AdventurerSequence.A, };
        Board board = new Board(10,10);
        board
                .withMountain(2,2)
                .withMountain(1,1)
                .withMountain(1,1)
                .withTreasure(3,i, j); // treasure is steapable
        assertDoesNotThrow(()  -> new Adventurer(name, i, j, orientation, sequence, 0, board));
    }

    @Test
    public void createAdventurer_withBoardButAlreadyOccupiedCoords_shouldFail(){
        String name = "Luffy";
        int i = 0;
        int j = 0;
        Orientation orientation = Orientation.E;
        AdventurerSequence[] sequence = { AdventurerSequence.A, AdventurerSequence.A, AdventurerSequence.A, };
        Board board = new Board(10,10);
        board
                .withMountain(2,2)
                .withMountain(1,1)
                .withMountain(i,j)
                .withTreasure(3,1, 1);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, ()  -> new Adventurer(name, i, j, orientation, sequence, 0, board));

        assertNotNull(exception);
        assertEquals("Inital position not stepable", exception.getMessage());
    }


    @Test
    public void adventuredToString_withBoard_shouldSucceed() {
        fail();
    }


    private record SequenceTestsData(AdventurerSequence[] sequence, int startI, int startJ, Orientation startOrientation, int expectedI, int expectedJ) {};


    // Provide the sequences as method source
    private static Stream<SequenceTestsData> provideSequences() {
        return Stream.of(
                new SequenceTestsData(new AdventurerSequence[]{AdventurerSequence.A, AdventurerSequence.A, AdventurerSequence.A, AdventurerSequence.A,AdventurerSequence.A,AdventurerSequence.A}, 0, 0, Orientation.E, 0, 6),
                new SequenceTestsData(new AdventurerSequence[]{AdventurerSequence.A, AdventurerSequence.A, AdventurerSequence.A, AdventurerSequence.A,AdventurerSequence.A,AdventurerSequence.A}, 0, 0, Orientation.S, 6, 0),
                new SequenceTestsData(new AdventurerSequence[]{AdventurerSequence.D, AdventurerSequence.G, AdventurerSequence.A, AdventurerSequence.G,AdventurerSequence.G,AdventurerSequence.A}, 0, 0, Orientation.N, 1, 0),
                new SequenceTestsData(new AdventurerSequence[]{AdventurerSequence.A, AdventurerSequence.D}, 2, 3, Orientation.E, 2, 4),
                new SequenceTestsData(new AdventurerSequence[]{AdventurerSequence.D, AdventurerSequence.A, AdventurerSequence.G, AdventurerSequence.G}, 1, 1, Orientation.S, 1, 0),
                new SequenceTestsData(new AdventurerSequence[]{AdventurerSequence.A}, 4, 4, Orientation.W, 4, 3),
                new SequenceTestsData(new AdventurerSequence[]{AdventurerSequence.G, AdventurerSequence.D, AdventurerSequence.D, AdventurerSequence.G, AdventurerSequence.A}, 0, 5, Orientation.N, 0, 5),
                new SequenceTestsData(new AdventurerSequence[]{AdventurerSequence.A, AdventurerSequence.G}, 2, 2, Orientation.S, 3, 2),
                new SequenceTestsData(new AdventurerSequence[]{AdventurerSequence.D, AdventurerSequence.D, AdventurerSequence.D, AdventurerSequence.A, AdventurerSequence.A}, 3, 3, Orientation.E, 1, 3),
                new SequenceTestsData(new AdventurerSequence[]{AdventurerSequence.A, AdventurerSequence.G}, 4, 4, Orientation.N, 3, 4),
                new SequenceTestsData(new AdventurerSequence[]{AdventurerSequence.G, AdventurerSequence.A, AdventurerSequence.G, AdventurerSequence.G, AdventurerSequence.D}, 5, 5, Orientation.W, 6, 5),
                new SequenceTestsData(new AdventurerSequence[]{AdventurerSequence.A, AdventurerSequence.A, AdventurerSequence.G, AdventurerSequence.D}, 6, 6, Orientation.S, 8, 6)
        );
    }



}