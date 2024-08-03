package main.java.com.app.treasurequest;

import main.java.com.app.Board;
import main.java.com.app.CellValue;
import main.java.com.app.Treasure;
import main.java.com.app.adventurer.Adventurer;
import main.java.com.app.exception.InvalidStartingPositionException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class TreasureQuest {

    private Board board;
    private List<Adventurer> adventurers;

    public TreasureQuest(Board board, List<Adventurer> adventurers){
        this.board = board;
        this.adventurers = adventurers;
        this.validateAdventurerPositions();
    }


    public void runSimulation(){
        boolean endGame = false;
        Set<String> positions = new HashSet<>();
        do{
            endGame = false;
            positions = adventurers.stream()
                    .map(Adventurer::getPositionAsString)
                    .collect(Collectors.toSet());
            // make one round
            for (Adventurer adventurer : adventurers) {
                positions.remove(adventurer.getPositionAsString());
                boolean forecast = adventurer.forcastNextSequence(positions);
                if (forecast) {
                    adventurer.runNextSequence();
                }else{
                    adventurer.skipSequence();
                }
                positions.add(adventurer.getPositionAsString());
                endGame = endGame || !adventurer.hasNextSequence();
            }
            positions.clear();
        }while (!endGame);
    }


    private void validateAdventurerPositions() {
        Set<String> positions = new HashSet<>();

        for (Adventurer adventurer : adventurers) {
            int i = adventurer.getI();
            int j = adventurer.getJ();

            // Check if the adventurer is on a steppable cell
            if (!board.isStepable(i,j)) {
                throw new InvalidStartingPositionException("Adventurer " + adventurer.getName() + " is on a non-steppable cell at position (" + i + "," + j + ").");
            }

            // Check for overlapping adventurers
            String position = i + "," + j;
            if (!positions.add(position)) {
                throw new InvalidStartingPositionException("Multiple adventurers are starting at the same position: (" + i + "," + j + ").");
            }
        }
    }

    public Board getBoard() {
        return board;
    }

    public List<Adventurer> getAdventurers() {
        return adventurers;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(board == null ? "" : board.toString());
        builder.append(adventurers.stream().map(Adventurer::toString)
                .collect(Collectors.joining("\n")));
        return builder.toString();
    }
}
