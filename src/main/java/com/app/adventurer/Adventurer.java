package main.java.com.app.adventurer;

public class Adventurer {

    private String name;
    private int i;
    private int j;
    private Orientation orientation;
    private AdventurerSequence[] sequence;

    public Adventurer(String name, int i, int j, Orientation orientation, AdventurerSequence[] sequence) {
        this.name = name;
        this.i = i;
        this.j = j;
        this.orientation = orientation;
        this.sequence = sequence;
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
