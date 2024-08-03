package main.java.com.app.adventurer;

import main.java.com.app.board.Board;
import main.java.com.app.exception.InvalidStartingPositionException;
import main.java.com.app.exception.TreasureNotCollectible;

import java.util.*;

public class Adventurer {
    private String name;
    private int i;
    private int j;
    private int treasure;
    private Orientation orientation;
    private Board board;
    private AdventurerSequence[] sequence;
    private int currentIndex;
    public static final char KEY = 'A';


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
        this(name,i,j, orientation, sequence, 0);
    }

    protected Adventurer(String name, int i, int j, Orientation orientation, AdventurerSequence[] sequence, int treasure) {
        this(name,i,j,orientation, sequence, treasure,null);
    }

    public Adventurer(String name, int i, int j, Orientation orientation, AdventurerSequence[] sequence, int treasure, Board board) {
        this.name = name;
        this.i = i;
        this.j = j;
        this.treasure = treasure;
        this.board = board;
        this.currentIndex = 0;
        if(board != null){
            if(!board.isValidCoords(i,j)){
                throw new InvalidStartingPositionException("Initial position invalid");
            }

            if(!board.isStepable(i,j)){
                throw new InvalidStartingPositionException("Inital position not stepable");
            }
        }
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
        Integer[] nextPos = this.nextPosition();
        if((board != null && board.isStepable(nextPos[0], nextPos[1])) || board == null){
            this.i = nextPos[0];
            this.j = nextPos[1];

            if (board != null && board.isTreasureAt(i, j)) {
                this.incTreasure();
                try {
                    board.collectTreasure(i, j);
                } catch (TreasureNotCollectible e) { // should not happen in a non concurrent scenario
                    e.printStackTrace();
                }
            }
        }
    }

    public Integer[] nextPosition(){
        int newI = i;
        int newJ = j;
        switch (orientation){
            case E -> newJ = board == null ? j + 1:  Math.min(board.getWidth(),j + 1);
            case N -> newI = Math.max(0, i - 1);
            case S -> newI = board == null ? i + 1 : Math.min(board.getHeight(),i + 1);
            case W -> newJ = Math.max(0, j - 1);
        }

        return new Integer[]{newI, newJ};
    }


    public boolean forcastNextSequence(Set<String> blockedPositions) {


        if(this.hasNextSequence()){
            if(getNextSequence() == AdventurerSequence.A){ // if it's a movement we check the next coord
                Integer[] nextPos = this.nextPosition();
                int newI = nextPos[0];
                int newJ = nextPos[1];
               return !blockedPositions.contains(String.format("%d-%d", newI,newJ))
                        && board.isStepable(newI, newJ);
            }
            return true;
        }

        return true;
    }

    public void runSequence() {
        Arrays.stream(sequence).forEach(this::processCommand);
    }

    public boolean runNextSequence(){
        if(!this.hasNextSequence()){
            return false;
        }
        this.processCommand(sequence[currentIndex++]);
        return true;
    }

    public AdventurerSequence getNextSequence(){
        return sequence[currentIndex];
    }

    public void skipSequence() {
        currentIndex++;
    }

    public boolean hasNextSequence(){
        return currentIndex < sequence.length;
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

    public int getTreasure(){
        return treasure;
    }

    @Override
    public String toString() {
        return String.format("%s - %s - %d - %d - %s - %d", Adventurer.KEY, name,j, i, orientation,treasure);
    }

    public String getPositionAsString(){
        return String.format("%d-%d", i,j);
    }

    public Board getBoard() {
        return board;
    }

    public void incTreasure() {
        treasure++;
    }

}
