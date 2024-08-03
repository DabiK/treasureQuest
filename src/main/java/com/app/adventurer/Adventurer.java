package main.java.com.app.adventurer;

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
