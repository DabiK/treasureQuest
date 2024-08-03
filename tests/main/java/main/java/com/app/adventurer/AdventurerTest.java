package main.java.com.app.adventurer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.Assert.*;

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

    @Test
    public void runSequence_shouldSucceed(){
        fail();
    }


}