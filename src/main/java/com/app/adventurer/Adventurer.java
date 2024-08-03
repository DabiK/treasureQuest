package main.java.com.app.adventurer;

import java.util.Arrays;
import java.util.Map;

public class Adventurer {

    private String name;
    private int i;
    private int j;
    private Orientation orientation;
    private AdventurerSequence[] sequence;

    private static final Map<Orientation, Orientation> turnLeftMapping = Map.of(
            Orientation.E, Orientation.N,
            Orientation.N, Orientation.W,
            Orientation.W, Orientation.S,
            Orientation.S, Orientation.E
    );

    private static final Map<Orientation, Orientation> turnRightMapping = Map.of(
            Orientation.E, Orientation.S,
            Orientation.S, Orientation.W,
            Orientation.W, Orientation.N,
            Orientation.N, Orientation.E
    );

    public Adventurer(String name, int i, int j, Orientation orientation, AdventurerSequence[] sequence) {
        this.name = name;
        this.i = i;
        this.j = j;
        if(orientation == null){
            throw new IllegalArgumentException("Orientation should not be null");
        }

        if(sequence == null){
            throw new IllegalArgumentException("Orientation should not be null");
        }
        this.orientation = orientation;
        this.sequence = sequence;
    }


    public void turnLeft() {
        this.orientation = Adventurer.turnLeftMapping.get(this.orientation);
    }

    public void turnRight() {
        this.orientation = Adventurer.turnRightMapping.get(this.orientation);
    }

    public void moveForward() {
        switch (orientation){
            case E -> ++j;
            case N -> i = Math.max(0, i - 1);
            case S -> ++i;
            case W -> j = Math.max(0, j - 1);
        }
    }

    public void runSequence() {
        Arrays.stream(sequence).forEach(this::processCommand);
    }

    private void processCommand(AdventurerSequence adventurerSequence) {
        switch (adventurerSequence) {
            case A:
                moveForward();
                break;
            case G:
                turnLeft();
                break;
            case D:
                turnRight();
                break;
            default:
                throw new IllegalArgumentException("Unknown command: " + adventurerSequence);
        }
    }


    public String getName() {
        return name;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public AdventurerSequence[] getSequence() {
        return sequence;
    }
}
