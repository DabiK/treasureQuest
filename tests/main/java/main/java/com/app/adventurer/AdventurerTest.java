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

    @Test
    public void turnRight_shouldSucceed(){
        fail();
    }

    @Test
    public void moveForward_shouldSucceed(){
        fail();
    }

    @Test
    public void runSequence_shouldSucceed(){
        fail();
    }


}